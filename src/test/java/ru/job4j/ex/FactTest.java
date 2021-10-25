package ru.job4j.ex;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactTest {

    @Test()
    public void whenCalc4Then24() {
        int expected = 24;
        Fact f = new Fact();
        int actual = f.calc(4);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgumentLessThan0() {
        new Fact().calc(-1);
    }
}
