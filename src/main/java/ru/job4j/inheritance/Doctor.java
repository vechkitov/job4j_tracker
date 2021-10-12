package ru.job4j.inheritance;

import java.util.Date;

public class Doctor extends Profession {

    public Doctor(String name, String surname, String education, Date birthday) {
        super(name, surname, education, birthday);
    }

    public Diagnosis heal(Pacient pacient) {
        return null;
    }
}
