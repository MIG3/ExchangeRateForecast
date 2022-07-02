package ru.algorithms;

import ru.entity.CourseData;
import ru.tools.Parsing;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

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
     * @param dateForPrediction - дата для прогноза
     * @return - курс годом ранее от заданной даты
     */
    public Map<LocalDate, Double> courseLastYear(List<CourseData> courseDataList, LocalDate dateForPrediction)
    {
        LocalDate lyd = dateForPrediction.minusYears(1);
        LocalDate lastDateSource = courseDataList.get(1).getData();
        LocalDate startDate = lastDateSource;
        LocalDate nextDate;
        List<Double> courses = new ArrayList<Double>();
        Map<LocalDate, Double> coursesAndDate = new HashMap<LocalDate, Double>();
        double rates = 0;

        if (dateForPrediction.isBefore(LocalDate.now()))
            throw new RuntimeException("Дата должна быть больше текущей!");
        else
        {
            // эта часть работает
            if (lyd.isBefore(lastDateSource) || lyd.equals(lastDateSource))
            {
                for (int i = 1; i < courseDataList.size(); i++)
                {
                    if (lyd.equals(lastDateSource))
                    {
                        coursesAndDate.put(dateForPrediction,courseDataList.get(i).getCurs());
                        break;
                    }
                }
            }
            else
            {
                courses.add(courseDataList.get(1).getCurs());
                // количество дней между последней в исходных данных и датой, что на год меньше искомой
                int countDays = Period.between(lastDateSource, lyd).getDays();
                for (int i = 1; i < countDays + 1; i++)
                {
                    startDate = startDate.plusDays(1);
                    courses.add(courseDataList.get(i).getCurs());
                }
            }
        }
        return coursesAndDate;
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
