package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {

    public static void main(String[] args) {
        Map<String, String> userEmails = new HashMap<>();
        userEmails.put("ivanov@mail.ru", "Иванов");
        userEmails.put("petrov@mail.ru", "Петров");
        userEmails.put("sidovov@mail.ru", "Сидоров");
        for (String email : userEmails.keySet()) {
            String user = userEmails.get(email);
            System.out.println(email + ": " + user);
        }
    }
}
