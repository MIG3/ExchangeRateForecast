package ru.algorithms;

import java.util.List;

public class Prognosis
{
    public static double average(List<Double> courses)
    {
        return courses.stream()
                .mapToDouble(a -> a)
                .average().orElse(0);
    }
}
