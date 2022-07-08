package ru.tools;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Print
{
    final private DecimalFormat DEC_FORMAT = new DecimalFormat("#.##");
    /**
     * Метод печатает результат в консоль, округлённый до двух знаков после запятой
     * @param forecast - пары: дата и курс
     */
    public void printToConsole(Map<LocalDate, Double> forecast)
    {
        for (Map.Entry<LocalDate, Double> item : forecast.entrySet())
        {
            System.out.println(item.getKey().format(DateTimeFormatter.ofPattern("EEE - dd.MM.yyyy", Locale.getDefault())) + " - " + DEC_FORMAT.format(item.getValue()));
        }
    }
}
