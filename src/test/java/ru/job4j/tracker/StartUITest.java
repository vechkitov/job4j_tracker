package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        MemTracker tracker = new MemTracker();
        Output out = new ConsoleOutput();
        List<UserAction> actions = List.of(
                new CreateAction(out),
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("Replaced item");
        tracker.add(item);
        int itemId = item.getId();
        String newName = "New item name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(itemId), newName, "1"}
        );
        Output out = new ConsoleOutput();
        List<UserAction> actions = List.of(
                new ReplaceAction(out),
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(newName));
    }

    @Test
    public void whenDeleteItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("Deleted item");
        tracker.add(item);
        int itemId = item.getId();
        Input in = new StubInput(
                new String[]{"0", String.valueOf(itemId), "1"}
        );
        Output out = new ConsoleOutput();
        List<UserAction> actions = List.of(
                new DeleteAction(out),
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Exit Program" + System.lineSeparator()
        ));
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        int itemId = item.getId();
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(itemId), replaceName, "1"}
        );
        List<UserAction> actions = List.of(
                new ReplaceAction(out),
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "=== Edit item ====" + ln
                        + "Заявка изменена успешно." + ln
                        + "Menu." + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
        ));
    }

    @Test
    public void whenShowAllGiven2Items() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = new Item("test1");
        Item two = new Item("test2");
        tracker.add(one);
        tracker.add(two);
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        List<UserAction> actions = List.of(
                new ShowAllAction(out),
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Show all items ====" + ln
                        + one + ln
                        + two + ln
                        + "Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
        ));
    }

    @Test
    public void whenShowAllGivenNoItems() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        List<UserAction> actions = List.of(
                new ShowAllAction(out),
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Show all items ====" + ln
                        + "Хранилище еще не содержит заявок" + ln
                        + "Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
        ));
    }

    @Test
    public void whenFindByNameIfNameExists() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String itemName = "test1";
        Item one = new Item(itemName);
        Item two = new Item("test2");
        Item three = new Item(itemName);
        tracker.add(one);
        tracker.add(two);
        tracker.add(three);
        Input in = new StubInput(
                new String[] {"0", itemName, "1"}
        );
        List<UserAction> actions = List.of(
                new FindByNameAction(out),
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Find items by name ====" + ln
                        + one + ln
                        + three + ln
                        + "Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
        ));
    }

    @Test
    public void whenFindByNameIfNameDoesNotExist() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String itemName = "test1";
        Input in = new StubInput(
                new String[] {"0", itemName, "1"}
        );
        List<UserAction> actions = List.of(
                new FindByNameAction(out),
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Find items by name ====" + ln
                        + "Заявки с именем: " + itemName + " не найдены." + ln
                        + "Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
        ));
    }

    @Test
    public void whenFindByIdIfIdExists() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = new Item("test1");
        Item two = new Item("test2");
        tracker.add(one);
        tracker.add(two);
        int itemId = two.getId();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(itemId), "1"}
        );
        List<UserAction> actions = List.of(
                new FindByIdAction(out),
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Find item by id ====" + ln
                        + two + ln
                        + "Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
        ));
    }

    @Test
    public void whenFindByIdIfIdDoesNotExist() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = new Item("test1");
        Item two = new Item("test2");
        tracker.add(one);
        tracker.add(two);
        int itemId = 3;
        Input in = new StubInput(
                new String[] {"0", String.valueOf(itemId), "1"}
        );
        List<UserAction> actions = List.of(
                new FindByIdAction(out),
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Find item by id ====" + ln
                        + "Заявка с введенным id: " + itemId + " не найдена." + ln
                        + "Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1", "0"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(
                new ExitProgramAction()
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                        "Menu." + ln
                                + "0. Exit Program" + ln
                                + "Wrong input, you can select: 0 .. 0" + ln
                                + "Menu." + ln
                                + "0. Exit Program" + ln
                )
        );
    }
}
