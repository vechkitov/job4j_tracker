package ru.job4j.ex;

public class FindEl {

    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        if (key == null) {
            throw new IllegalArgumentException("key should not be null");
        }
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (key.equals(value[i])) {
                rsl = i;
                break;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("Element '" + key + "' is not found");
        }
        return rsl;
    }

    public static void main(String[] args) {
        String[] keys = {"one", "two", "three"};
        try {
            FindEl.indexOf(keys, "four");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
