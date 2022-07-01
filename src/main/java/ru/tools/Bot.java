package ru.tools;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.algorithms.Algorithm;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Bot extends TelegramLongPollingBot
{
    final private String BOT_TOKEN = "5451582967:AAFvygGJoGSOs_pVSjRWMVeR3eFnaWAyViw";
    final private String BOT_NAME = "BotCurrencyRateForecast";
    final private DecimalFormat DEC_FORMAT = new DecimalFormat("#.##");

    @Override
    public String getBotUsername()
    {
        return BOT_NAME;
    }

    @Override
    public String getBotToken()
    {
        return BOT_TOKEN;
    }
    @Override
    public void onUpdateReceived(Update update)
    {
        Map<LocalDate, Double> forecast = new HashMap<LocalDate, Double>() {{
            put(LocalDate.of(2022, 7, 28), 51.4);
            put(LocalDate.of(2022, 7, 29), 52.9);
        }};
        try
        {
            if(update.hasMessage() && update.getMessage().hasText())
            {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();

                //Получаем текст сообщения пользователя, отправляем в метод вызывающий главный метод general
                Map<LocalDate, Double> fc = startAlgorithm(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage outMess = new SendMessage();

                //Добавляем в наше сообщение id чата а также наш ответ
                outMess.setChatId(chatId);
                //outMess.setText(response);

                for(Map.Entry<LocalDate, Double> item : fc.entrySet())
                {
                    outMess.setText(item.getKey().format(DateTimeFormatter.ofPattern("EEE - dd.MM.yyyy", Locale.getDefault())) + " - " + DEC_FORMAT.format(item.getValue()));
                    execute(outMess);
                }
                //Отправка в чат
                //execute(outMess);
            }
        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }
    public Map<LocalDate, Double> startAlgorithm(String textMsg) throws IOException
    {
        Algorithm prognos = new Algorithm();
        Parsing pars = new Parsing();
        Output write = new Output();

        pars.parsingCommand(textMsg);
        Map<LocalDate, Double> response = prognos.general(pars.parsingFile(pars.filePath), Parsing.period);
        // здесь должен быть вызов метода печати в чат


        //response = write.printToMessage(forecast);
        return response;
    }
}
