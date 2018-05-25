package com.moojm.dreamvalley.gui;

import com.moojm.dreamvalley.object.UpgradeTown;
import com.moojm.dreamvalley.perks.Perk;
import com.moojm.dreamvalley.utils.ChatUtil;

import java.util.ArrayList;
import java.util.List;

public class LoreUtils {

    private static int getNextTier(Perk perk, UpgradeTown town) {
        int max = perk.getMaxTier();
        int current = town.getTierFromPerk(perk);
        if (current >= max) {
            return 0;
        }
        return current + 1;
    }

    private static double getUpgradeCost(Perk perk, int nextTier) {
        return perk.getTierCost(nextTier);
    }


    public static List<String> buildLore(Perk perk, UpgradeTown town) {
        List<String> lore = new ArrayList<>();
        lore.add(ChatUtil.toColor("&8<------------------->"));
        for (int i = 1; i < perk.getMaxTier() + 1; i++) {
            lore.add(ChatUtil.toColor("&7* &8Tier " + i + ": &7" + perk.getType() + " " + i));
        }
        lore.add(ChatUtil.toColor("&8<------------------->"));
        int nextTier = getNextTier(perk, town);
        if (nextTier != 0) {
            lore.add(ChatUtil.toColor("&7• Next Upgrade: &eTier " + nextTier));
            double upgradeCost = getUpgradeCost(perk, nextTier);
            lore.add(ChatUtil.toColor("&7• Upgrade Cost: &a" + upgradeCost + " Gems"));
        }
        return lore;
    }
}
