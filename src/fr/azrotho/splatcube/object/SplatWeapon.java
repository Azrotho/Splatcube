package fr.azrotho.splatcube.object;

import fr.azrotho.splatcube.Splatcube;

public class SplatWeapon {
    private String name;
    private String displayName;
    private int inkCost;
    private int damage;
    private int radiusPaint;
    private int radiusDamage;
    private int cooldown;
    private int customModelData;

    public SplatWeapon(String name, String displayName, int inkCost, int damage, int radiusPaint, int radiusDamage, int cooldown, int customModelData) {
        this.name = name;
        this.displayName = displayName;
        this.inkCost = inkCost;
        this.damage = damage;
        this.radiusPaint = radiusPaint;
        this.radiusDamage = radiusDamage;
        this.cooldown = cooldown;
        this.customModelData = customModelData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getInkCost() {
        return inkCost;
    }

    public void setInkCost(int inkCost) {
        this.inkCost = inkCost;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRadiusPaint() {
        return radiusPaint;
    }

    public void setRadiusPaint(int radiusPaint) {
        this.radiusPaint = radiusPaint;
    }

    public int getRadiusDamage() {
        return radiusDamage;
    }

    public void setRadiusDamage(int radiusDamage) {
        this.radiusDamage = radiusDamage;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public void setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
    }

    public static SplatWeapon getSplatWeapon(String name) {
        for (SplatWeapon splatWeapon : Splatcube.getWeapons()) {
            if (splatWeapon.getName().equalsIgnoreCase(name)) {
                return splatWeapon;
            }
        }
        return null;
    }
}
