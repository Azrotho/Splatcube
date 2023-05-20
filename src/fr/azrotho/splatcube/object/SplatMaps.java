package fr.azrotho.splatcube.object;

import fr.azrotho.splatcube.Splatcube;
import org.bukkit.Location;

public class SplatMaps {
    private Location spawn;
    private Location orangeSpawn;
    private Location blueSpawn;
    private String name;
    private int radius;

    public SplatMaps(Location spawn, Location orangeSpawn, Location blueSpawn, int radius, String name) {
        this.spawn = spawn;
        this.orangeSpawn = orangeSpawn;
        this.blueSpawn = blueSpawn;
        this.radius = radius;
        this.name = name;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public Location getOrangeSpawn() {
        return orangeSpawn;
    }

    public void setOrangeSpawn(Location orangeSpawn) {
        this.orangeSpawn = orangeSpawn;
    }

    public Location getBlueSpawn() {
        return blueSpawn;
    }

    public void setBlueSpawn(Location blueSpawn) {
        this.blueSpawn = blueSpawn;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SplatMaps getMap(String name) {
        for(SplatMaps maps : Splatcube.getMaps()) {
            if(maps.getName().equalsIgnoreCase(name)) {
                return maps;
            }
        }
        return null;
    }
}
