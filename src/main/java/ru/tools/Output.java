package ru.tools;

import java.text.DecimalFormat;

public class Output
{
    /**
     * Метод печатает результат в консоль, округлённый до двух знаков после запятой
     * @param averageRate - среднее значение курсов
     */
    public static void printToConsole(double averageRate)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(df.format(averageRate));
    }
}
