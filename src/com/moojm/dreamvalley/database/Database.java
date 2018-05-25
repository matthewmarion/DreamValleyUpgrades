package com.moojm.dreamvalley.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {

    protected Connection connection;


    protected Database() {
        this.connection = null;
    }

    public abstract Connection openConnection() throws SQLException,
            ClassNotFoundException;


    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean closeConnection() throws SQLException {
        if (connection == null) {
            return false;
        }
        connection.close();
        return true;
    }

    public ResultSet querySQL(String query) throws SQLException,
            ClassNotFoundException {
        if (!checkConnection()) {
            openConnection();
        }

        Statement statement = connection.createStatement();

        ResultSet ResultSet = statement.executeQuery(query);

        connection.commit();

        return ResultSet;
    }

    public int updateSQL(String query) throws SQLException,
            ClassNotFoundException {
        if (!checkConnection()) {
            openConnection();
        }

        Statement statement = connection.createStatement();

        int ResultCode = statement.executeUpdate(query);

        connection.commit();

        return ResultCode;
    }

    public int[] sendBatchStatement(String[] stmts) throws SQLException,
            ClassNotFoundException {
        if(!checkConnection()) {
            openConnection();
        }

        Statement statement = connection.createStatement();

        for(String state : stmts) {
            statement.addBatch(state);
        }

        int[] ResultCodes = statement.executeBatch();

        connection.commit();

        return ResultCodes;
    }
}
