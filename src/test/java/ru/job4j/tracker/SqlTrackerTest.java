package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {

    private static Connection cn;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader()
                .getResourceAsStream("test.properties")) {
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

    @AfterClass
    public static void closeConnection() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    @After
    public void wipeTable() throws Exception {
        try (PreparedStatement ps = cn.prepareStatement("delete from items;")) {
            ps.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(cn);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenReplaceItemThenReturnNewName() {
        SqlTracker tracker = new SqlTracker(cn);
        Item item = new Item("item");
        tracker.add(item);
        tracker.replace(item.getId(), new Item("new item"));
        assertThat(tracker.findById(item.getId()).getName(), is("new item"));
    }

    @Test
    public void whenDeleteItemThenItemNotFound() {
        SqlTracker tracker = new SqlTracker(cn);
        Item item = new Item("item");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenFindAllThenReturnAllItems() {
        SqlTracker tracker = new SqlTracker(cn);
        tracker.add(new Item("First"));
        tracker.add(new Item("Second"));
        assertThat(tracker.findAll().size(), is(2));
    }

    @Test
    public void whenFindByNameThenReturnItemsWithTheSameName() {
        SqlTracker tracker = new SqlTracker(cn);
        final String name = "First";
        tracker.add(new Item(name));
        tracker.add(new Item(name));
        List<Item> result = tracker.findByName(name);
        assertThat(result.size(), is(2));
        assertThat(result.get(0).getName(), is(name));
    }

    @Test
    public void whenFindByIdThenReturnItemWithTheSameId() {
        SqlTracker tracker = new SqlTracker(cn);
        Item item = new Item("item");
        tracker.add(item);
        Item foundItem = tracker.findById(item.getId());
        assertThat(foundItem.getId(),
                is(item.getId()));
    }
}
