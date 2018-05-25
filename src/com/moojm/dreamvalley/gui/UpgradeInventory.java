package com.moojm.dreamvalley.gui;

import com.moojm.dreamvalley.DreamValleyUpgradesPlugin;
import com.moojm.dreamvalley.object.UpgradeTown;
import com.moojm.dreamvalley.perks.*;
import com.moojm.dreamvalley.perks.boosts.SpeedBoost;
import com.moojm.dreamvalley.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class UpgradeInventory {

    private Inventory inventory;
    private String townName;

    public UpgradeInventory(String townName) {
        this.townName = townName;
        UpgradeTown town = UpgradeTown.getTownFromName(townName);
        String inventoryName = ChatUtil.toColor("&9" + townName + "'s upgrades");
        inventory = Bukkit.createInventory(null, 27, inventoryName);

        ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11);
        ItemMeta meta = border.getItemMeta();
        meta.setDisplayName(" ");
        border.setItemMeta(meta);

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, border);
        }

        Material speedMaterial = Material.getMaterial(DreamValleyUpgradesPlugin.getInstance().getConfig().getString("perks" + ".speed-boost" + ".item"));
        ItemStack speed = new UpgradeItem.UpgradeItemBuilder().setMaterial(speedMaterial).setDisplayName("Speed Boost").setType(PerkType.SPEED).setTown(town).build().toItemStack();
        inventory.setItem(11, speed);

        Material jumpMaterial = Material.getMaterial(DreamValleyUpgradesPlugin.getInstance().getConfig().getString("perks" + ".jump-boost" + ".item"));
        ItemStack jump = new UpgradeItem.UpgradeItemBuilder().setMaterial(jumpMaterial).setDisplayName("Jump Boost").setType(PerkType.JUMP).setTown(town).build().toItemStack();
        inventory.setItem(12, jump);

        Material regenMaterial = Material.getMaterial(DreamValleyUpgradesPlugin.getInstance().getConfig().getString("perks" + ".regen" + ".item"));
        ItemStack regen = new UpgradeItem.UpgradeItemBuilder().setMaterial(regenMaterial).setDisplayName("Regen").setType(PerkType.REGEN).setTown(town).build().toItemStack();
        inventory.setItem(13, regen);

        Material saturationMaterial = Material.getMaterial(DreamValleyUpgradesPlugin.getInstance().getConfig().getString("perks" + ".saturation" + ".item"));
        ItemStack saturation = new UpgradeItem.UpgradeItemBuilder().setMaterial(saturationMaterial).setDisplayName("Saturation").setType(PerkType.SATURATION).setTown(town).build().toItemStack();
        inventory.setItem(14, saturation);

        Material strengthMaterial = Material.getMaterial(DreamValleyUpgradesPlugin.getInstance().getConfig().getString("perks" + ".strength" + ".item"));
        ItemStack strength = new UpgradeItem.UpgradeItemBuilder().setMaterial(strengthMaterial).setDisplayName("Strength").setType(PerkType.STRENGTH).setTown(town).build().toItemStack();
        inventory.setItem(15, strength);
    }
    public Inventory getInventory() {
        return inventory;
    }
}
