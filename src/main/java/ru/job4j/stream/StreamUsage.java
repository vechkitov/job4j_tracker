package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {

    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, -2, 3, 4, -5);
        List<Integer> rsl = ints.stream()
                .filter(i -> i > 0)
                .collect(Collectors.toList());
        rsl.forEach(System.out::println);
    }
}
