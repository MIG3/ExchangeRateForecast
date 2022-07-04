package ru.tools;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.apache.log4j.lf5.util.ResourceUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.algorithms.Algorithm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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
        try
        {
            if(update.hasMessage() && update.getMessage().hasText())
            {
                Message inMess = update.getMessage();
                String chatId = inMess.getChatId().toString();

                //Получаем текст сообщения пользователя, отправляем в метод вызывающий главный метод general
                Map<LocalDate, Double> fc = startAlgorithm(inMess.getText());

                SendMessage outMess = new SendMessage();

                outMess.setChatId(chatId);

                for(Map.Entry<LocalDate, Double> item : fc.entrySet())
                {
                    outMess.setText(item.getKey().format(DateTimeFormatter.ofPattern("EEE - dd.MM.yyyy", Locale.getDefault())) + " - " + DEC_FORMAT.format(item.getValue()));
                    execute(outMess);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PythonExecutionException | TelegramApiException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Map<LocalDate, Double> startAlgorithm(String textMsg) throws IOException, PythonExecutionException
    {
        Algorithm prognos = new Algorithm();
        Parsing pars = new Parsing();
        Output write = new Output();
        Map<LocalDate, Double> fc = new HashMap<LocalDate, Double>();

        pars.parsingCommand(textMsg);
        fc = prognos.general(pars.parsingFile(pars.filePath), pars.period, 2);
        write.graph(fc, pars.currency, pars.period);
        return fc;
    }
}
