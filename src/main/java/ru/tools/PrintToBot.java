package ru.tools;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PrintToBot
{
    public String print(List<Map<LocalDate, Double>> listForecast, DecimalFormat DEC_FORMAT)
    {
        String response = "";
        for (Map<LocalDate, Double> localDateDoubleMap : listForecast)
        {
            for (Map.Entry<LocalDate, Double> item : localDateDoubleMap.entrySet())
            {
                response += item.getKey().format(DateTimeFormatter.ofPattern(
                        "EEE - dd.MM.yyyy", Locale.getDefault()))
                        + " - "
                        + DEC_FORMAT.format(item.getValue()) + "\n";
            }
            response += "-------------------------------\n";
        }
        return response;
    }
}
