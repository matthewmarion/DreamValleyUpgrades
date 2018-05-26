package com.moojm.dreamvalley.object;

import com.moojm.dreamvalley.database.MySqlUpgradeRepository;
import com.moojm.dreamvalley.perks.Perk;
import com.moojm.dreamvalley.perks.PerkType;
import com.moojm.dreamvalley.utils.ConsoleUtils;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.logging.Level;

public class UpgradeTown {

    private static List<UpgradeTown> towns = new ArrayList<>();

    private String townName;
    private int speedTier;
    private int jumpTier;
    private int regenTier;
    private int saturationTier;
    private int strenthTier;

    private UpgradeTown(UpgradeTownBuilder builder) {
        this.townName = builder.townName;
        this.speedTier = builder.speedTier;
        this.jumpTier = builder.jumpTier;
        this.regenTier = builder.regenTier;
        this.saturationTier = builder.saturationTier;
        this.strenthTier = builder.strenthTier;
    }

    public static void setTowns(List<UpgradeTown> towns) {
        UpgradeTown.towns = towns;
    }

    public static UpgradeTown getTownFromName(String townName) {
        for (UpgradeTown town : towns) {
            if (town.getTownName().equals(townName)) {
                return town;
            }
        }
        return null;
    }

    public Set<PotionEffect> getEffectsFromTiers() {
        Set<PotionEffect> effects = new HashSet<>();
        if (speedTier > 0) {
            effects.add(new PotionEffect(PotionEffectType.SPEED, 5000000, speedTier - 1));
        }
        if (jumpTier > 0) {
            effects.add(new PotionEffect(PotionEffectType.JUMP, 5000000, jumpTier - 1));
        }
        if (regenTier > 0) {
            effects.add(new PotionEffect(PotionEffectType.REGENERATION, 5000000, regenTier - 1));
        }
        if (saturationTier > 0) {
            effects.add(new PotionEffect(PotionEffectType.SATURATION, 5000000, saturationTier - 1));
        }
        if (strenthTier > 0) {
            effects.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5000000, strenthTier - 1));
        }
        return effects;
    }

    public int getTierFromPerk(Perk perk) {
        switch (perk.getType()) {
            case SPEED:
                return speedTier;
            case JUMP:
                return jumpTier;
            case REGEN:
                return regenTier;
            case SATURATION:
                return saturationTier;
            case STRENGTH:
                return strenthTier;
            default:
                return 0;
        }
    }

    public String getTownName() {
        return townName;
    }

    public int getSpeedTier() {
        return speedTier;
    }

    public int getJumpTier() {
        return jumpTier;
    }

    public int getRegenTier() {
        return regenTier;
    }

    public int getSaturationTier() {
        return saturationTier;
    }

    public int getStrenthTier() {
        return strenthTier;
    }

    public static List<UpgradeTown> getTowns() {
        return towns;
    }

    public static final class UpgradeTownBuilder {

        private String townName;
        private int speedTier;
        private int jumpTier;
        private int regenTier;
        private int saturationTier;
        private int strenthTier;

        public UpgradeTownBuilder() {

        }

        public UpgradeTown build() {
            Objects.requireNonNull(townName, "townName");
            Objects.requireNonNull(speedTier, "speedTier");
            Objects.requireNonNull(jumpTier, "jumpTier");
            Objects.requireNonNull(regenTier, "regenTier");
            Objects.requireNonNull(saturationTier, "saturationTier");
            Objects.requireNonNull(strenthTier, "strenthTier");
            return new UpgradeTown(this);
        }

        public UpgradeTownBuilder setTownName(String townName) {
            this.townName = townName;
            return this;
        }

        public UpgradeTownBuilder setSpeedTier(int speedTier) {
            this.speedTier = speedTier;
            return this;
        }

        public UpgradeTownBuilder setJumpTier(int jumpTier) {
            this.jumpTier = jumpTier;
            return this;
        }

        public UpgradeTownBuilder setRegenTier(int regenTier) {
            this.regenTier = regenTier;
            return this;
        }

        public UpgradeTownBuilder setSaturationTier(int saturationTier) {
            this.saturationTier = saturationTier;
            return this;
        }

        public UpgradeTownBuilder setStrenthTier(int strenthTier) {
            this.strenthTier = strenthTier;
            return this;
        }
    }

}
