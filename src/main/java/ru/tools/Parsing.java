package ru.tools;
import ru.entity.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


public class Parsing
{
    public static String flag;
    public static int period = 0;
    public static String currency;

    public static void parsingCommand(String command)
    {
//        throw new UnsupportedOperationException("Парсинг входных команд.");
        command = command.trim();
        String[] arr = command.split(" ");
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i].equals("USD") || arr[i].equals("доллара"))
            {
                currency = "usd";
            }
            else if (arr[i].equals("EUR") || arr[i].equals("евро"))
            {
                currency = "eur";
            }
            else if (arr[i].equals("TRY") || arr[i].equals("турецкой лиры"))
            {
                currency = "try";
            }
            if (arr[i].equals("завтра") || arr[i].equals("tomorrow"))
            {
                flag = "next";
                period = 1;
            }
            else if (arr[i].equals("дней") || arr[i].equals("days"))
            {
                flag = "period";
                period = Integer.parseInt(arr[i-1]);
            }

        }
    }

    public static List<CourseData> parsingFile(String filePath) throws IOException
    {
        List<CourseData> courseDataList = new ArrayList<CourseData>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath), Charset.defaultCharset()))
        {
            // чтение csv файла. Через newFormat указывается не стандартный разделитель
            Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(reader);

            for (CSVRecord record : records)
            {
                CourseData data = new CourseData();
                data.Nominal = record.get(0);
                data.Data = record.get(1);
                data.Curs = record.get(2).replaceAll("^\"|\"$", "").trim();
                data.Cdx = record.get(3);
                courseDataList.add(data);
            }
/*            for (CourseData cd: courseDataList)
            {
                System.out.println(cd.Nominal);
                System.out.println(cd.Data);
            }*/
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return courseDataList;
    }

}
