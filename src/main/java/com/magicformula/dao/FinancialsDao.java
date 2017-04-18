package com.magicformula.dao;

import com.magicformula.main.MagicFormula;
import com.magicformula.model.Financials;
import com.magicformula.util.SqlUtil;

import java.sql.*;
import java.util.concurrent.Executors;

public class FinancialsDao {

    private static final String INSERT = "replace into financials " +
            "(primarysymbol, periodenddate, ebit, totalcurrentassets, totalcurrentliabilities, totalassets, intangibleassets, totalshorttermdebt, totallongtermdebt, cashandcashequivalents, cashfromoperatingactivities, capitalexpenditures, totalliabilities) " +
            "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
        SqlUtil.setDouble(preparedStatement, i++, financials.getEbit());
        SqlUtil.setDouble(preparedStatement, i++, financials.getTotalcurrentassets());
        SqlUtil.setDouble(preparedStatement, i++, financials.getTotalcurrentliabilities());
        SqlUtil.setDouble(preparedStatement, i++, financials.getTotalassets());
        SqlUtil.setDouble(preparedStatement, i++, financials.getIntangibleassets());
        SqlUtil.setDouble(preparedStatement, i++, financials.getTotalshorttermdebt());
        SqlUtil.setDouble(preparedStatement, i++, financials.getTotallongtermdebt());
        SqlUtil.setDouble(preparedStatement, i++, financials.getCashandcashequivalents());
        SqlUtil.setDouble(preparedStatement, i++, financials.getCashfromoperatingactivities());
        SqlUtil.setDouble(preparedStatement, i++, financials.getCapitalexpenditures());
        SqlUtil.setDouble(preparedStatement, i++, financials.getTotalliabilities());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
