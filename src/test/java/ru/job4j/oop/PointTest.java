package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void when00to20then2() {
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        double expected = 2;
        double actual = a.distance(b);
        double delta = 0.01;
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void whenXMinus1Y1ToX2YMinus2Then4Dot24() {
        Point a = new Point(-1, 1);
        Point b = new Point(2, -2);
        double expected = 4.24;
        double actual = a.distance(b);
        double delta = 0.01;
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void whenX10Y3ToXMinus4YMinus6Then16Dot64() {
        Point a = new Point(10, 3);
        Point b = new Point(-4, -6);
        double expected = 16.64;
        double actual = a.distance(b);
        double delta = 0.01;
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void whenXMinus1Y2ToX3Y2Then4() {
        Point a = new Point(-1, 2);
        Point b = new Point(3, 2);
        double expected = 4;
        double actual = a.distance(b);
        double delta = 0.01;
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void whenX4Y3Z2ToX2Y1ZMinus4Then6Dot63() {
        Point a = new Point(4, 3, 2);
        Point b = new Point(2, 1, -4);
        double expected = 6.63;
        double actual = a.distance3d(b);
        double delta = 0.01;
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void whenXMinus1YMinus1ZMinus1ToX3Y3Z3Then6Dot93() {
        Point a = new Point(-1, -1, -1);
        Point b = new Point(3, 3, 3);
        double expected = 6.93;
        double actual = a.distance3d(b);
        double delta = 0.01;
        Assert.assertEquals(expected, actual, delta);
    }
}
