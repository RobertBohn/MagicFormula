package com.magicformula.util;

import com.magicformula.main.MagicFormula;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executors;

public class SqlUtil {

    public static Connection getConnection() throws SQLException {
        Connection connect = DriverManager.getConnection(MagicFormula.properties.getProperty("dbconnect"));
        connect.setNetworkTimeout(Executors.newFixedThreadPool(2), 2000);
        return connect;
    }
}
