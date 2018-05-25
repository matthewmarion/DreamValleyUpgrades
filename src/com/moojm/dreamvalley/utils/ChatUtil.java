package com.moojm.dreamvalley.utils;

import net.md_5.bungee.api.ChatColor;

public class ChatUtil {

    public static final String NO_PERMISSION = "&cYou do not have permission.";

    public static String toColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
