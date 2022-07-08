package ru.launch;

import ru.algorithms.GeneralAlgorithm;
import ru.tools.Graph;
import ru.tools.Print;
import ru.tools.ParsingCommand;
import ru.tools.ParsingFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StartConsole
{
    public void start() throws Exception
    {
        GeneralAlgorithm prognos = new GeneralAlgorithm();
        ParsingCommand pars = new ParsingCommand();
        Print write = new Print();
        ParsingFile file = new ParsingFile();
        Graph graph = new Graph();

        String command = pars.readCommand();

        Map<String, String> curFile = new HashMap<String, String>();
        curFile = pars.parsingCommand(command);

        Map<LocalDate, Double> forecast = new HashMap<LocalDate, Double>();
        for (Map.Entry<String, String> item : curFile.entrySet())
        {
            forecast = prognos.general(file.parsingFile(item.getValue()), pars.getPeriod(), pars.getAlgorithm());
            write.printToConsole(forecast);
            if (pars.isGraph())
            {
                graph.diagram(forecast, item.getValue(), pars.getPeriod());
            }
        }
    }
}
