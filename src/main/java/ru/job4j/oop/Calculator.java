package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int y) {
        return y - x;
    }

    public static void main(String[] args) {
        sum(10);
        minus(5);
        Calculator calculator = new Calculator();
        calculator.multiply(3);
        calculator.divide(2);
        calculator.sumAllOperation(1);
    }

    public int multiply(int y) {
        return x * y;
    }

    public double divide(int y) {
        return (double) y / x;
    }

    public double sumAllOperation(int y) {
        double sum = sum(y);
        sum += minus(y);
        sum += multiply(y);
        sum += divide(y);
        return sum;
    }
}
