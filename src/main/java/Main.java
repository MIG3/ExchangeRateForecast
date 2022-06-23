import ru.algorithms.*;
import ru.entity.*;
import ru.tools.*;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main( String[] args ) throws IOException, ParseException
    {
        Algorithm prognos = new Algorithm();
        Parsing pars = new Parsing();
        String filePath = "";


        List<Double> rates = new ArrayList<Double>();
        rates.add(75.45);
        rates.add(76.12);
        rates.add(77.34);
        rates.add(78.23);
        rates.add(80.11);
        rates.add(82.10);
        rates.add(90.45);

        pars.parsingCommand(" Курс доллара на завтра");

        if (pars.currency.equals("usd"))
            filePath = "RC_F01_06_2002_T17_06_2022_USD.csv";
        if (pars.currency.equals("eur"))
            filePath = "RC_F01_06_2002_T17_06_2022_EUR.csv";
        if (pars.currency.equals("try"))
            filePath = "RC_F01_06_2002_T17_06_2022_TRY.csv";

        List<CourseData> courseData = pars.parsingFile(filePath);
        //prognos.general(rates, pars.flag, pars.period);
        prognos.general(courseData, pars.flag, pars.period);

    }
}
