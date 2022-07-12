package ru.job4j.tracker;

import java.sql.SQLException;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public static void main(String[] args) throws SQLException {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        try (SqlTracker tracker = new SqlTracker()) {
            List<UserAction> actions = List.of(
                    new CreateAction(output),
                    new ShowAllAction(output),
                    new ReplaceAction(output),
                    new DeleteAction(output),
                    new FindByIdAction(output),
                    new FindByNameAction(output),
                    new ExitProgramAction()
            );
            new StartUI(output).init(input, tracker, actions);
        }
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
            run = action.execute(input, store);
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
