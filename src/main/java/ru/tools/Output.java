package ru.tools;


import java.text.DecimalFormat;

public class Output
{
    /**
     * Метод печатает результат в консоль, округлённый до двух знаков после запятой
     * @param averageRate - среднее значение курсов
     * @param flag - указывает на какой период выдавать прогноз
     */
    public static void printToConsole(double averageRate, String flag)
    {
        if (flag.equals("next"))
        {
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println(df.format(averageRate));
        }
        else if (flag.equals("week"))
        {
            throw new UnsupportedOperationException("Вывод результата в консоль.");
        }
    }
}
