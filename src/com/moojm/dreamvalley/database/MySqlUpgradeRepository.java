package com.moojm.dreamvalley.database;

import com.moojm.dreamvalley.DreamValleyUpgradesPlugin;
import com.moojm.dreamvalley.object.UpgradeTown;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUpgradeRepository implements UpgradeRespository {

    private static final String UPGRADES_TABLE = "Town_Upgrades";

    @Override
    public void add(UpgradeTown town) {
        try {
            Connection connection = DreamValleyUpgradesPlugin.getMySQL().getConnection();
            String query = "INSERT INTO " + UPGRADES_TABLE + " (town, speed, jump, regen, saturation, strength) VALUES (?, ?, ?, ?, ?, ?);";
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

    @Override
    public UpgradeTown get(String townName) {
        try {
            Connection connection = DreamValleyUpgradesPlugin.getMySQL().getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + UPGRADES_TABLE + " WHERE town='" + townName + "';";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int speedTier = result.getInt("speed");
                int jumpTier = result.getInt("jump");
                int regenTier = result.getInt("regen");
                int saturationTier = result.getInt("saturation");
                int strengthTier = result.getInt("strength");
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

    @Override
    public List<UpgradeTown> getAll() {
        List<UpgradeTown> towns = new ArrayList<>();
        try {
            Connection connection = DreamValleyUpgradesPlugin.getMySQL().getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + UPGRADES_TABLE + ";";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                UpgradeTown upgradeTown = new UpgradeTown.UpgradeTownBuilder().setSpeedTier(result.getInt("speed")).
                        setJumpTier(result.getInt("jump")).setRegenTier(result.getInt("regen")).setSaturationTier(result.getInt("saturation"))
                        .setStrenthTier(result.getInt("strength")).build();
                towns.add(upgradeTown);
            }
            return towns;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(String perk, UpgradeTown upgradeTown, int tier) {
        try {
            Connection connection = DreamValleyUpgradesPlugin.getMySQL().getConnection();
            String query = "UPDATE " + UPGRADES_TABLE + " SET " + perk + "=? WHERE town=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, tier);
            statement.setString(2, upgradeTown.getTownName());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
