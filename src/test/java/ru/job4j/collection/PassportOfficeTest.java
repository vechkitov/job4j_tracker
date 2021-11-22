package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PassportOfficeTest {

    @Test
    public void addWhenCitizenExists() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        boolean rsl = office.add(citizen);
        assertTrue(rsl);
        assertThat(office.get(citizen.getPassport()), is(citizen));
    }

    @Test
    public void addWhenCitizenDoNotExists() {
        String passport = "2f44a";
        Citizen citizen1 = new Citizen(passport, "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen1);
        Citizen citizen2 = new Citizen(passport, "Petr Arsentev Sergeevich");
        boolean rsl = office.add(citizen2);
        assertFalse(rsl);
        assertThat(office.get(passport), is(citizen1));
    }

    @Test
    public void getWhenCitizenExists() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertThat(office.get("2f44a"), is(citizen));
    }

    @Test
    public void getWhenCitizenDoNotExists() {
        PassportOffice office = new PassportOffice();
        assertNull(office.get("2f44a"));
    }
}
