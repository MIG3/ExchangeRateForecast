package ru.launch;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import ru.algorithms.GeneralAlgorithm;
import ru.tools.Graph;
import ru.tools.ParsingCommand;
import ru.tools.ParsingFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class StartBot
{
    public List<Map<LocalDate, Double>> start(String textMsg) throws IOException, PythonExecutionException
    {
        GeneralAlgorithm prognos = new GeneralAlgorithm();
        ParsingCommand pars = new ParsingCommand();
        ParsingFile file = new ParsingFile();
        Graph graph = new Graph();
        Map<LocalDate, Double> forecast = new TreeMap<LocalDate, Double>();
        Map<String, String> curFile = new TreeMap<String, String>();
        List<List<Double>> forecastToGraph = new ArrayList<List<Double>>();
        List<Map<LocalDate, Double>> listFc = new ArrayList<Map<LocalDate, Double>>();

        curFile = pars.parsingCommand(textMsg);

        for (Map.Entry<String, String> item : curFile.entrySet())
        {
            forecast = prognos.general(file.parsingFile(pars.getFilePath()), pars.getPeriod(), pars.getAlgorithm(), pars.getDate());
            listFc.add(forecast);
            List<Double> temp = forecast.entrySet().parallelStream().collect(ArrayList::new,
                    (list, element) -> list.add(element.getValue()), ArrayList::addAll);
            forecastToGraph.add(temp);
        }
        if (pars.isGraph() && pars.getDate()==null)
        {
            graph.diagram(forecastToGraph, pars.getPeriod());
        }
        return listFc;
    }
}
