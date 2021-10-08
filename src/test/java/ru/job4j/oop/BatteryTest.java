package ru.job4j.oop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BatteryTest {

    @Test
    public void whenExchangeThenSrcIs0() {
        Battery src = new Battery(50);
        Battery dest = new Battery(3);
        int expected = 0;
        src.exchange(dest);
        int actual = src.getLoad();
        assertEquals(expected, actual);
    }

    @Test
    public void whenExchangeThenDestIsIncreased() {
        Battery src = new Battery(50);
        Battery dest = new Battery(3);
        int expected = 53;
        src.exchange(dest);
        int actual = dest.getLoad();
        assertEquals(expected, actual);
    }
}
