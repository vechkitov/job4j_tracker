package ru.job4j.tracker;

public class ExitProgramAction implements UserAction {
    @Override
    public String name() {
        return "Exit Program";
    }

    @Override
    public boolean execute(Input input, Store store) {
        return false;
    }
}
