package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CreateActionTest {

    @Test
    public void whenAddItem() {
        String itemName = "Fix PC";
        String[] answers = {itemName};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        Output out = new ConsoleOutput();
        new CreateAction(out).execute(input, tracker);
        Item created = tracker.findAll().get(0);
        Item expected = new Item(itemName);
        assertThat(created.getName(), is(expected.getName()));
    }
}
