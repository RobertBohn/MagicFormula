package com.magicformula.dao;

import com.magicformula.main.MagicFormula;
import com.magicformula.model.Guru;
import com.magicformula.util.SqlUtil;
import com.magicformula.util.StringUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

public class GuruDao {

    private static final String INSERT = "replace into guru " +
            "(primarysymbol, returnoncapital, earningsyield, pricetobook, freecashflow, enterprisevalue, updatedate) " +
            "values(?,?,?,?,?,?,now())";

    private static final String SELECT = "select primarysymbol, companyname from company where primarysymbol in (%s)";

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

    public List<Guru> getGurus() throws SQLException {
        List<Guru> gurus = new LinkedList<Guru>();

        String statement = String.format(SELECT, StringUtil.buildIntoString(MagicFormula.properties.getProperty("start")));
        PreparedStatement preparedStatement = connect.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Guru guru = new Guru();
            guru.setPrimarysymbol(resultSet.getString("primarysymbol"));
            guru.setCompanyname(StringUtil.formatCompanyName(resultSet.getString("companyname")));
            gurus.add(guru);
        }
        resultSet.close();

        return gurus;
    }
}
