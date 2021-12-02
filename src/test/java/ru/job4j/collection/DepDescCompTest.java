package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class DepDescCompTest {

    @Test
    public void whenUpDepartmentGoAfter() {
        int rsl = new DepDescComp().compare(
                "K2/SK1/SSK2",
                "K2/SK1/SSK1"
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenUpDepartmentGoBefore() {
        int rsl = new DepDescComp().compare(
                "K2",
                "K2/SK1"
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenFirstGreatestDepartmentGoAfter() {
        int rsl = new DepDescComp().compare(
                "K2/SK1/SSK1",
                "K1/SK1/SSK1"
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenFirstGreatestDepartmentGoBefore() {
        int rsl = new DepDescComp().compare(
                "K1/SK1/SSK1",
                "K2/SK1/SSK1"
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArg1IsNull() {
        new DepDescComp().compare(null, "K2/SK1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArg2IsNull() {
        new DepDescComp().compare("K2/SK1", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArg1IsBlank() {
        new DepDescComp().compare(" ", "K2/SK1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArg2IsBlank() {
        new DepDescComp().compare("K2/SK1", " ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArg1EqualsSlash() {
        new DepDescComp().compare("/", "K2/SK1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArg2EqualsSlash() {
        new DepDescComp().compare("K2/SK1", "/");
    }
}
