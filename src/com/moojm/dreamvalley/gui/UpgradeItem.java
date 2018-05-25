package com.moojm.dreamvalley.gui;

import com.moojm.dreamvalley.object.UpgradeTown;
import com.moojm.dreamvalley.perks.PerkFactory;
import com.moojm.dreamvalley.perks.PerkType;
import com.moojm.dreamvalley.utils.ChatUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class UpgradeItem {

    private Material material;
    private String displayName;
    private PerkType type;
    private UpgradeTown town;

    public UpgradeItem(UpgradeItemBuilder builder) {
        this.material = builder.material;
        this.displayName = builder.displayName;
        this.type = builder.type;
        this.town = builder.town;
    }

    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatUtil.toColor("&f&l[ &r&b" + displayName + " &l&f]"));
        List<String> lore = LoreUtils.buildLore(PerkFactory.getPerk(type), town);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static final class UpgradeItemBuilder {

        private Material material;
        private String displayName;
        private List<String> lore;
        private PerkType type;
        private UpgradeTown town;

        public UpgradeItemBuilder() {

        }

        public UpgradeItem build() {
            Objects.requireNonNull(material, "material");
            Objects.requireNonNull(displayName, "displayName");
            Objects.requireNonNull(type, "type");
            Objects.requireNonNull(town, "town");
            return new UpgradeItem(this);
        }

        public UpgradeItemBuilder setMaterial(Material material) {
            this.material = material;
            return this;
        }

        public UpgradeItemBuilder setDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public UpgradeItemBuilder setType(PerkType type) {
            this.type = type;
            return this;
        }

        public UpgradeItemBuilder setTown(UpgradeTown town) {
            this.town = town;
            return this;
        }
    }
}
