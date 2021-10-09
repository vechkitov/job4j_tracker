package ru.job4j.oop;

public class Ball {

    public void tryRun(boolean condition) {
        String message = condition ? "Колобок съеден." : "Колобок сбежал.";
        System.out.println(message);
    }
}
