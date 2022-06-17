import ru.algorithms.Prognosis;
import ru.tools.Output;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main( String[] args )
    {
        Prognosis pr = new Prognosis();
        Output write = new Output();
        double average = 0.0;

        List<Double> rates = new ArrayList<Double>();
        rates.add(75.45);
        rates.add(76.12);
        rates.add(77.34);
        rates.add(78.23);
        rates.add(80.11);
        rates.add(82.10);
        rates.add(90.45);

        average = pr.average(rates);
        write.printToConsole(average, "next");


    }
}
