package ru.job4j.poly;

public class Plane implements Vehicle {

    @Override
    public void move() {
        System.out.println("Самолет полетел");
    }

    @Override
    public boolean repair() {
        System.out.println("Замена кресла в самолете. Кресло есть в наличии");
        return true;
    }
}
