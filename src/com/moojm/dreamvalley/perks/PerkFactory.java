package com.moojm.dreamvalley.perks;

import com.moojm.dreamvalley.perks.boosts.*;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PerkFactory {

    public static Perk getPerk(PerkType type) {
        switch (type) {
            case SPEED:
                return new SpeedBoost();
            case JUMP:
                return new JumpBoost();
            case REGEN:
                return new Regen();
            case SATURATION:
                return new Saturation();
            case STRENGTH:
                return new Strength();
            default:
                return null;
        }
    }

    public static Perk getPerk(String displayName) {
        if (displayName.contains("Speed")) {
            return new SpeedBoost();
        }
        if (displayName.contains("Jump")) {
            return new JumpBoost();
        }
        if (displayName.contains("Regen")) {
            return new Regen();
        }
        if (displayName.contains("Saturation")) {
            return new Saturation();
        }
        if (displayName.contains("Strength")) {
            return new Strength();
        }
        return null;
    }
}
