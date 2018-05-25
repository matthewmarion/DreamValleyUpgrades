package com.moojm.dreamvalley.database;

import com.moojm.dreamvalley.DreamValleyUpgradesPlugin;
import com.moojm.dreamvalley.object.UpgradeTown;

import java.sql.*;

public class MySqlUpgradeRepository {

    private static final String UPGRADES_TABLE = "Town_Upgrades";

    public void add(UpgradeTown town) {
        try {
            Connection connection = DreamValleyUpgradesPlugin.getMySQL().getConnection();
            String query = "INSERT INTO " + UPGRADES_TABLE + " (town, speedTier, jumpTier, regenTier, saturationTier, strengthTier) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, town.getTownName());
            statement.setInt(2, town.getSpeedTier());
            statement.setInt(3, town.getJumpTier());
            statement.setInt(4, town.getRegenTier());
            statement.setInt(5, town.getSaturationTier());
            statement.setInt(6, town.getStrenthTier());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UpgradeTown get(String townName) {
        try {
            Connection connection = DreamValleyUpgradesPlugin.getMySQL().getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + UPGRADES_TABLE + " WHERE town='" + townName + "';";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int speedTier = result.getInt("speedTier");
                int jumpTier = result.getInt("jumpTier");
                int regenTier = result.getInt("regenTier");
                int saturationTier = result.getInt("saturationTier");
                int strengthTier = result.getInt("strengthTier");
                return new UpgradeTown.UpgradeTownBuilder().setTownName(townName)
                        .setSpeedTier(speedTier).setJumpTier(jumpTier).setRegenTier(regenTier)
                        .setSaturationTier(saturationTier).setStrenthTier(strengthTier).build();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
