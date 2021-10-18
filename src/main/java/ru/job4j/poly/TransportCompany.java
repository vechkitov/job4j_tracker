package ru.job4j.poly;

public class TransportCompany {

    public static void main(String[] args) {
        Vehicle[] vehicles = {
                new IntercityBus(),
                new Plane(),
                new IntercityBus(),
                new Train(),
                new Train(),
                new Plane(),
                new IntercityBus()
        };
        for (Vehicle vehicle : vehicles) {
            boolean isReady = vehicle.repair();
            if (isReady) {
                vehicle.move();
            }
        }
    }
}
