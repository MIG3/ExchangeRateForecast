import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.algorithms.*;
import ru.launch.StartConsole;
import ru.tools.Bot;


public class Main
{
    private static String TYPE_PROGRAM = "bot";

    public static void main( String[] args ) throws Exception
    {
        StartConsole console = new StartConsole();
        if (TYPE_PROGRAM.equals("console"))
            console.start();
        else if (TYPE_PROGRAM.equals("bot"))
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
}
