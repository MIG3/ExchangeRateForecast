package ru.tools;
import ru.entity.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


public class Parsing
{
    public static int period = 0;
    public LocalDate date;
    public String algorithm;
    public boolean graph = false;
    public String filePath;

    public String readCommand()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите команду: ");
        return in.nextLine();
    }
    /** Парсинг входной команды
     *
     * @param command - строка с командой
     */
    public void parsingCommand(String command)
    {
        command = command.trim();
        String[] arr = command.split("\\s+");

        for (int i = 0; i < arr.length; i++)
        {
            String dataToLowerCase = arr[i].toLowerCase();
            if (dataToLowerCase.equals("usd") || dataToLowerCase.equals("доллара"))
                filePath = "src/main/resources/RC_F01_06_2002_T17_06_2022_USD.csv";
            else if (dataToLowerCase.equals("eur") || dataToLowerCase.equals("евро"))
                filePath = "src/main/resources/RC_F01_06_2002_T17_06_2022_EUR.csv";
            else if (dataToLowerCase.equals("try") || dataToLowerCase.equals("турецкой_лиры"))
                filePath = "src/main/resources/RC_F01_06_2002_T17_06_2022_TRY.csv";
            if (dataToLowerCase.equals("завтра") || dataToLowerCase.equals("tomorrow"))
                period = 1;
            else if (dataToLowerCase.equals("дней") || dataToLowerCase.equals("days"))
            {
                if (Integer.parseInt(arr[i - 1]) > 7 || Integer.parseInt(arr[i - 1]) < 1)
                    throw new RuntimeException("Количество дней должно быть не меньше 1 и не больше 7");
                period = Integer.parseInt(arr[i - 1]);
            }
            else if (dataToLowerCase.equals("неделю") || dataToLowerCase.equals("week"))
                period = 7;
            else if (dataToLowerCase.equals("месяц") || dataToLowerCase.equals("month"))
                period = 30;
            else if (dataToLowerCase.equals("дату") || dataToLowerCase.equals("date"))
                date = LocalDate.parse(arr[i - 1]);
            else if (dataToLowerCase.equals("алгоритму") || dataToLowerCase.equals("alg"))
                algorithm = arr[i - 1];
            else if (dataToLowerCase.equals("график") || dataToLowerCase.equals("graph"))
                graph = true;
        }
    }

    /**
     * Парсинг входных данных из файлов в поля класса CourseData
     * @param filePath - путь к файлу
     * @return - список со всеми значениями
     * @throws IOException - стек ошибки
     */
    public List<CourseData> parsingFile(String filePath) throws IOException
    {
        List<CourseData> courseDataList = new ArrayList<CourseData>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath), Charset.defaultCharset()))
        {
            // чтение csv файла. withFirstRecordAsHeader пропускает первую строку
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : records)
            {
                CourseData data = new CourseData();
                String tt = record.get(0).trim().replace(" ", "");
                data.setNominal(Integer.parseInt(tt));
                data.setData(LocalDate.parse(record.get(1), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                String tmp = record.get(2).replaceAll("^\"|\"$", "").trim();
                data.setCurs(Double.parseDouble(tmp.replaceAll(",", ".")));
                data.setCdx(record.get(3));
                courseDataList.add(data);
            }
        } catch (NullPointerException ex)
        {
            throw new NullPointerException("Произошла ошибка чтения файла!");
        }
        return courseDataList;
    }

}
