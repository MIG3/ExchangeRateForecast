package ru.algorithms;

import ru.entity.CourseData;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Prognosis
{
    /**
     * Алгоритм прогнозирования - среднее арифметическое значение на основании 7 последних курсов
     *
     * @param courses - курсы из входных данных
     * @return - среднее значение
     */
    public double average(List<Double> courses)
    {
        return courses.stream()
                .mapToDouble(a -> a)
                .average().orElse(0);
    }

    /**
     * Алгоритм прогнозирования "Прошлогодний" - возвращает курс за дату равную текущей, но год наза
     *
     * @param courseDataList - исходные данные
     * @param lastYearDate   дата в прошлом году
     * @return - курс годом ранее
     */
    public double lastYear(List<CourseData> courseDataList, LocalDate lastYearDate)
    {
        double rates = 0;
        for (int i = 1; i < courseDataList.size(); i++)
        {
            if (courseDataList.get(i).getData() == lastYearDate)
                rates = courseDataList.get(i).getCurs();
            else
                // проверить, что данный подход будет корректно работать, если дата в прошлом годы отсутствует
                rates = courseDataList.get(i - 1).getCurs();
        }
        return rates;
    }

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
