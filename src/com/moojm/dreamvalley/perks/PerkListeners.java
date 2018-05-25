package com.moojm.dreamvalley.perks;

import com.moojm.dreamvalley.DreamValleyUpgradesPlugin;
import com.moojm.dreamvalley.object.UpgradeTown;
import com.palmergames.bukkit.towny.event.PlayerEnterTownEvent;
import com.palmergames.bukkit.towny.event.PlayerLeaveTownEvent;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;

import java.util.*;

public class PerkListeners implements Listener {

    private static Set<UUID> playersInTown = new HashSet<>();

    @EventHandler
    public void on(PlayerEnterTownEvent event) {

        Player player = event.getPlayer();
        Town playersTown = getTown(player.getName());

        if (playersTown == null) {
            return;
        }

        Town enteredTown = event.getEnteredtown();
        if (!townIsPlayers(enteredTown, playersTown)) {
            return;
        }

        UpgradeTown upgradeTown = UpgradeTown.getTownFromName(playersTown.getName());
        playersInTown.add(player.getUniqueId());
        givePlayerEffects(player, upgradeTown);
    }

    @EventHandler
    public void on(PlayerLeaveTownEvent event) {
        Player player = event.getPlayer();
        Town playersTown = getTown(player.getName());

        if (playersTown == null) {
            return;
        }

        Town leftTown = event.getLefttown();
        if (!townIsPlayers(leftTown, playersTown)) {
            return;
        }
        playersInTown.remove(player.getUniqueId());
        clearPotionEffects(player);
    }

    @EventHandler
    public void on(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        Town town = getTown(player.getName());
        Location location = player.getLocation();
    }

    public void clearPotionEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    public void givePlayerEffects(Player player, UpgradeTown upgradeTown) {
        Set<PotionEffect> effects = upgradeTown.getEffectsFromTiers();
        for (PotionEffect effect : effects) {
            player.addPotionEffect(effect);
        }
    }

    public void givePlayersEffects() {
        for (UUID uuid : playersInTown) {
            Player player = Bukkit.getPlayer(uuid);
            Town town = getTown(player.getName());
            UpgradeTown upgradeTown = UpgradeTown.getTownFromName(town.getName());
            Set<PotionEffect> effects = upgradeTown.getEffectsFromTiers();
            for (PotionEffect effect : effects) {
                player.addPotionEffect(effect);
            }
        }
    }

    private boolean townIsPlayers(Town town, Town playersTown) {
        return town.getUuid().equals(playersTown.getUuid());
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

    public static Set<UUID> getPlayersInTown() {
        return playersInTown;
    }

}
