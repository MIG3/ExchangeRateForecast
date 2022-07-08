package ru.tools;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Graph
{
    public void diagram(Map<LocalDate, Double> forecast, String currency, int period) throws PythonExecutionException, IOException
    {
        Plot plt = Plot.create();
        List<Double> courses = forecast.entrySet().parallelStream().collect(ArrayList::new,
                (list, element) -> list.add(element.getValue()), ArrayList::addAll);
        Collections.reverse(courses);

        if (currency.equals("usd"))
        {
            plt.plot().add(courses).color("g");
            plt.xlim(0, period);
            plt.title("Динамика курсов валют");
            plt.xlabel("Даты");
            plt.ylabel("Курсы влют");
            plt.savefig("histogram.png").dpi(200);
            plt.show();
        }
    }
}
