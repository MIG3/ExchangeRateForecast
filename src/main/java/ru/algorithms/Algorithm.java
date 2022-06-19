package ru.algorithms;

import ru.tools.Output;
import java.util.List;

public class Algorithm
{
    public static void general(List<Double> courses, String flag, int period)
    {
        Prognosis pr = new Prognosis();
        Output write = new Output();
        double average = 0.0;

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
