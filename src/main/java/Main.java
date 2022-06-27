import ru.algorithms.*;
import ru.entity.*;
import ru.tools.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args ) throws Exception
    {
        Algorithm prognos = new Algorithm();
        Parsing pars = new Parsing();
        String filePath = "";

        Scanner in = new Scanner(System.in);
        System.out.print("Введите команду: ");
        String command = in.nextLine();

        try
        {
            pars.parsingCommand(command);

            if (pars.currency.equals("usd"))
                filePath = "RC_F01_06_2002_T17_06_2022_USD.csv";
            if (pars.currency.equals("eur"))
                filePath = "RC_F01_06_2002_T17_06_2022_EUR.csv";
            if (pars.currency.equals("try"))
                filePath = "RC_F01_06_2002_T17_06_2022_TRY.csv";

            List<CourseData> courseData = pars.parsingFile(filePath);
            prognos.general(courseData, pars.period);
        } catch (RuntimeException e)
        {
            throw new RuntimeException("Входная команда должна иметь вид: Курс-валюта-период, например, rate USD tomorrow");
        }

    }
}
