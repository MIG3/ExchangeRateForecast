package ru.algorithms;

import java.util.List;

public class Prognosis
{
    /**
     * Алгоритм прогнозирования - среднее арифметическое значение на основании 7 последних курсов
     * @param courses - курсы из входных данных
     * @return - среднее значение
     */
    public static double average(List<Double> courses)
    {
        return courses.stream()
                .mapToDouble(a -> a)
                .average().orElse(0);
    }
}
