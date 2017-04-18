package com.magicformula.util;

import com.magicformula.main.MagicFormula;

import java.sql.*;
import java.util.concurrent.Executors;

public class SqlUtil {

    public static Connection getConnection() throws SQLException {
        Connection connect = DriverManager.getConnection(MagicFormula.properties.getProperty("dbconnect"));
        connect.setNetworkTimeout(Executors.newFixedThreadPool(2), 2000);
        return connect;
    }

    public static void setDouble(PreparedStatement preparedStatement, int index, Double value) throws SQLException {
        if (value == null)
            preparedStatement.setNull(index, Types.DOUBLE);
        else
            preparedStatement.setDouble(index, value);
    }
}
