package com.magicformula.dao;

import com.magicformula.main.MagicFormula;
import com.magicformula.model.Price;

import java.sql.*;
import java.util.concurrent.Executors;

public class PriceDao {

    private static final String INSERT = "replace into price " +
            "(primarysymbol, companyname, lasttradedate, closingprice, sharesoutstanding, averagedailyvolume, dividendpershare) " +
            "values(?,?,?,?,?,?,?)";

    private Connection connect;

    public PriceDao() throws SQLException {
        connect = DriverManager.getConnection(MagicFormula.properties.getProperty("dbconnect"));
        connect.setNetworkTimeout(Executors.newFixedThreadPool(2), 2000);
    }

    public void insert(Price price) throws SQLException {
        PreparedStatement preparedStatement = connect.prepareStatement(INSERT);
        int i=1;

        preparedStatement.setString(i++, price.getPrimarysymbol());
        preparedStatement.setString(i++, price.getCompanyname());
        preparedStatement.setDate(i++, price.getLasttradedate());
        setDouble(preparedStatement, i++, price.getClosingprice());
        setDouble(preparedStatement, i++, price.getSharesoutstanding());
        setDouble(preparedStatement, i++, price.getAveragedailyvolume());
        setDouble(preparedStatement, i++, price.getDividendpershare());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private void setDouble(PreparedStatement preparedStatement, int index, Double value) throws SQLException {
        if (value == null) {
            preparedStatement.setNull(index, Types.DOUBLE);
        } else {
            preparedStatement.setDouble(index, value);
        }
    }
}
