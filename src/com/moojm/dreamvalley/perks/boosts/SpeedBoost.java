package com.moojm.dreamvalley.perks.boosts;

import com.moojm.dreamvalley.DreamValleyUpgradesPlugin;
import com.moojm.dreamvalley.perks.Perk;
import com.moojm.dreamvalley.perks.PerkType;
import org.bukkit.Material;

import java.util.List;

public class SpeedBoost extends Perk {

    private final String name = "Speed";
    private final PerkType type = PerkType.SPEED;
    private Material material;

    private static double tierOnePrice;
    private static double tierTwoPrice;
    private static double tierThreePrice;
    private final int maxTier = 3;

    public SpeedBoost() {
        this.material = Material.getMaterial(DreamValleyUpgradesPlugin.getInstance().getConfig().getString("perks" + ".speed-boost" + ".item"));
        this.tierOnePrice = DreamValleyUpgradesPlugin.getInstance().getConfig().getDouble("perks" + ".speed-boost" + ".tier-one");
        this.tierTwoPrice = DreamValleyUpgradesPlugin.getInstance().getConfig().getDouble("perks" + ".speed-boost" + ".tier-two");
        this.tierThreePrice = DreamValleyUpgradesPlugin.getInstance().getConfig().getDouble("perks" + ".speed-boost" + ".tier-three");
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
