package ru.tools;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Output
{
    final private DecimalFormat DEC_FORMAT = new DecimalFormat("#.##");
    /**
     * Метод печатает результат в консоль, округлённый до двух знаков после запятой
     * @param forecast - пары: дата и курс
     */
    public void printToConsole(Map<LocalDate, Double> forecast)
    {
        //DecimalFormat df = new DecimalFormat("#.##");
        for (Map.Entry<LocalDate, Double> item : forecast.entrySet())
        {
            System.out.println(item.getKey().format(DateTimeFormatter.ofPattern("EEE - dd.MM.yyyy", Locale.getDefault())) + " - " + DEC_FORMAT.format(item.getValue()));
        }
    }

    public void graph(Map<LocalDate, Double> forecast, String currency, int period) throws PythonExecutionException, IOException
    {
        //String currency = "usd";
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
