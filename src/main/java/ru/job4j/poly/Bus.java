package ru.job4j.poly;

public class Bus implements Transport {

    private boolean isDriving;
    private int numOfPassengers;
    private double fuelQuantity;

    @Override
    public void drive() {
        isDriving = true;
    }

    @Override
    public void takePassengers(int amount) {
        numOfPassengers += amount;
    }

    @Override
    public double refuel(double quantity) {
        fuelQuantity += quantity;
        double price = 50 + Math.random();
        return price;
    }
}
