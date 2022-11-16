package ru.job4j.tracker;

import java.util.List;

public class DeleteHalfOfItemsAction implements UserAction {
    private final Output out;

    public DeleteHalfOfItemsAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete half of items";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Delete half of items ====");
        List<Item> items = store.findAll();
        for (int i = 0; i < items.size() / 2; i++) {
            store.delete(items.get(i).getId());
        }
        return true;
    }
}
