package ru.job4j.inheritance;

import java.util.Date;

public class Surgeon extends Doctor {

    public Surgeon(String name, String surname, String education, Date birthday) {
        super(name, surname, education, birthday);
    }

    public boolean doSurgery(Pacient pacient) {
        return false;
    }
}
