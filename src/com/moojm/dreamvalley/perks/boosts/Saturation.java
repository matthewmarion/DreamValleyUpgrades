package com.moojm.dreamvalley.perks.boosts;

import com.moojm.dreamvalley.DreamValleyUpgradesPlugin;
import com.moojm.dreamvalley.perks.Perk;
import com.moojm.dreamvalley.perks.PerkType;
import org.bukkit.Material;

public class Saturation extends Perk {

    private final String name = "Saturation";
    private final PerkType type = PerkType.SATURATION;
    private Material material;

    private static double tierOnePrice;
    private final int maxTier = 1;

    public Saturation() {
        this.material = Material.getMaterial(DreamValleyUpgradesPlugin.getInstance().getConfig().getString("perks" + ".saturation" + ".item"));
        this.tierOnePrice = DreamValleyUpgradesPlugin.getInstance().getConfig().getDouble("perks" + ".saturation" + ".tier-one");
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
            default:
                return 0;
        }
    }
}
