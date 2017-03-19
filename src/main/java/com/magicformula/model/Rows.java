package com.magicformula.model;

/**
 * Created by robert on 3/19/17.
 */
public class Rows
{
    private String rownum;

    private Values[] values;

    public String getRownum ()
    {
        return rownum;
    }

    public void setRownum (String rownum)
    {
        this.rownum = rownum;
    }

    public Values[] getValues ()
    {
        return values;
    }

    public void setValues (Values[] values)
    {
        this.values = values;
    }
}
