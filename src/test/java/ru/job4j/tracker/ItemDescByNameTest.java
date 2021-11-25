package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemDescByNameTest {

    @Test
    public void whenCompare() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item("item 1");
        Item item2 = new Item("item 2");
        Item item3 = new Item("item 3");
        items.add(item3);
        items.add(item1);
        items.add(item2);
        List<Item> expected = new ArrayList<>();
        expected.add(item3);
        expected.add(item2);
        expected.add(item1);
        Collections.sort(items, new ItemDescByName());
        assertEquals(expected, items);
    }
}
