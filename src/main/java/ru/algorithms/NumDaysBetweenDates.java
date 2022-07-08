package ru.algorithms;

import java.time.LocalDate;
import java.time.Period;
public class NumDaysBetweenDates
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
}
