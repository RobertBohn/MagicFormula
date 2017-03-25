package com.magicformula.dao;

import com.magicformula.main.MagicFormula;
import com.magicformula.model.Company;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;

public class CompanyDao {

    private static final String INSERT = "replace into company " +
            "(cik, companyname, entityid, primaryexchange, marketoperator, markettier, primarysymbol, siccode, sicdescription) " +
            "values(?,?,?,?,?,?,?,?,?)";

    private static final String SELECT_TICKERS = "select primarysymbol from company where primarysymbol >= '' " +
            " and primarysymbol not in ('ZRPTIX') order by primarysymbol";

    private Connection connect;

    public CompanyDao() throws SQLException {
        connect = DriverManager.getConnection(MagicFormula.properties.getProperty("dbconnect"));
        connect.setNetworkTimeout(Executors.newFixedThreadPool(10), 3000);
    }

    public void insert(Company company) throws SQLException {
        PreparedStatement preparedStatement = connect.prepareStatement(INSERT);
        int i=1;

        preparedStatement.setString(i++, company.getCik());
        preparedStatement.setString(i++, company.getCompanyname());
        preparedStatement.setString(i++, company.getEntityid());
        preparedStatement.setString(i++, company.getPrimaryexchange());
        preparedStatement.setString(i++, company.getMarketoperator());
        preparedStatement.setString(i++, company.getMarkettier());
        preparedStatement.setString(i++, company.getPrimarysymbol());
        preparedStatement.setString(i++, company.getSiccode());
        preparedStatement.setString(i++, company.getSicdescription());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public Set<String> getTickers() throws SQLException {
        Set<String> set = new TreeSet();
        PreparedStatement preparedStatement = connect.prepareStatement(SELECT_TICKERS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            set.add(resultSet.getString("primarysymbol"));
        }
        resultSet.close();
        return set;
    }
}
