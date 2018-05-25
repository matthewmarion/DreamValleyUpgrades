package com.moojm.dreamvalley.gui;

import com.moojm.dreamvalley.command.UpgradeCommand;
import com.moojm.dreamvalley.perks.Perk;
import com.moojm.dreamvalley.perks.PerkFactory;
import com.moojm.dreamvalley.perks.boosts.SpeedBoost;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class UpgradeListeners implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!isUpgradeInventory(player)) {
            return;
        }
        if (event.getRawSlot() >= event.getInventory().getSize()) {
            event.setCancelled(true);
            return;
        }

        ItemStack item = event.getCurrentItem();
        if(item == null || item.getType() == Material.AIR) {
            return;
        }

        String displayName = item.getItemMeta().getDisplayName();
        Perk selectedPerk = PerkFactory.getPerk(displayName);

        if (selectedPerk == null) {
            return;
        }

        player.closeInventory();
        player.sendMessage("You have selected: " + selectedPerk.getName());


    }

    @EventHandler
    public void on(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (!isUpgradeInventory(player)) {
            return;
        }
        removeOpenUpgrade(player);
    }

    private void removeOpenUpgrade(Player player) {
        UpgradeCommand.getOpenUpgrades().remove(player.getUniqueId());
    }


    private boolean isUpgradeInventory(Player player) {
        return UpgradeCommand.getOpenUpgrades().contains(player.getUniqueId());
    }
}
