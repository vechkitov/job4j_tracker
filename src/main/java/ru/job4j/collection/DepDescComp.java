package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    private final String slash = "/";

    @Override
    public int compare(String o1, String o2) {
        checkArgs(o1, o2);
        String[] strArr1 = split(o1);
        String[] strArr2 = split(o2);
        int rsl = strArr2[0].compareTo(strArr1[0]);
        if (rsl == 0) {
            rsl = strArr1[1].compareTo(strArr2[1]);
        }
        return rsl;
    }

    private String[] split(String s) {
        String part1 = "";
        String part2 = "";
        int idx = s.indexOf(slash);
        if (idx == -1) {
            part1 = s;
        } else {
            part1 = s.substring(0, idx);
            part2 = s.endsWith(slash) ? "" : s.substring(idx + 1);
        }
        return new String[]{part1, part2};
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
