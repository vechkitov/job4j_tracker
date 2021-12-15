package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FuncComputationTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = new FuncComputation().diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11d, 13d, 15d);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        List<Double> result = new FuncComputation().diapason(2, 5, x -> x * x + 2);
        List<Double> expected = Arrays.asList(6d, 11d, 18d);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentialFunctionThenExponentialResults() {
        List<Double> result = new FuncComputation().diapason(1, 4, x -> Math.pow(3, x));
        List<Double> expected = Arrays.asList(3d, 9d, 27d);
        assertThat(result, is(expected));
    }
}
