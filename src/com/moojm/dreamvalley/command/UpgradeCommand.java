package com.moojm.dreamvalley.command;

import com.moojm.dreamvalley.database.MySqlUpgradeRepository;
import com.moojm.dreamvalley.gui.UpgradeInventory;
import com.moojm.dreamvalley.object.UpgradeTown;
import com.moojm.dreamvalley.utils.ChatUtil;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UpgradeCommand implements Listener {

    private static Set<UUID> openUpgrades = new HashSet<>();
    private MySqlUpgradeRepository repository = new MySqlUpgradeRepository();

    @EventHandler
    public void on(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equals("/towny upgrades")) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            try {
                Resident resident = TownyUniverse.getDataSource().getResident(player.getName());
                Town town = resident.getTown();
                player.sendMessage(ChatUtil.toColor("&aOpening town upgrades panel."));
                String townName = town.getName();
                if (!isRegistered(townName)) {
                    UpgradeTown upgradeTown = new UpgradeTown.UpgradeTownBuilder().setTownName(townName)
                            .setSpeedTier(0).setJumpTier(0).setRegenTier(0).setSaturationTier(0).setStrenthTier(0).build();
                    UpgradeTown.getTowns().add(upgradeTown);
                }
                openUpgrades(player, townName);
            } catch (NotRegisteredException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isRegistered(String townName) {
        return UpgradeTown.getTownFromName(townName) != null;
    }

    private void openUpgrades(Player player, String townName) {
        UpgradeInventory inv = new UpgradeInventory(townName);
        player.openInventory(inv.getInventory());
        addOpenUpgrade(player);
    }

    private void addOpenUpgrade(Player player) {
        openUpgrades.add(player.getUniqueId());
    }

    public static Set<UUID> getOpenUpgrades() {
        return UpgradeCommand.openUpgrades;
    }
}
