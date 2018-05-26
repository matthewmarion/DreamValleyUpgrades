package com.moojm.dreamvalley.database;

import com.moojm.dreamvalley.object.UpgradeTown;

import java.util.List;

public interface UpgradeRespository {

    void add(UpgradeTown town);
    UpgradeTown get(String townName);
    List<UpgradeTown> getAll();
    void update(String perk, UpgradeTown upgradeTown, int tier);
}
