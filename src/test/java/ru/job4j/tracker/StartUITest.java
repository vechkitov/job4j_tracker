package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(),
                new ExitProgramAction()
        };
        new StartUI().init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("Replaced item");
        tracker.add(item);
        int itemId = item.getId();
        String newName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(itemId), newName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(),
                new ExitProgramAction()
        };
        new StartUI().init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(newName));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("Deleted item");
        tracker.add(item);
        int itemId = item.getId();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(itemId), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(),
                new ExitProgramAction()
        };
        new StartUI().init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }
}
