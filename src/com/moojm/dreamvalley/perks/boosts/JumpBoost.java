package com.moojm.dreamvalley.perks.boosts;

import com.moojm.dreamvalley.DreamValleyUpgradesPlugin;
import com.moojm.dreamvalley.object.UpgradeTown;
import com.moojm.dreamvalley.perks.Perk;
import com.moojm.dreamvalley.perks.PerkType;
import org.bukkit.Material;

import java.util.List;

public class JumpBoost extends Perk {

    private final String name = "Jump";
    private final PerkType type = PerkType.JUMP;
    private Material material;

    private static double tierOnePrice;
    private static double tierTwoPrice;
    private static double tierThreePrice;
    private final int maxTier = 3;

    private List<String> lore;

    public JumpBoost() {
        this.material = Material.getMaterial(DreamValleyUpgradesPlugin.getInstance().getConfig().getString("perks" + ".jump-boost" + ".item"));
        this.tierOnePrice = DreamValleyUpgradesPlugin.getInstance().getConfig().getDouble("perks" + ".jump-boost" + ".tier-one");
        this.tierTwoPrice = DreamValleyUpgradesPlugin.getInstance().getConfig().getDouble("perks" + ".jump-boost" + ".tier-two");
        this.tierThreePrice = DreamValleyUpgradesPlugin.getInstance().getConfig().getDouble("perks" + ".jump-boost" + ".tier-three");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PerkType getType() {
        return type;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public int getMaxTier() {
        return maxTier;
    }

    @Override
    public double getTierCost(int tier) {
        switch (tier) {
            case 1:
                return tierOnePrice;
            case 2:
                return tierTwoPrice;
            case 3:
                return tierThreePrice;
            default:
                return 0;
        }
    }
}
