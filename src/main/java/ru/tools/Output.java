package ru.tools;

import java.text.DecimalFormat;

public class Output
{
    /**
     * Метод печатает результат в консоль, округлённый до двух знаков после запятой
     * @param averageRate - среднее значение курсов
     */
    public static void printToConsole(double averageRate, String date, int numOfDay)
    {
        String days = "";
        DecimalFormat df = new DecimalFormat("#.##");
        if (numOfDay == 1)
            days = "Пн";
        else if (numOfDay == 2)
            days = "Вт";
        else if (numOfDay == 3)
            days = "Ср";
        else if (numOfDay == 4)
            days = "Чт";
        else if (numOfDay == 5)
            days = "Пт";
        else if (numOfDay == 6)
            days = "Сб";
        else if (numOfDay == 7)
            days = "Вс";
        System.out.println(days + " " + date + " - " + df.format(averageRate));

    }
}
