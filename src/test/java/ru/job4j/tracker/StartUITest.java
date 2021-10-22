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
        Output out = new ConsoleOutput();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitProgramAction()
        };
        new StartUI(out).init(in, tracker, actions);
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
                new String[]{"0", String.valueOf(itemId), newName, "1"}
        );
        Output out = new ConsoleOutput();
        UserAction[] actions = {
                new ReplaceAction(out),
                new ExitProgramAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(newName));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("Deleted item");
        tracker.add(item);
        int itemId = item.getId();
        Input in = new StubInput(
                new String[]{"0", String.valueOf(itemId), "1"}
        );
        Output out = new ConsoleOutput();
        UserAction[] actions = {
                new DeleteAction(out),
                new ExitProgramAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ExitProgramAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Exit Program" + System.lineSeparator()
        ));
    }
}
