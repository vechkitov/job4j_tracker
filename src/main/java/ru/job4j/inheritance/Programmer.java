package ru.job4j.inheritance;

import java.util.Date;

public class Programmer extends Engineer {

    private String mainProgLang;

    public Programmer(String name, String surname, String education, Date birthday,
                      WorkingConditions workingConditions, String mainProgLang) {
        super(name, surname, education, birthday, workingConditions);
        this.mainProgLang = mainProgLang;
    }

    public String getMainProgLang() {
        return mainProgLang;
    }

    public Program program() {
        return null;
    }
}
