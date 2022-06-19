package ru.tools;

import java.util.List;
import java.util.Scanner;

public class Parsing
{
    public static String flag;
    public static int period = 0;
    public static String currency;

    public static void parsingCommand(String command)
    {
//        throw new UnsupportedOperationException("Парсинг входных команд.");
        command = command.trim();
        String[] arr = command.split(" ");
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i].equals("завтра") || arr[i].equals("tomorrow"))
            {
                flag = "next";
                period = 1;
            }
            else if (arr[i].equals("дней") || arr[i].equals("days"))
            {
                flag = "period";
                period = Integer.parseInt(arr[i-1]);
            }
        }
    }

    public static List<Double> parsingFile(String currency)
    {
        throw new UnsupportedOperationException("Парсинг входных данных.");
    }

}
