package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class JobAscByNameTest {

    @Test
    public void whenFirstGreaterThanSecond() {
        int rsl = new JobAscByName().compare(
                new Job("job2", 1),
                new Job("job1", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenFirstLessThanSecond() {
        int rsl = new JobAscByName().compare(
                new Job("job1", 1),
                new Job("job2", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenFirstEqualsToSecond() {
        int rsl = new JobAscByName().compare(
                new Job("job1", 2),
                new Job("job1", 3)
        );
        assertThat(rsl, equalTo(0));
    }
}
