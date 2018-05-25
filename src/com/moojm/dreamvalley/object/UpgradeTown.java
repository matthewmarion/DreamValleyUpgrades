package com.moojm.dreamvalley.object;

import com.moojm.dreamvalley.database.MySqlUpgradeRepository;
import com.moojm.dreamvalley.perks.Perk;
import com.moojm.dreamvalley.perks.PerkType;
import com.moojm.dreamvalley.utils.ConsoleUtils;

import java.util.Objects;
import java.util.logging.Level;

public class UpgradeTown {

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

    public static UpgradeTown getTownFromName(String townName) {
        MySqlUpgradeRepository repository = new MySqlUpgradeRepository();
        UpgradeTown town = repository.get(townName);
        try {
            return town;
        } catch (NullPointerException e) {
            ConsoleUtils.log(Level.WARNING, "Town does not exist.");
        }
        return null;
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
