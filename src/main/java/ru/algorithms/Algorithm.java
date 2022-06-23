package ru.algorithms;

import ru.tools.Output;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ru.entity.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Algorithm
{

    public static void general(List<CourseData> courseDataList, String flag, int period) throws ParseException
    {
        Prognosis pr = new Prognosis();
        Output write = new Output();
        WorkDate differenceDate = new WorkDate();

        double average = 0.0;
        String oldD = courseDataList.get(1).Data;

        differenceDate.countDays(period, oldD);

        List<Double> courses= new ArrayList<Double>();
        for (int i = 1; i < 8; i++)
        {
            courses.add(Double.parseDouble(courseDataList.get(i).Curs.replaceAll(",", ".")));
        }

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
                write.printToConsole(rate);
            }
        }
        else
        {
            average = pr.average(courses);
            write.printToConsole(average);
        }
    }
}
