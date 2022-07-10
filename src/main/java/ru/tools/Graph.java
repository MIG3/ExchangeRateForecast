package ru.tools;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Graph
{
    public void diagram(List<List<Double>> courses, int period) throws PythonExecutionException, IOException
    {
        Plot plt = Plot.create();
        String[] colors = {"r", "g", "b", "y", "o"};

        int i = 0;
        while ( i <courses.size())
        {
            plt.plot().add(courses.get(i)).color(colors[i]);
            i++;
        }
        plt.xlim(0, period);
        plt.title("Динамика курсов валют");
        plt.xlabel("Даты");
        plt.ylabel("Курсы влют");
        plt.savefig("./src/main/diagram/diagram.png").dpi(200);
        //plt.show();
    }
}
