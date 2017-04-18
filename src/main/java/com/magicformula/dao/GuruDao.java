package com.magicformula.dao;

import com.magicformula.main.MagicFormula;
import com.magicformula.model.Guru;
import com.magicformula.util.SqlUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.Executors;

public class GuruDao {

    private static final String INSERT = "replace into guru " +
            "(primarysymbol, returnoncapital, earningsyield, pricetobook, freecashflow, enterprisevalue) " +
            "values(?,?,?,?,?,?)";

    private Connection connect;

    public GuruDao() throws SQLException {
        connect = DriverManager.getConnection(MagicFormula.properties.getProperty("dbconnect"));
        connect.setNetworkTimeout(Executors.newFixedThreadPool(2), 6000);
    }

    public void insert(Guru guru) throws SQLException {
        PreparedStatement preparedStatement = connect.prepareStatement(INSERT);
        int i=1;

        preparedStatement.setString(i++, guru.getPrimarysymbol());
        SqlUtil.setDouble(preparedStatement, i++, guru.getReturnoncapital());
        SqlUtil.setDouble(preparedStatement, i++, guru.getEarningsyield());
        SqlUtil.setDouble(preparedStatement, i++, guru.getPricetobook());
        SqlUtil.setDouble(preparedStatement, i++, guru.getFreecashflow());
        SqlUtil.setDouble(preparedStatement, i++, guru.getEnterprisevalue());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
