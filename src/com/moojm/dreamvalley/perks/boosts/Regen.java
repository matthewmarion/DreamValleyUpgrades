package com.moojm.dreamvalley.perks.boosts;

import com.moojm.dreamvalley.DreamValleyUpgradesPlugin;
import com.moojm.dreamvalley.perks.Perk;
import com.moojm.dreamvalley.perks.PerkType;
import org.bukkit.Material;


public class Regen extends Perk {

    private final String name = "Regen";
    private final PerkType type = PerkType.REGEN;
    private Material material;

    private static double tierOnePrice;
    private static double tierTwoPrice;
    private static double tierThreePrice;
    private final int maxTier = 3;
    
    public Regen() {
        this.material = Material.getMaterial(DreamValleyUpgradesPlugin.getInstance().getConfig().getString("perks" + ".regen" + ".item"));
        this.tierOnePrice = DreamValleyUpgradesPlugin.getInstance().getConfig().getDouble("perks" + ".regen" + ".tier-one");
        this.tierTwoPrice = DreamValleyUpgradesPlugin.getInstance().getConfig().getDouble("perks" + ".regen" + ".tier-two");
        this.tierThreePrice = DreamValleyUpgradesPlugin.getInstance().getConfig().getDouble("perks" + ".regen" + ".tier-three");
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
