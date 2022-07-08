package ru.algorithms;

import ru.entity.CourseData;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class LastYearAlgorithm implements IPrognosis
{
    /**
     * Алгоритм прогнозирования - среднее арифметическое значение на основании 7 последних курсов
     *
     * @param courses - курсы из входных данных
     * @return - среднее значение
     */
    /*public double average(List<Double> courses)
    {
        return courses.stream()
                .mapToDouble(a -> a)
                .average().orElse(0);
    }*/

    /**
     * Алгоритм прогнозирования "Прошлогодний" - возвращает пару:курс за дату равную текущей, но год назад, и дату прогноза.
     * Если дата из прошлого года меньше последней в файлах данных, то выполняется поиск нужного курса в источнике
     * Иначе, в map пишутся новые курс-дата, начиная с последней из файла до прошлогодней даты. Возвращается последнее значения курса в словаре.
     *
     * @param courseDataList - исходные данные
     * @param dateForPrediction - дата для прогноза
     * @return - курс годом ранее от заданной даты и дата прогноза
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
        double curs = 0;
        if (dateForPrediction.isBefore(LocalDate.now()))
            throw new RuntimeException("Дата должна быть больше текущей!");
        else
        {
            if (lyd.isBefore(lastDateSource) || lyd.equals(lastDateSource))
            {
                curs = courseLastYearT(courseDataList, lyd);
                coursesAndDate.put(dateForPrediction, curs);
            }
            else
            {
                coursesAndDate.put(courseDataList.get(1).getData(), courseDataList.get(1).getCurs());
                // количество дней между последней в исходных данных и датой, что на год меньше искомой
                int countDays = Period.between(lastDateSource, lyd).getDays();

                for (int i = 0; i < countDays; i++)
                {
                    if (lyd.equals(courseDataList.get(i).getData()))
                    {
                        coursesAndDate.put(dateForPrediction, coursesAndDate.get(startDate));
                        break;
                    }
                    startDate = startDate.plusDays(1);
                    curs = courseLastYearT(courseDataList, startDate.minusYears(1));
                    coursesAndDate.put(startDate, curs);
                }
                // удаляем все значения, кроме последнего из словаря, дату меняем на прогнозируемую
                Map<LocalDate, Double> temp = new HashMap<LocalDate, Double>();
                Map.Entry actualValue = coursesAndDate.entrySet()
                        .stream()
                        .findFirst()
                        .get();
                temp.put(dateForPrediction,(Double)actualValue.getValue());
                coursesAndDate.clear();
                coursesAndDate = temp;
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
    /*public double mystic(List<Double> courses)
    {
        Random random = new Random();
        return courses.get(random.nextInt(courses.size()));
    }*/

    /**
     * Ищет курс из тех, что есть в файлах данных
     * @param courseDataList - список с данными
     * @param lyd - дата на год меньше той, на которую выполняется прогноз
     * @return - курс из предыдущего года
     */
    private double courseLastYearT(List<CourseData> courseDataList, LocalDate lyd)
    {
        LocalDate lydMinusD = lyd.minusDays(2);
        double c = 0;
        for (int i = 1; i < courseDataList.size(); i++)
        {
            if (lyd.equals(courseDataList.get(i).getData()) || lydMinusD.equals(courseDataList.get(i).getData()))
            {
                c = courseDataList.get(i).getCurs();
            }
        }
        return c;
    }

}
