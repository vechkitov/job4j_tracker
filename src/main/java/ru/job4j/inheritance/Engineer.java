package ru.job4j.inheritance;

import java.util.Date;

public class Engineer extends Profession {

    private WorkingConditions workingConditions;

    public Engineer(String name, String surname, String education, Date birthday,
                    WorkingConditions workingConditions) {
        super(name, surname, education, birthday);
        this.workingConditions = workingConditions;
    }

    public WorkingConditions getWorkingConditions() {
        return null;
    }

    public Project makeProject() {
        return null;
    }
}
