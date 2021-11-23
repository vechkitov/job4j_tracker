package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OrderConvertTest {

    @Test
    public void whenSingleOrder() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Dress"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("3sfe"), is(new Order("3sfe", "Dress")));
    }

    @Test
    public void whenMultipleOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Dress"));
        orders.add(new Order("34kj", "Hat"));
        orders.add(new Order("pehz", "Shoe"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.size(), is(3));
    }

    @Test
    public void whenDuplicatesOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Dress"));
        orders.add(new Order("3sfe", "Dress"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.size(), is(1));
    }

    @Test
    public void whenNoOrders() {
        List<Order> orders = new ArrayList<>();
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.size(), is(0));
    }
}
