package ru.job4j.oop;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    private int x;
    private int y;
    private int z;

    public Point(int first, int second) {
        this.x = first;
        this.y = second;
    }

    public Point(int x, int y, int z) {
        this(x, y);
        this.z = z;
    }

    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        double dist = a.distance(b);
        System.out.println(dist);
    }

    public double distance(Point that) {
        double result = sqrt(pow(this.x - that.x, 2) + pow(this.y - that.y, 2));
        return result;
    }

    public double distance3d(Point that) {
        double result = sqrt(pow(this.x - that.x, 2) + pow(this.y - that.y, 2)
                + pow(this.z - that.z, 2));
        return result;
    }
}
