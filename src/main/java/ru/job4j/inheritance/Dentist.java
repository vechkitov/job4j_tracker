package ru.job4j.inheritance;

import java.util.Date;

public class Dentist extends Doctor {

    public Dentist(String name, String surname, String education, Date birthday) {
        super(name, surname, education, birthday);
    }

    public boolean treatTeeth() {
        return false;
    }
}
