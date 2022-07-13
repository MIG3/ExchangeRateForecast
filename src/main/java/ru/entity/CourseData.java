package ru.entity;

import java.time.LocalDate;

/**
 * Класс данных: номинал, дата, курс, валюта
 */
public class CourseData
{
    private int nominal;
    private LocalDate Data;
    private double curs;
    private String cdx;


    public int getNominal()
    {
        return nominal;
    }

    public void setNominal(int nominal)
    {
        this.nominal = nominal;
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
        return curs;
    }

    public void setCurs(double curs)
    {
        this.curs = curs;
    }

    public String getCdx()
    {
        return cdx;
    }

    public void setCdx(String cdx)
    {
        this.cdx = cdx;
    }
}
