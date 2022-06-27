package ru.tools;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Output
{
    /**
     * Метод печатает результат в консоль, округлённый до двух знаков после запятой
     * @param averageRate - среднее значение курсов
     */
    public void printToConsole(double averageRate, LocalDate date)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(date.format(DateTimeFormatter.ofPattern("EEE - dd.MM.yyyy", Locale.getDefault())) + " - " + df.format(averageRate));
    }
}
