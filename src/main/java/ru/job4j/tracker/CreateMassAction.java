package ru.job4j.tracker;

public class CreateMassAction implements UserAction {
    private final Output out;
    private int amountOfItems;

    public CreateMassAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add multiple items";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Create multiple items ====");
        amountOfItems = input.askInt("Enter amount of items: ");
        for (int i = 0; i < amountOfItems; i++) {
            Item item = new Item();
            store.add(item);
            item.setName(String.format("Item %d", item.getId()));
        }
        out.println(String.format("Создано заявок: %d", amountOfItems));
        return true;
    }
}
