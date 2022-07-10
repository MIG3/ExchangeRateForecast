package ru.algorithms;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AlgorithmTest extends TestCase
{
    AverageAlgorithm algorithm = new AverageAlgorithm();
    List<Double> courses = new ArrayList<>();

    @Test
    public void usdTomorrowAverageAlgSeven()
    {
        courses.add(61.1094);
        courses.add(60.9565);
        courses.add(60.2282);
        courses.add(58.3895);
        courses.add(57.778);
        courses.add(57.0926);
        courses.add(56.6624);
        assertThat(algorithm.average(courses)).isEqualTo(58.88808571428571);
    }

    @Test
    public void usdTomorrowAverageAlgNull()
    {
        assertThat(algorithm.average(courses)).isEqualTo(0.0);
    }

}