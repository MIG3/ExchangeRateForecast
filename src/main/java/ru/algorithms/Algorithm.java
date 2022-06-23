package ru.algorithms;

import ru.tools.Output;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ru.entity.*;


public class Algorithm
{

    public static void general(List<CourseData> courseDataList, String flag, int period) throws ParseException
    {
        Prognosis pr = new Prognosis();
        Output write = new Output();
        WorkDate differenceDate = new WorkDate();

        double average = 0.0;
        String oldD = courseDataList.get(1).Data;

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String curDate = dateFormat.format(date);

        differenceDate.countDays(period, oldD, curDate, dateFormat);

        List<Double> courses= new ArrayList<Double>();
        for (int i = 1; i < 8; i++)
        {
            courses.add(Double.parseDouble(courseDataList.get(i).Curs.replaceAll(",", ".")));
        }
        String nextDate;
        Date temp = date;
        if (period > 1)
        {

            if (flag.equals("period"))
            {
                for (int i = 0; i < period; i++)
                {
                    courses.add(pr.average(courses));
                    courses.remove(0);
                }
            }
            for (double rate: courses)
            {
                temp = differenceDate.addOneDay(temp);
                nextDate = dateFormat.format(temp);
                int d = differenceDate.getDayNumberNew(LocalDate.ofInstant(temp.toInstant(), ZoneId.systemDefault()));
                write.printToConsole(rate, nextDate, d);
            }
        }
        else
        {
            average = pr.average(courses);
            nextDate = dateFormat.format(differenceDate.addOneDay(temp));
            int d = differenceDate.getDayNumberNew(LocalDate.ofInstant(temp.toInstant(), ZoneId.systemDefault()));
            write.printToConsole(average, nextDate, d);
        }
    }
}
