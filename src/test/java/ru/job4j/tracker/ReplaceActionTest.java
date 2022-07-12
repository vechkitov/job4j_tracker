package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReplaceActionTest {

    @Test
    public void whenReplaceItem() {
        String itemName = "replaced item";
        MemTracker tracker = new MemTracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                String.valueOf(item.getId()),
                itemName
        };
        Output out = new ConsoleOutput();
        new ReplaceAction(out).execute(new StubInput(answers), tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is(itemName));
    }
}
