package com.moojm.dreamvalley.perks;

import org.bukkit.Material;

public abstract class Perk {

    public abstract String getName();
    public abstract PerkType getType();
    public abstract Material getMaterial();
    public abstract int getMaxTier();
    public abstract double getTierCost(int tier);

}
