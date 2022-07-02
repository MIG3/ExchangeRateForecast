package ru.tools;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

    public void graph(Map<LocalDate, Double> forecast)
    {

    }
}
