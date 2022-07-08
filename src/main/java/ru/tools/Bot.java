package ru.tools;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.launch.StartBot;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        try
        {
            if (update.hasMessage() && update.getMessage().hasText())
            {
                String response = "";
                Message inMess = update.getMessage();
                String chatId = inMess.getChatId().toString();
                StartBot bot = new StartBot();
                //Получаем текст сообщения пользователя, отправляем в метод вызывающий главный метод general
                Map<LocalDate, Double> fc = bot.start(inMess.getText());
                SendMessage outMess = new SendMessage();
                outMess.setChatId(chatId);
                for(Map.Entry<LocalDate, Double> item : fc.entrySet())
                {
                    response += item.getKey().format(DateTimeFormatter.ofPattern("EEE - dd.MM.yyyy", Locale.getDefault())) + " - " + DEC_FORMAT.format(item.getValue())+ "\n";
                 }
                outMess.setText(response);
                execute(outMess);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PythonExecutionException | TelegramApiException e)
        {
            throw new RuntimeException(e);
        }
    }
}
