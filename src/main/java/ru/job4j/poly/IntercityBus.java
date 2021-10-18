package ru.job4j.poly;

public class IntercityBus implements Vehicle {

    @Override
    public void move() {
        System.out.println("Автобус поехал.");
    }

    @Override
    public boolean repair() {
        System.out.println("Замена коробки передач автобуса. Нет в наличии");
        return false;
    }
}
