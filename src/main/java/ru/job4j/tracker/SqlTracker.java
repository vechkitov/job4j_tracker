package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class);
    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in =
                     SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement ps = cn.prepareStatement(
                "insert into items(name, created) values(?,?);",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.execute();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    item.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOG.error("Не удалось добавить заявку '{}' в БД", item, e);
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement ps = cn.prepareStatement(
                "update items set name = ?, created = ? where id = ?;")) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.setInt(3, id);
            rsl = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error("Не удалось заменить заявку с id={} в БД", id, e);
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement ps = cn.prepareStatement(
                "delete from items where id = ?;")) {
            ps.setInt(1, id);
            rsl = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error("Не удалось удалить заявку с id={} из БД", id, e);
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        final List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "select id, name, created from items;")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(createItem(rs));
                }
            }
        } catch (SQLException e) {
            LOG.error("Не удалось получить заявки из БД", e);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String name) {
        final List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "select id, name, created from items where name = ?;")) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(createItem(rs));
                }
            }
        } catch (SQLException e) {
            LOG.error("Не удалось получить заявки с name='{}' из БД", name, e);
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement ps = cn.prepareStatement(
                "select id, name, created from items where id = ?;")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = createItem(rs);
                }
            }
        } catch (SQLException e) {
            LOG.error("Не удалось получить заявку с id={} из БД", id, e);
        }
        return item;
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    private Item createItem(ResultSet rs) throws SQLException {
        return new Item(rs.getInt("id"),
                rs.getString("name"),
                rs.getTimestamp("created").toLocalDateTime());
    }
}
