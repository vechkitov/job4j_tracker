package ru.job4j.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class College {

    public static void main(String[] args) {
        Student ivan = new Student();
        ivan.setFio("Иванов Иван Иванович");
        ivan.setGroup("MT-00");
        ivan.setAdmissionDate(LocalDate.of(2000, 7, 1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String info = "Студент " + ivan.getFio() + " обучается в группе \"" + ivan.getGroup()
                + "\". Дата поступления: " + ivan.getAdmissionDate().format(formatter) + "г.";
        System.out.println(info);
    }
}
