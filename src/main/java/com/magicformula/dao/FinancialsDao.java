package com.magicformula.dao;

import com.magicformula.main.MagicFormula;
import com.magicformula.model.Financials;

import java.sql.*;
import java.util.concurrent.Executors;

public class FinancialsDao {

    private static final String INSERT = "replace into financials " +
            "(primarysymbol, periodenddate, ebit, totalcurrentassets, totalcurrentliabilities, totalassets, intangibleassets, totalshorttermdebt, totallongtermdebt, cashandcashequivalents) " +
            "values(?,?,?,?,?,?,?,?,?,?)";

    private Connection connect;

    public FinancialsDao() throws SQLException {
        connect = DriverManager.getConnection(MagicFormula.properties.getProperty("dbconnect"));
        connect.setNetworkTimeout(Executors.newFixedThreadPool(2), 6000);
    }

    public void insert(Financials financials) throws SQLException {
        PreparedStatement preparedStatement = connect.prepareStatement(INSERT);
        int i=1;

        preparedStatement.setString(i++, financials.getPrimarysymbol());
        preparedStatement.setDate(i++, financials.getPeriodenddate());
        setDouble(preparedStatement, i++, financials.getEbit());
        setDouble(preparedStatement, i++, financials.getTotalcurrentassets());
        setDouble(preparedStatement, i++, financials.getTotalcurrentliabilities());
        setDouble(preparedStatement, i++, financials.getTotalassets());
        setDouble(preparedStatement, i++, financials.getIntangibleassets());
        setDouble(preparedStatement, i++, financials.getTotalshorttermdebt());
        setDouble(preparedStatement, i++, financials.getTotallongtermdebt());
        setDouble(preparedStatement, i++, financials.getCashandcashequivalents());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private void setDouble(PreparedStatement preparedStatement, int index, Double value) throws SQLException {
        if (value == null)
            preparedStatement.setNull(index, Types.DOUBLE);
        else
            preparedStatement.setDouble(index, value);
    }
}
