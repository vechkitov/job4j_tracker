package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class DeleteActionTest {

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        int itemId = item.getId();
        String[] answers = {String.valueOf(itemId)};
        Input input = new StubInput(answers);
        Output out = new ConsoleOutput();
        new DeleteAction(out).execute(input, tracker);
        Item actual = tracker.findById(itemId);
        assertNull(actual);
    }
}
