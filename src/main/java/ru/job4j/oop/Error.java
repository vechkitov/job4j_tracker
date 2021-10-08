package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public static void main(String[] args) {
        Error defErr = new Error();
        Error oomErr = new Error(true, 1, "Out of memory");
        Error noPaperErr = new Error(false, 22, "Нет бумаги в принтере");
        defErr.printInfo();
        oomErr.printInfo();
        noPaperErr.printInfo();
    }

    public void printInfo() {
        System.out.println("Используется?: " + active);
        System.out.println("Статус: " + status);
        System.out.println("Сообщение: " + message);
    }
}
