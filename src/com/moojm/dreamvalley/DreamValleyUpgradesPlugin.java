package com.moojm.dreamvalley;

import com.moojm.dreamvalley.command.UpgradeCommand;
import com.moojm.dreamvalley.database.MySQL;
import com.moojm.dreamvalley.gui.UpgradeListeners;
import com.moojm.dreamvalley.perks.PerkListeners;
import com.moojm.dreamvalley.utils.ConsoleUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class DreamValleyUpgradesPlugin extends JavaPlugin {

    private static DreamValleyUpgradesPlugin instance;
    private static MySQL mySQL;
    private static Statement statement;

    public static DreamValleyUpgradesPlugin getInstance() {
        return DreamValleyUpgradesPlugin.instance;
    }

    @Override
    public void onEnable() {
        DreamValleyUpgradesPlugin.instance = this;
        loadSettings();
        setupDatabase(this.getConfig());
        Bukkit.getServer().getPluginManager().registerEvents(new UpgradeCommand(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new UpgradeListeners(), this);
        PerkListeners perkListeners = new PerkListeners();
        Bukkit.getServer().getPluginManager().registerEvents(perkListeners, this);
        /*Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (PerkListeners.getPlayersInTown().size() != 0) {
                    System.out.println("Setting potion effects in runnable again..");
                    perkListeners.givePlayersEffects();
                }

            }
        }, 0, 40L);*/
    }

    @Override
    public void onDisable() {
        DreamValleyUpgradesPlugin.instance = null;
    }

    private void loadSettings() {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }

    private void setupDatabase(FileConfiguration config) {
        Bukkit.getLogger().info("Setting up MySQL.");
        String hostname = config.getString("mysql" + ".hostname");
        String port = config.getString("mysql" + ".port");
        String database = config.getString("mysql" + ".database");
        String username = config.getString("mysql" + ".username");
        String password = config.getString("mysql" + ".password");
        mySQL = new MySQL(hostname, port, database, username, password);
        try {
            mySQL.openConnection();
            ConsoleUtils.log(Level.INFO, "Opened connection with MySQL database.");
            mySQL.createTables();
            ConsoleUtils.log(Level.INFO, "Created MySQL tables.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static MySQL getMySQL() {
        return DreamValleyUpgradesPlugin.mySQL;
    }


}
