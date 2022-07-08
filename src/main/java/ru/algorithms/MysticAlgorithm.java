package ru.algorithms;

import java.util.List;
import java.util.Random;

public class MysticAlgorithm implements IPrognosis
{
    /**
     * Алгоритм прогнозирования "Мистический" - возвращает случайный курс за последние 30 дней
     *
     * @param courses - курсы за 30 дней
     * @return - случайный курс
     */
    public double mystic(List<Double> courses)
    {
        Random random = new Random();
        return courses.get(random.nextInt(courses.size()));
    }
}
