package com.magicformula.dao;

import com.magicformula.main.MagicFormula;
import com.magicformula.model.Financials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.Executors;

public class FinancialsDao {

    private static final String INSERT = "replace into financials " +
            "(primarysymbol, periodenddate, ebit, totalcurrentassets, totalcurrentliabilities, totalassets, intangibleassets, totalshorttermdebt, totallongtermdebt, cashandcashequivalents) " +
            "values(?,?,?,?,?,?,?,?,?,?)";

    private Connection connect;

    public FinancialsDao() throws SQLException {
        connect = DriverManager.getConnection(MagicFormula.properties.getProperty("dbconnect"));
        connect.setNetworkTimeout(Executors.newFixedThreadPool(10), 3000);
    }


    public void insert(Financials financials) throws SQLException {
        PreparedStatement preparedStatement = connect.prepareStatement(INSERT);
        int i=1;

        preparedStatement.setString(i++, financials.getPrimarysymbol());
        preparedStatement.setString(i++, financials.getPeriodenddate());
        preparedStatement.setDouble(i++, financials.getEbit());
        preparedStatement.setDouble(i++, financials.getTotalcurrentassets());
        preparedStatement.setDouble(i++, financials.getTotalcurrentliabilities());
        preparedStatement.setDouble(i++, financials.getTotalassets());
        preparedStatement.setDouble(i++, financials.getIntangibleassets());
        preparedStatement.setDouble(i++, financials.getTotalshorttermdebt());
        preparedStatement.setDouble(i++, financials.getTotallongtermdebt());
        preparedStatement.setDouble(i++, financials.getCashandcashequivalents());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
