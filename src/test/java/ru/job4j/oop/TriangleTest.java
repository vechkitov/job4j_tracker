package ru.job4j.oop;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

public class TriangleTest {

    @Test
    public void whenAreaIs8() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        double area = triangle.area();
        assertThat(area, closeTo(8, 0.001));
    }

    @Test
    public void whenAreaIs2() {
        Point a = new Point(-1, -1);
        Point b = new Point(-1, 1);
        Point c = new Point(-3, -1);
        Triangle triangle = new Triangle(a, b, c);
        double area = triangle.area();
        assertThat(area, closeTo(2, 0.001));
    }

    @Test
    public void whenTriangleIsNotExistThenMinus1() {
        Point a = new Point(0, 0);
        Point b = new Point(10, 0);
        Point c = new Point(5, 0);
        Triangle triangle = new Triangle(a, b, c);
        double area = triangle.area();
        assertThat(area, closeTo(-1, 0.001));
    }
}
