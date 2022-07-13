package ru.tools;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ParsingCommand
{
    private int period = 0;
    private LocalDate date;
    private String currency;
    private boolean graph = false;
    private String filePath;
    private String algorithm;

    public int getPeriod()
    {
        return period;
    }
    public LocalDate getDate()
    {
        return date;
    }
    public String getAlgorithm()
    {
        return algorithm;
    }
    public boolean isGraph()
    {
        return graph;
    }
    public String getCurrency()
    {
        return currency;
    }
    public String getFilePath()
    {
        return filePath;
    }

    public String readCommand()
    {
        Scanner in = new Scanner(System.in);
        System.out.print(
                "Прогноз валюты на период или дату по заданному алгоритму c возможным построением графика.\n" +
                "-----------------------------------------------------------------------------------------\n" +
                "Алгоритмы необходимо указывать на английском языке.\nДоступны следующие варианты:\n" +
                "- average - алгоритм среднего на основе последних 7 дней;\n" +
                "- mystic - алгоритм среднего на основе последних 30 дней;\n" +
                "- from_internet - алгоритм линейной регрессии на основе последних 30 дней;\n" +
                "- last_year - алгоритм курса за прошлогоднюю дату.\n" +
                "После названия алгоритма необходимо ипользовать ключевое слово alg (average alg).\n");
        System.out.print("Введите команду: ");
        return in.nextLine();
    }

    /**
     * Парсинг входной команды
     * @param command команда на вход
     * @return мапа из пар валюта - путь к файлу с этой валютой
     */
    public Map<String, String> parsingCommand(String command)
    {
        command = command.trim();
        String[] arr = command.split("\\s+");
        Map<String, String> currencyAndFile = new TreeMap<>();
        for (int i = 0; i < arr.length; i++)
        {
            String dataToLowerCase = arr[i].toLowerCase();
            if (dataToLowerCase.equals("usd") || dataToLowerCase.equals("доллара"))
            {
                filePath = "src/main/resources/RC_F01_06_2002_T17_06_2022_USD.csv";
                currency = "usd";
                currencyAndFile.put(currency,filePath);
            }
            else if (dataToLowerCase.equals("eur") || dataToLowerCase.equals("евро"))
            {
                filePath = "src/main/resources/RC_F01_06_2002_T17_06_2022_EUR.csv";
                currency = "eur";
                currencyAndFile.put(currency,filePath);
            }
            else if (dataToLowerCase.equals("try") || dataToLowerCase.equals("турецкой_лиры"))
            {
                filePath = "src/main/resources/RC_F01_06_2002_T17_06_2022_TRY.csv";
                currency = "try";
                currencyAndFile.put(currency,filePath);
            }
            if (dataToLowerCase.equals("завтра") || dataToLowerCase.equals("tomorrow"))
                period = 1;
            else if (dataToLowerCase.equals("дней") || dataToLowerCase.equals("days"))
            {
                period = Integer.parseInt(arr[i - 1]);
            }
            else if (dataToLowerCase.equals("неделю") || dataToLowerCase.equals("week"))
                period = 7;
            else if (dataToLowerCase.equals("месяц") || dataToLowerCase.equals("month"))
                period = 30;
            else if (dataToLowerCase.equals("дату") || dataToLowerCase.equals("date"))
                date = LocalDate.parse(arr[i - 1], DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault()));
            else if (dataToLowerCase.equals("алгоритму") || dataToLowerCase.equals("alg"))
                algorithm = arr[i - 1];
            else if (dataToLowerCase.equals("график") || dataToLowerCase.equals("graph"))
                graph = true;
        }
        return currencyAndFile;
    }
}
