package ru.algorithms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.time.DateUtils.addDays;

public class WorkDate
{
    /**
     * Метод, который считает количество дней, на которые отличаются даты входных данных и текущей.
     * @param period - количество дней для прогноза
     * @param oldDate - самая свежая дата во входном файле
     * @param curDate - текущая дата (сегодняшняя)
     * @param dateFormat - формат даты
     * @return - интервал разницы между датами во входном файле и текущей
     * @throws ParseException
     */
    public int countDays(int period, String oldDate, String curDate, DateFormat dateFormat) throws ParseException
    {
        if (oldDate.equals(curDate))
            return 0;
        else
        {
            Date cd = dateFormat.parse(curDate);
            Date od = dateFormat.parse(oldDate);
            long diffInMillies = Math.abs(cd.getTime() - od.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            period += diff;
            return period;
        }
    }

    /**
     * Метод, который увеличивает переданную дату на 1 день
     * @param date - дата
     * @return
     */
    public Date addOneDay(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date newDate = addDays(date, 1);
        return newDate;
    }

    /**
     * Метод, который возвращает номер дня недели для переданной даты на вход
     * @param date - дата
     * @return
     */
    public int getDayNumberNew(LocalDate date)
    {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }

}
