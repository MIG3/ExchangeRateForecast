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
        pars.parsingCommand(command);
        Map<LocalDate, Double> forecast = new HashMap<LocalDate, Double>();
        forecast = prognos.general(file.parsingFile(pars.getFilePath()), pars.getPeriod(), pars.getAlgorithm());
        write.printToConsole(forecast);
        if (pars.isGraph())
        {
            graph.diagram(forecast, pars.getCurrency(), pars.getPeriod());
        }
    }
}
