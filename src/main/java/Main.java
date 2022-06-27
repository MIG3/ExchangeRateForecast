import ru.algorithms.*;
import ru.tools.*;


public class Main
{
    public static void main( String[] args ) throws Exception
    {
        Algorithm prognos = new Algorithm();
        Parsing pars = new Parsing();

        String command = pars.readCommand();
        pars.parsingCommand(command);
        prognos.general(pars.parsingFile(pars.filePath), pars.period);


    }
}
