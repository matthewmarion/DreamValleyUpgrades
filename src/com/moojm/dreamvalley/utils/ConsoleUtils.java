package com.moojm.dreamvalley.utils;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class ConsoleUtils {

    private static final String PREFIX = "[DreamValleyUpgrades]";

    public static void log(Level level, String message) {
        Bukkit.getLogger().log(level, PREFIX + " " + message);
    }
}
