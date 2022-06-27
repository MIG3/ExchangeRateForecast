package ru.tools;
import ru.entity.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


public class Parsing
{
    public static int period = 0;
    public static String currency;

    /** Парсинг входной команды
     *
     * @param command - строка с командой
     * @throws Exception - описание ошибки
     */
    public static void parsingCommand(String command) throws Exception
    {
        command = command.trim();
        String[] arr = command.split("\\s+");

        for (int i = 0; i < arr.length; i++)
        {
            String dataToLowerCase = arr[i].toLowerCase();
            if (dataToLowerCase.equals("usd") || dataToLowerCase.equals("доллара"))
            {
                currency = "usd";
            } else if (dataToLowerCase.equals("eur") || dataToLowerCase.equals("евро"))
            {
                currency = "eur";
            } else if (dataToLowerCase.equals("try") || dataToLowerCase.equals("турецкой лиры"))
            {
                currency = "try";
            }
            if (dataToLowerCase.equals("завтра") || dataToLowerCase.equals("tomorrow"))
            {
                period = 1;
            } else if (dataToLowerCase.equals("дней") || dataToLowerCase.equals("days"))
            {
                if (Integer.parseInt(arr[i - 1]) > 7 || Integer.parseInt(arr[i - 1]) < 1)
                    throw new Exception("Количество дней должно быть не меньше 1 и не больше 7");
                period = Integer.parseInt(arr[i - 1]);
            } else if (dataToLowerCase.equals("week"))
            {
                period = 7;
            }
        }
    }

    /**
     * Парсинг входных данных из файлов в поля класса CourseData
     * @param filePath - путь к файлу
     * @return - список со всеми значениями
     * @throws IOException - стек ошибки
     */
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
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return courseDataList;
    }

}
