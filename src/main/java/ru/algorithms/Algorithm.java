package ru.algorithms;

import ru.tools.Output;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import ru.entity.*;


public class Algorithm
{

    public static void general(List<CourseData> courseDataList, int period) throws ParseException
    {
        Prognosis pr = new Prognosis();
        Output write = new Output();
        WorkDate differenceDate = new WorkDate();

        double average = 0.0;
        String oldD = courseDataList.get(1).Data;
        int interval, sum;

        Date date = new Date();
        Date startDate;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String curDate = dateFormat.format(date);

        interval = differenceDate.countDays(period, oldD, curDate, dateFormat);
        sum = period + interval;

        List<Double> courses= new ArrayList<Double>();
        for (int i = 1; i < 8; i++)
        {
            courses.add(Double.parseDouble(courseDataList.get(i).Curs.replaceAll(",", ".")));
        }
        Collections.reverse(courses); // чтобы список начинался с самых старых значений. Пригодится позже

        String nextDate;

        // Период > 1:
        // 1. Даты равны -> на неделю от текущей даты
        // 2. Даты не равны:
        //   а. на завтра + период между датами
        //   б. на неделю + период между датами
        if (sum > 1)
        {
            startDate = dateFormat.parse(courseDataList.get(1).Data);
            // считаю среднее на каждый следующий день
            for (int i = 0; i < sum; i++)
            {
                courses.add(pr.average(courses));
                courses.remove(0);
            }

            startDate = new Date();
            // если нужен курс на следующий день, получаю последний элемент списка
            if (period == 1)
            {
                startDate = differenceDate.addOneDay(startDate);
                nextDate = dateFormat.format(startDate);
                int d = differenceDate.getDayNumberNew(LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault()));
                write.printToConsole(courses.get(courses.size() - 1), nextDate, d);
            }
            else
            {
                for (int i = 0; i < period; i++)
                //for (double rate : courses)
                {
                    startDate = differenceDate.addOneDay(startDate);
                    nextDate = dateFormat.format(startDate);
                    int d = differenceDate.getDayNumberNew(LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault()));
                    write.printToConsole(courses.get(i), nextDate, d);
                }
            }
        }
        // Период == 1:
        // Даты равны -> на следующий день от текущей даты
        else
        {
            startDate = new Date();
            average = pr.average(courses);
            nextDate = dateFormat.format(differenceDate.addOneDay(startDate));
            int d = differenceDate.getDayNumberNew(LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault()));
            write.printToConsole(average, nextDate, d);
        }
    }
}
