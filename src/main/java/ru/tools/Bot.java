package ru.tools;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.launch.StartBot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Bot extends TelegramLongPollingBot
{
    final private String BOT_TOKEN = "5451582967:AAFvygGJoGSOs_pVSjRWMVeR3eFnaWAyViw";
    final private String BOT_NAME = "BotCurrencyRateForecast";
    final private DecimalFormat DEC_FORMAT = new DecimalFormat("#.##");
    private static Logger log = Logger.getLogger(Bot.class.getName());
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
                ParsingCommand pars = new ParsingCommand();
                PrintToBot printToBot = new PrintToBot();

                pars.parsingCommand(inMess.getText());
                List<Map<LocalDate, Double>> fc = bot.start(inMess.getText());
                response = printToBot.print(fc, DEC_FORMAT);

                if(pars.isGraph())
                {
                    SendPhoto outMess = new SendPhoto();
                    outMess.setChatId(chatId);
                    InputFile photo = new InputFile(new File("./src/main/diagram/diagram.png"));
                    outMess.setPhoto(photo);
                    outMess.setCaption(response);
                    try
                    {
                        execute(outMess);
                    }
                    catch (TelegramApiException e)
                    {
                        log.trace(e);
                    }
                }
                else
                {
                    SendMessage outMess = new SendMessage();
                    outMess.setChatId(chatId);
                    outMess.setText(response);
                    try
                    {
                        execute(outMess);
                    }
                    catch (TelegramApiException e)
                    {
                        log.trace(e);
                    }
                }
            }
        } catch (IOException e)
        {
            log.trace(e);
        } catch (PythonExecutionException e)
        {
            throw new RuntimeException(e);
        }
    }
}
