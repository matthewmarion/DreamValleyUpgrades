# DreamValleyUpgrades

![Showcase](https://i.gyazo.com/ce2abec17ee14ddd917b3015b371b34c.png)
![Showcase](https://i.imgur.com/1yaWTP6.gif)

DreamValleyUpgrades is a plugin made for a private client that allows Towny mayors to upgrade their town with different potion effects.
Mayors can purchase different upgrades using the money allocated in their town bank. 

## Features
* Organized GUI
* Purchase validation
* Ability to add additional tiers in config
* Auto-generated upgrade item lore
* MySQL

## Dependencies
* Towny 0.92.0.1 or newer
* MySQL

## Commands
* /towny upgrades

## Configuration
Default configuration file:
```YAML
mysql:
  hostname: 
  port: 
  database: 
  username: 
  password: 
perks:
  speed-boost:
    item: SUGAR
    tier-one: 50
    tier-two: 100
    tier-three: 200
  jump-boost:
    item: FEATHER
    tier-one: 2000
    tier-two: 5000
    tier-three: 10000
  regen:
    item: REDSTONE
    tier-one: 50
    tier-two: 100
    tier-three: 200
  saturation:
    item: GOLDEN_CARROT
    tier-one: 50
  strength:
    item: BLAZE_POWDER
    tier-one: 50
    tier-two: 100
    tier-three: 200
    ```
