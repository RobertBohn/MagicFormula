package com.magicformula.dao;

import com.magicformula.main.MagicFormula;
import com.magicformula.model.Rank;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

public class RankDao {

    private static final String SELECT_ALL = "select performance.primarysymbol, returnoncapital, earningsyield " +
            "from performance, price, financials " +
            "where " +
            "performance.primarysymbol = price.primarysymbol " +
            "and performance.primarysymbol = financials.primarysymbol " +
            "and lasttradedate >= '2017-03-30' " +
            "and periodenddate >= '2016-11-15' " +
            "and closingprice * sharesoutstanding > 30000000 " +
            "and averagedailyvolume > 50000";

    private static final String INSERT = "replace into rank " +
            "(primarysymbol, returnoncapitalrank, earningsyieldrank, combinedrank) " +
            "values(?,?,?,?)";

    private Connection connect;

    public RankDao() throws SQLException {
        connect = DriverManager.getConnection(MagicFormula.properties.getProperty("dbconnect"));
        connect.setNetworkTimeout(Executors.newFixedThreadPool(2), 3000);
    }

    public List<Rank> getAll() throws SQLException {
        LinkedList<Rank> list = new LinkedList();

        PreparedStatement preparedStatement = connect.prepareStatement(SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Rank rank = new Rank();
            rank.setPrimarysymbol(resultSet.getString("primarysymbol"));
            rank.setEarningsyield(resultSet.getDouble("earningsyield"));
            rank.setReturnoncapital(resultSet.getDouble("returnoncapital"));
            list.add(rank);
        }
        resultSet.close();

        return list;
    }

    public void insert (Rank rank) throws SQLException {
        PreparedStatement preparedStatement = connect.prepareStatement(INSERT);
        int i=1;

        preparedStatement.setString(i++, rank.getPrimarysymbol());
        preparedStatement.setInt(i++, rank.getReturnoncapitalrank());
        preparedStatement.setInt(i++, rank.getEarningsyieldrank());
        preparedStatement.setInt(i++, rank.getCombinedrank());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
