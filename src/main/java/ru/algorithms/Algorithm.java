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

        if (flag.equals("week"))
        {
            for (int i = 0 ; i < period; i++)
            {
                courses.add(pr.average(courses));
                courses.remove(0);
            }
        }
        average = pr.average(courses);
        write.printToConsole(average);
    }
}
