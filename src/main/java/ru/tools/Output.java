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
     * @param averageRate - среднее значение курсов
     */
    public void printToConsole(double averageRate, LocalDate date)
    {
        //DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(date.format(DateTimeFormatter.ofPattern("EEE - dd.MM.yyyy", Locale.getDefault())) + " - " + DEC_FORMAT.format(averageRate));
    }

}
