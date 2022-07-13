package ru.launch;

import ru.algorithms.GeneralAlgorithm;
import ru.tools.Graph;
import ru.tools.PrintToConsole;
import ru.tools.ParsingCommand;
import ru.tools.ParsingFile;

import java.time.LocalDate;
import java.util.*;

public class StartConsole
{
    public void start() throws Exception
    {
        GeneralAlgorithm prognos = new GeneralAlgorithm();
        ParsingCommand pars = new ParsingCommand();
        PrintToConsole write = new PrintToConsole();
        ParsingFile file = new ParsingFile();
        Graph graph = new Graph();

        String command = pars.readCommand();

        Map<String, String> curFile = new TreeMap<String, String>();
        curFile = pars.parsingCommand(command);

        List<List<Double>> listOLists = new ArrayList<List<Double>>();

        Map<LocalDate, Double> forecast = new TreeMap<LocalDate, Double>();
        for (Map.Entry<String, String> item : curFile.entrySet())
        {
            forecast = prognos.general(file.parsingFile(item.getValue()), pars.getPeriod(), pars.getAlgorithm(), pars.getDate());

            List<Double> temp = forecast.entrySet().parallelStream().collect(ArrayList::new,
                    (list, element) -> list.add(element.getValue()), ArrayList::addAll);
            listOLists.add(temp);

            write.printToConsole(forecast);
        }
        if (pars.isGraph() && pars.getDate() == null)
        {
            graph.diagram(listOLists, pars.getPeriod());
        }
    }
}
