package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Comparing strings should not be null, "
                    + "but was: left = " + left + ", right = " + right);
        }
        String leftNum = left.split(". ")[0];
        String rightNum = right.split(". ")[0];
        return Integer.compare(Integer.parseInt(leftNum), Integer.parseInt(rightNum));
    }
}
