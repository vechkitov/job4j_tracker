package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class JobAscByPriorityTest {

    @Test
    public void whenFirstGreaterThanSecond() {
        int rsl = new JobAscByPriority().compare(
                new Job("job1", 3),
                new Job("job2", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenFirstLessThanSecond() {
        int rsl = new JobAscByPriority().compare(
                new Job("job1", 1),
                new Job("job2", 2)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenFirstEqualsToSecond() {
        int rsl = new JobAscByPriority().compare(
                new Job("job1", 2),
                new Job("job2", 2)
        );
        assertThat(rsl, equalTo(0));
    }
}
