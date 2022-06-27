package ru.algorithms;

import ru.tools.Output;

import java.time.LocalDate;
import java.util.*;

import ru.entity.*;


public class Algorithm
{
    /**
     * Основной метод, в котором реализована вся логика.
     * Считается интервал, на который надо посчитать курсы и складывается с периодом из входных данных.
     * Считаются курсы в зависимости от размера периода и равенства/неравенства текущей даты и самой свежей из входных данных.
     * @param courseDataList - список с значениями, в которые входят: курсы, даты, номинал и валюта
     * @param period - количество дней для прогноза
     */
    public void general(List<CourseData> courseDataList, int period)
    {
        Prognosis pr = new Prognosis();
        Output write = new Output();
        WorkDate differenceDate = new WorkDate();

        double average = 0.0;
        LocalDate oldD = courseDataList.get(0).getData();
        LocalDate curDate = LocalDate.now();
        LocalDate startDate;
        LocalDate nextDate;
        int interval, sum;


        interval = differenceDate.countDays(period, oldD, curDate);
        sum = period + interval;

        List<Double> courses= new ArrayList<Double>();
        for (int i = 1; i < 8; i++)
        {
            courses.add(courseDataList.get(i).getCurs());
        }
        Collections.reverse(courses); // чтобы список начинался с самых старых значений. Пригодится позже

        // Период > 1:
        // 1. Даты равны -> на неделю от текущей даты
        // 2. Даты не равны:
        //   а. на завтра + период между датами
        //   б. на неделю + период между датами
        if (sum > 1)
        {
            // считаю среднее на каждый следующий день
            for (int i = 0; i < sum; i++)
            {
                courses.add(pr.average(courses));
                courses.remove(0);
            }

            startDate = LocalDate.now();
            // если нужен курс на следующий день, получаю последний элемент списка
            if (period == 1)
            {
                nextDate = differenceDate.addOneDay(startDate);
                write.printToConsole(courses.get(courses.size() - 1), nextDate);
            }
            else
            {
                for (int i = 0; i < period; i++)
                {
                    startDate = differenceDate.addOneDay(startDate);
                    write.printToConsole(courses.get(i), startDate);
                }
            }
        }
        // Период == 1:
        // Даты равны -> на следующий день от текущей даты
        else
        {
            nextDate = LocalDate.now();
            average = pr.average(courses);
            write.printToConsole(average, nextDate);
        }
    }
}
