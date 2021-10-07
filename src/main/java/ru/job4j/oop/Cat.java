package ru.job4j.oop;

public class Cat {

    public static void main(String[] args) {
        Cat peppy = new Cat();
        String say = peppy.sound();
        System.out.println("Peppy says " + say);
    }

    public String sound() {
        String voice = "may-may";
        return voice;
    }
}
