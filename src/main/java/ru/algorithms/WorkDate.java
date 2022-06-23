package ru.algorithms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.time.DateUtils.addDays;

public class WorkDate
{
    public int countDays(int period, String oldDate, String curDate, DateFormat dateFormat) throws ParseException
    {
        if (oldDate.equals(curDate))
            return period;
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

    public Date addOneDay(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date newDate = addDays(date, 1);
        return newDate;
    }

    public int getDayNumberNew(LocalDate date)
    {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }

}
