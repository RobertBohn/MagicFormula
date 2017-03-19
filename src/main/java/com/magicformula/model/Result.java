package com.magicformula.model;

/**
 * Created by robert on 3/19/17.
 */
public class Result
{
    private String totalrows;

    private Rows[] rows;

    public String getTotalrows ()
    {
        return totalrows;
    }

    public void setTotalrows (String totalrows)
    {
        this.totalrows = totalrows;
    }

    public Rows[] getRows ()
    {
        return rows;
    }

    public void setRows (Rows[] rows)
    {
        this.rows = rows;
    }
}
