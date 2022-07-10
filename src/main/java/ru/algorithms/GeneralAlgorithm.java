package ru.algorithms;

import ru.tools.*;

import java.time.LocalDate;
import java.util.*;

import ru.entity.*;


public class GeneralAlgorithm
{
    /**
     * Основной метод, в котором реализована вся логика.
     * Считается интервал, на который надо посчитать курсы и складывается с периодом из входных данных.
     * Считаются курсы в зависимости от размера периода и равенства/неравенства текущей даты и самой свежей из входных данных.
     * @param courseDataList - список с значениями, в которые входят: курсы, даты, номинал и валюта
     * @param period - количество дней для прогноза, которое задаётся в команде
     */
    public Map<LocalDate, Double> general(List<CourseData> courseDataList, int period, String algo, LocalDate futureDay)
    {
        Map<LocalDate, Double> forecast = new TreeMap<LocalDate, Double>();
        LastYearAlgorithm lastYearAlgorithm = new LastYearAlgorithm();
        MysticAlgorithm mysticAlgorithm = new MysticAlgorithm();
        AverageAlgorithm averageAlgorithm = new AverageAlgorithm();

        NumDaysBetweenDates differenceDate = new NumDaysBetweenDates();
        ParsingCommand pars = new ParsingCommand();

        double average = 0.0;
        LocalDate oldD = courseDataList.get(0).getData();
        LocalDate curDate = LocalDate.now();
        LocalDate startDate;
        LocalDate nextDate;
        int interval, sum;
        int countDayFromAlgo = 0;

        interval = differenceDate.countDays(oldD, curDate);
        //если необходимо указать курс на конкретную дату
        if (futureDay != null)
            period = differenceDate.countDays(curDate, futureDay);

        sum = period + interval;

        if (algo.equals("average"))
            countDayFromAlgo = 8;
        else if (algo.equals("mystic") || algo.equals("from_internet"))
            countDayFromAlgo = 31;

        List<Double> courses= new ArrayList<Double>();
        if (!algo.equals("last_year"))
        {
            // в этом цикле считаются те курсы, на основе которых потом считаются для периодов
            for (int i = 1; i < countDayFromAlgo; i++) // в этом цикле число, до которого бежит i - это то число на основе которого применяется алгоритм
            {
                courses.add(courseDataList.get(i).getCurs());
            }
            Collections.reverse(courses); // чтобы список начинался с самых старых значений. Пригодится позже

            double[] numOfDate = new double[courses.size()];
            for (int i = 0; i < numOfDate.length; i++)
            {
                numOfDate[i] = i+1;
            }

            // Период > 1:
            // 1. Даты равны -> на неделю от текущей даты
            // 2. Даты не равны:
            //   а. на завтра + период между датами
            //   б. на неделю + период между датами
            if (sum > 1)
            {
                // считаю значение курса по заданному алгоритму на каждый следующий день
                for (int i = 0; i < sum; i++)
                {
                    switch (algo)
                    {
                        case "average":
                            courses.add(averageAlgorithm.average(courses));
                            break;
                        case "mystic":
                            courses.add(mysticAlgorithm.mystic(courses));
                            break;
                        case "from_internet":
                            LinearRegression lr = new LinearRegression(numOfDate, courses);
                            double lrr = lr.predict(31);
                            courses.add(lrr);

                            break;
                    }
                    courses.remove(0);
                }

                startDate = LocalDate.now();
                // если нужен курс на следующий день, получаю последний элемент списка
                if (period == 1 || futureDay != null)
                {
                    if (futureDay != null)
                    {
                        nextDate = futureDay;
                    }
                    else
                        nextDate = startDate.plusDays(1);
                    forecast.put(nextDate, courses.get(courses.size() - 1));
                } else
                {
                    for (int i = 0; i < period; i++)
                    {
                        startDate = startDate.plusDays(1);
                        forecast.put(startDate, courses.get(i));
                    }
                }
            }
            // Период == 1:
            // Даты равны -> на следующий день от текущей даты
            else
            {
                nextDate = LocalDate.now().plusDays(1);
                switch (algo)
                {
                    case "average":
                        average = averageAlgorithm.average(courses);
                        break;
                    case "mystic":
                        average = mysticAlgorithm.mystic(courses);
                        break;
                    case "from_internet":
                        double[] dateFuture = new double[]{1};
                        LinearRegression lr = new LinearRegression(dateFuture, courses);
                        average = lr.predict(31);
                        break;
                }
                forecast.put(nextDate, average);
            }
        }
        else
        {
            assert futureDay != null;
            forecast = lastYearAlgorithm.courseLastYear(courseDataList, futureDay);
            Map.Entry actualValue = forecast.entrySet()
                    .stream()
                    .findFirst()
                    .orElseThrow();
        }
        return forecast;
    }
}
