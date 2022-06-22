package ru.algorithms;

import ru.tools.Output;

import java.util.ArrayList;
import java.util.List;
import ru.entity.*;

public class Algorithm
{
    public static void general(List<CourseData> courseDataList, String flag, int period)
    {
        Prognosis pr = new Prognosis();
        Output write = new Output();
        double average = 0.0;

        List<Double> courses= new ArrayList<Double>();
        for (int i = 1; i < 8; i++)
        {
            courses.add(Double.parseDouble(courseDataList.get(i).Curs.replaceAll(",",".")));
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
//            System.out.println("Если нужен прогноз на несколько дней, период должен быть > 0, если на завтра - период должен быть = 1");

    }
}
