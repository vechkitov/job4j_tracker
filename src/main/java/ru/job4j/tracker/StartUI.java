package ru.job4j.tracker;

import java.time.LocalTime;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(
                new CreateAction(output),
                new ShowAllAction(output),
                new ReplaceAction(output),
                new DeleteAction(output),
                new FindByIdAction(output),
                new FindByNameAction(output),
                new CreateMassAction(output),
                new DeleteHalfOfItemsAction(output),
                new ExitProgramAction()
        );
        new StartUI(output).init(input, tracker, actions);
    }

    /**
     * Инициализирует приложение и запускает выполнение различных пользовательских действий
     *
     * @param input   Input
     * @param store   Store
     * @param actions list of UserAction
     */
    public void init(Input input, Store store, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            LocalTime t1 = LocalTime.now();
            run = action.execute(input, store);
            System.out.printf("%s - %s%n", t1, LocalTime.now());
        }
    }

    /**
     * Выводит на экран меню доступных пользовательских действий
     */
    private void showMenu(List<UserAction> actions) {
        out.println("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }
}
