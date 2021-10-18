package ru.job4j.poly;

public class Train implements Vehicle {

    @Override
    public void move() {
        System.out.println("Поезд пошел");
    }

    @Override
    public boolean repair() {
        System.out.println("Ремонт парового котла паровоза. Запчасти есть в наличии");
        return true;
    }
}
