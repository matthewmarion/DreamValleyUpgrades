package com.moojm.dreamvalley.gui;

import com.moojm.dreamvalley.DreamValleyUpgradesPlugin;
import com.moojm.dreamvalley.command.UpgradeCommand;
import com.moojm.dreamvalley.database.MySqlUpgradeRepository;
import com.moojm.dreamvalley.object.UpgradeTown;
import com.moojm.dreamvalley.perks.Perk;
import com.moojm.dreamvalley.perks.PerkFactory;
import com.moojm.dreamvalley.perks.boosts.SpeedBoost;
import com.moojm.dreamvalley.utils.ChatUtil;
import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import org.bukkit.Bukkit;
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
        Town town = getTown(player.getName());

        if (!playerIsMayor(player, town)) {
            event.setCancelled(true);
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

        if (item.getType() == Material.STAINED_GLASS_PANE) {
            event.setCancelled(true);
            return;
        }

        String displayName = item.getItemMeta().getDisplayName();
        Perk selectedPerk = PerkFactory.getPerk(displayName);

        if (selectedPerk == null) {
            return;
        }

        UpgradeTown upgradeTown = UpgradeTown.getTownFromName(town.getName());
        if (!canUpgrade(selectedPerk, upgradeTown)) {
            event.setCancelled(true);
            return;
        }

        double townBalance = getTownBalance(player.getName());
        double perkPrice = getPerkPrice(selectedPerk, upgradeTown);

        if (!townCanAfford(townBalance, perkPrice)) {
            player.sendMessage(ChatUtil.toColor("&cInsufficient balance to purchase this upgrade."));
            event.setCancelled(true);
            return;
        }

        withdrawBalance(town, perkPrice);
        upgradePerk(upgradeTown, selectedPerk);

        event.setCancelled(true);
        player.closeInventory();
        player.sendMessage(ChatUtil.toColor("&l&aSUCCESS &r&eTransaction for &b" + selectedPerk.getName() + " &ehas completed."));
    }

    private boolean playerIsMayor(Player player, Town town) {
        try {
            Resident resident = TownyUniverse.getDataSource().getResident(player.getName());
            return town.isMayor(resident);
        } catch (NotRegisteredException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean canUpgrade(Perk perk, UpgradeTown upgradeTown) {
        int tier = upgradeTown.getTierFromPerk(perk);
        if (tier >= perk.getMaxTier()) {
            return false;
        }
        return true;
    }

    private void upgradePerk(UpgradeTown upgradeTown, Perk perk) {
        int tier = upgradeTown.getTierFromPerk(perk) + 1;
        MySqlUpgradeRepository mySqlUpgradeRepository = new MySqlUpgradeRepository();
        mySqlUpgradeRepository.update(perk.getName().toLowerCase(), upgradeTown, tier);
    }

    private void withdrawBalance(Town town, double perkPrice) {
        try {
            town.setBalance(town.getHoldingBalance() - perkPrice, "Purchased town upgrade.");
        } catch (EconomyException e) {
            e.printStackTrace();
        }
    }

    private boolean townCanAfford(double townBalance, double perkPrice) {
        if (townBalance < perkPrice) {
            return false;
        }
        return true;
    }

    private double getPerkPrice(Perk perk, UpgradeTown town) {
        int tier = LoreUtils.getNextTier(perk, town);
        return perk.getTierCost(tier);
    }

    private Town getTown(String player) {
        try {
            Resident resident = TownyUniverse.getDataSource().getResident(player);
            Town town = resident.getTown();
            return town;
        } catch (NotRegisteredException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double getTownBalance(String player) {
        Town town = this.getTown(player);
        try {
            return town.getHoldingBalance();
        } catch (EconomyException e) {
            e.printStackTrace();
        }
        return -1;
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
