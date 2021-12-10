package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    private final String slash = "/";

    @Override
    public int compare(String o1, String o2) {
        checkArgs(o1, o2);
        String[] strArr1 = o1.split(slash, 2);
        String[] strArr2 = o2.split(slash, 2);
        int rsl = strArr2[0].compareTo(strArr1[0]);
        return rsl == 0 ? o1.compareTo(o2) : rsl;
    }

    private void checkArgs(String o1, String o2) {
        if (o1 == null || o2 == null
                || o1.isBlank() || o2.isBlank()
                || slash.equals(o1) || slash.equals(o2)
        ) {
            throw new IllegalArgumentException(String.format("o1 = %s; o2 = %s", o1, o2));
        }
    }
}
