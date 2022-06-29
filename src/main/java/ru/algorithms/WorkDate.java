package ru.algorithms;

import java.time.LocalDate;
import java.time.Period;
public class WorkDate
{
    /**
     * Метод, который считает количество дней, на которые отличаются даты входных данных и текущей.
     * @param period - количество дней для прогноза
     * @param oldDate - самая свежая дата во входном файле
     * @param curDate - текущая дата (сегодняшняя)
     * @return - интервал разницы между датами во входном файле и текущей
     */
    public int countDays(int period, LocalDate oldDate, LocalDate curDate)
    {
        if (oldDate.equals(curDate))
            return 0;
        else
        {
            Period diff = Period.between(curDate, oldDate);
            return Math.abs(diff.getDays());
        }
    }

    /**
     * Метод, который увеличивает переданную дату на 1 день
     * @param date - сегодня
     * @return - завтра
     */
    public LocalDate addOneDay(LocalDate date)
    {
        return date.plusDays(1);
    }

    /**
     * Метод, получающий текущую дату, которая была в прошлом году
     * @param date - текущая дата
     * @return - прошлогодняя дата
     */
    public LocalDate lastYear(LocalDate date)
    {
        return date.minusYears(1);
    }
}
