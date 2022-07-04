package ru.algorithms;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.tools.Bot;
import ru.tools.Output;

import java.time.LocalDate;
import java.util.*;

import ru.entity.*;
import ru.tools.Parsing;


public class Algorithm
{
    /**
     * Основной метод, в котором реализована вся логика.
     * Считается интервал, на который надо посчитать курсы и складывается с периодом из входных данных.
     * Считаются курсы в зависимости от размера периода и равенства/неравенства текущей даты и самой свежей из входных данных.
     * @param courseDataList - список с значениями, в которые входят: курсы, даты, номинал и валюта
     * @param period - количество дней для прогноза
     */
    public Map<LocalDate, Double> general(List<CourseData> courseDataList, int period, int algo)
    {
        Map<LocalDate, Double> forecast = new HashMap<LocalDate, Double>();
        Map<LocalDate, Double> forecastReverse = new LinkedHashMap<LocalDate, Double>();
        Prognosis pr = new Prognosis();
        WorkDate differenceDate = new WorkDate();
        Parsing pars = new Parsing();

        double average = 0.0;
        LocalDate oldD = courseDataList.get(0).getData();
        LocalDate curDate = LocalDate.now();
        LocalDate startDate;
        LocalDate nextDate;
        int interval, sum;
        int countDayFromAlgo = 0;

        interval = differenceDate.countDays(period, oldD, curDate);
        sum = period + interval;

        if (algo==1)
            countDayFromAlgo = 8;
        else if (algo == 2)
            countDayFromAlgo = 31;

        List<Double> courses= new ArrayList<Double>();
        if (algo != 3)
        {
            // в этом цикле считаются те курсы, на основе которых потом считаются для периодов
            for (int i = 1; i < countDayFromAlgo; i++) // в этом цикле число, до которого бежит i - это то число на основе которого применяется алгоритм
            {
                courses.add(courseDataList.get(i).getCurs());
            }
            Collections.reverse(courses); // чтобы список начинался с самых старых значений. Пригодится позже

            // Период > 1:
            // 1. Даты равны -> на неделю от текущей даты
            // 2. Даты не равны:
            //   а. на завтра + период между датами
            //   б. на неделю + период между датами
            if (sum > 1)
            {
                // считаю значение курса по заданному алгоритму на каждый следующий день
                for (int i = 0; i < sum; i++)
                {
                    if (algo == 1)
                        courses.add(pr.average(courses));
                    else if (algo == 2)
                        courses.add(pr.mystic(courses));
                    courses.remove(0);
                }

                startDate = LocalDate.now();
                // если нужен курс на следующий день, получаю последний элемент списка
                if (period == 1)
                {
                    nextDate = differenceDate.addOneDay(startDate);
                    forecast.put(nextDate, courses.get(courses.size() - 1));
                } else
                {
                    for (int i = 0; i < period; i++)
                    {
                        startDate = differenceDate.addOneDay(startDate);
                        forecast.put(startDate, courses.get(i));
                    }
                }
            }
            // Период == 1:
            // Даты равны -> на следующий день от текущей даты
            else
            {
                nextDate = LocalDate.now();
                average = pr.average(courses);
                forecast.put(nextDate, average);
            }
        }
        else
        {

            forecast = pr.courseLastYear(courseDataList, Parsing.date);
            Map.Entry actualValue = forecast.entrySet()
                    .stream()
                    .findFirst()
                    .get();
        }
        return forecast;
    }

    public void console() throws Exception
    {
        Algorithm prognos = new Algorithm();
        Parsing pars = new Parsing();
        Output write = new Output();

        String command = pars.readCommand();
        pars.parsingCommand(command);
        Map<LocalDate, Double> forecast = new HashMap<LocalDate, Double>();
        forecast = prognos.general(pars.parsingFile(pars.filePath), pars.period, 2);
        write.printToConsole(forecast);
        write.graph(forecast, pars.currency, pars.period);

    }

    /**
     * Работает с ботом
     */
    public void telegramBot()
    {
        try
        {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot());
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }
}
