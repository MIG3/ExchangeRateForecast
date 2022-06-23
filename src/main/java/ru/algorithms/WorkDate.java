package ru.algorithms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WorkDate
{
    public int countDays(int period, String oldDate) throws ParseException
    {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String curDate = dateFormat.format(date);
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
}
