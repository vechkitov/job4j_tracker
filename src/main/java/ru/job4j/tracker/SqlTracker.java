package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public SqlTracker() {
        init();
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
        try (final PreparedStatement ps = cn.prepareStatement(
                "insert into items(name, created) values(?,?);",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.execute();
            try (final ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    item.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(String.format(
                    "Не удалось добавить заявку %s в БД: %s", item, e));
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        try (final PreparedStatement ps = cn.prepareStatement(
                "update items set name = ?, created = ? where id = ?;")) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.setInt(3, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException(String.format(
                    "Не удалось заменить заявку в БД (id=%d): %s", id, e));
        }
    }

    @Override
    public boolean delete(int id) {
        try (final PreparedStatement ps = cn.prepareStatement(
                "delete from items where id = ?;")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException(String.format(
                    "Не удалось удалить заявку из БД (id=%d): %s", id, e));
        }
    }

    @Override
    public List<Item> findAll() {
        final List<Item> items = new ArrayList<>();
        try (final PreparedStatement ps = cn.prepareStatement(
                "select id, name, created from items;")) {
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(
                            new Item(rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getTimestamp("created").toLocalDateTime())
                    );
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(String.format(
                    "Не удалось получить заявки из БД: %s", e));
        }
        return items;
    }

    @Override
    public List<Item> findByName(String name) {
        final List<Item> items = new ArrayList<>();
        try (final PreparedStatement ps = cn.prepareStatement(
                "select id, name, created from items where name = ?;")) {
            ps.setString(1, name);
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(
                            new Item(rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getTimestamp("created").toLocalDateTime())
                    );
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(String.format(
                    "Не удалось получить заявки из БД с name=%s: %s", name, e));
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (final PreparedStatement ps = cn.prepareStatement(
                "select id, name, created from items where id = ?;")) {
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = new Item(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getTimestamp("created").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(String.format(
                    "Не удалось найти заявку в БД с id=%d: %s", id, e));
        }
        return item;
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }
}
