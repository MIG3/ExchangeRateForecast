package ru.tools;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.entity.CourseData;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParsingFile
{
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
