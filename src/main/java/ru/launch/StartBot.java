package ru.launch;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import ru.algorithms.GeneralAlgorithm;
import ru.tools.Graph;
import ru.tools.Print;
import ru.tools.ParsingCommand;
import ru.tools.ParsingFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StartBot
{
    public Map<LocalDate, Double> start(String textMsg) throws IOException, PythonExecutionException
    {
        GeneralAlgorithm prognos = new GeneralAlgorithm();
        ParsingCommand pars = new ParsingCommand();
        ParsingFile file = new ParsingFile();
        Print write = new Print();
        Graph graph = new Graph();
        Map<LocalDate, Double> fc = new HashMap<LocalDate, Double>();

        pars.parsingCommand(textMsg);
        fc = prognos.general(file.parsingFile(pars.getFilePath()), pars.getPeriod(), pars.getAlgorithm());
        if (pars.isGraph())
        {
            graph.diagram(fc, pars.getCurrency(), pars.getPeriod());
        }
        return fc;
    }
}
