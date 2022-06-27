package ru.entity;

import java.time.LocalDate;

/**
 * Класс данных: номинал, дата, курс, валюта
 */
public class CourseData
{
    private int Nominal;
    private LocalDate Data;
    private double Curs;
    private String Cdx;


    public int getNominal()
    {
        return Nominal;
    }

    public void setNominal(int nominal)
    {
        Nominal = nominal;
    }

    public LocalDate getData()
    {
        return Data;
    }

    public void setData(LocalDate data)
    {
        Data = data;
    }

    public double getCurs()
    {
        return Curs;
    }

    public void setCurs(double curs)
    {
        Curs = curs;
    }

    public String getCdx()
    {
        return Cdx;
    }

    public void setCdx(String cdx)
    {
        Cdx = cdx;
    }
}
