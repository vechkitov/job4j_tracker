package ru.job4j.tracker;

public class ValidateInput implements Input {
    private Output out;
    private Input in;

    public ValidateInput(Output out, Input in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public String askStr(String question) {
        return in.askStr(question);
    }

    @Override
    public int askInt(String question) {
        boolean valid = false;
        int value = -1;
        do {
            try {
                value = in.askInt(question);
                valid = true;
            } catch (NumberFormatException e) {
                out.println("Please enter validate data again.");
            }
        } while (!valid);
        return value;
    }
}
