package com.moojm.dreamvalley.database;

import com.moojm.dreamvalley.object.UpgradeTown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

public class MySQL extends Database {

   MySqlUpgradeRepository mySqlUpgradeRepository;

    private Connection connection;
    private String hostname;
    private String database;
    private String username;
    private String password;
    private String port;

    public MySQL(String hostname, String port, String database, String username, String password) {
        super();
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.mySqlUpgradeRepository = new MySqlUpgradeRepository();
    }

    @Override
    public Connection openConnection() throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"
                        + this.hostname + ":" + this.port + "/" + this.database,
                this.username, this.password);
        connection.setAutoCommit(false);
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void createTables() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Town_Upgrades(town VARCHAR(36), speed TINYINT, jump TINYINT, regen TINYINT, saturation TINYINT, strength TINYINT);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUpgradeTowns() {
        UpgradeTown.setTowns(mySqlUpgradeRepository.getAll());
    }

}