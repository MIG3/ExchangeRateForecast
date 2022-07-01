import ru.algorithms.*;


public class Main
{
    private static String TYPE_PROGRAM = "bot";

    public static void main( String[] args ) throws Exception
    {
        Algorithm prognos = new Algorithm();
        if (TYPE_PROGRAM.equals("console"))
            prognos.console();
        else if (TYPE_PROGRAM.equals("bot"))
            prognos.telegramBot();


    }
}
