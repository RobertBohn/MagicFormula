package com.magicformula.dao;

import com.magicformula.main.MagicFormula;
import com.magicformula.model.Company;

import java.sql.*;
import java.util.concurrent.Executors;

public class CompanyDao {

    private static final String INSERT = "replace into company " +
            "(cik, companyname, entityid, primaryexchange, marketoperator, markettier, primarysymbol, siccode, sicdescription) " +
            "values(?,?,?,?,?,?,?,?,?)";

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
}
