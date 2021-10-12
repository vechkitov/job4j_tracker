package ru.job4j.inheritance;

import java.util.Date;

public class Builder extends Engineer {

    private String typeBuild;

    public Builder(String name, String surname, String education, Date birthday,
                   WorkingConditions workingConditions, String typeBuild) {
        super(name, surname, education, birthday, workingConditions);
        this.typeBuild = typeBuild;
    }

    public String getTypeBuild() {
        return typeBuild;
    }

    public Building build() {
        return null;
    }
}
