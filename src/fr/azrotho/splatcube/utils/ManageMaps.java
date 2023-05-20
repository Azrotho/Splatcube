package fr.azrotho.splatcube.utils;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatMaps;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ManageMaps {
    public static void save() throws IOException {
        for(SplatMaps splatMaps : Splatcube.getMaps()) {
            YamlConfiguration yamlConfiguration = new YamlConfiguration();
            yamlConfiguration.set("spawn.x" , splatMaps.getSpawn().getX());
            yamlConfiguration.set("spawn.y", splatMaps.getSpawn().getY());
            yamlConfiguration.set("spawn.z", splatMaps.getSpawn().getZ());
            yamlConfiguration.set("spawn.yaw", splatMaps.getSpawn().getYaw());
            yamlConfiguration.set("spawn.pitch", splatMaps.getSpawn().getPitch());
            yamlConfiguration.set("orangeSpawn.x", splatMaps.getOrangeSpawn().getX());
            yamlConfiguration.set("orangeSpawn.y", splatMaps.getOrangeSpawn().getY());
            yamlConfiguration.set("orangeSpawn.z", splatMaps.getOrangeSpawn().getZ());
            yamlConfiguration.set("orangeSpawn.yaw", splatMaps.getOrangeSpawn().getYaw());
            yamlConfiguration.set("orangeSpawn.pitch", splatMaps.getOrangeSpawn().getPitch());
            yamlConfiguration.set("orangeSpawn.world", splatMaps.getOrangeSpawn().getWorld().getName());
            yamlConfiguration.set("blueSpawn.x", splatMaps.getBlueSpawn().getX());
            yamlConfiguration.set("blueSpawn.y", splatMaps.getBlueSpawn().getY());
            yamlConfiguration.set("blueSpawn.z", splatMaps.getBlueSpawn().getZ());
            yamlConfiguration.set("blueSpawn.yaw", splatMaps.getBlueSpawn().getYaw());
            yamlConfiguration.set("blueSpawn.pitch", splatMaps.getBlueSpawn().getPitch());
            yamlConfiguration.set("radius", splatMaps.getRadius());
            yamlConfiguration.set("name", splatMaps.getName());

            yamlConfiguration.save(Splatcube.getInstance().getDataFolder() + "/maps/" + splatMaps.getName() + ".yml");
        }
    }

    public static void load() {
        File file = new File(Splatcube.getInstance().getDataFolder() + "/maps/");
        if(!file.exists()) {
            file.mkdirs();
        }
        for(File file1 : file.listFiles()) {
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file1);

            String name = yamlConfiguration.getString("name");
            int radius = yamlConfiguration.getInt("radius");
            Location spawn = new Location(Splatcube.getInstance().getServer().getWorld("world"), yamlConfiguration.getDouble("spawn.x"), yamlConfiguration.getDouble("spawn.y"), yamlConfiguration.getDouble("spawn.z"), (float) yamlConfiguration.getDouble("spawn.yaw"), (float) yamlConfiguration.getDouble("spawn.pitch"));
            Location blueSpawn = new Location(Splatcube.getInstance().getServer().getWorld("world"), yamlConfiguration.getDouble("blueSpawn.x"), yamlConfiguration.getDouble("blueSpawn.y"), yamlConfiguration.getDouble("blueSpawn.z"), (float) yamlConfiguration.getDouble("blueSpawn.yaw"), (float) yamlConfiguration.getDouble("blueSpawn.pitch"));
            Location orangeSpawn = new Location(Splatcube.getInstance().getServer().getWorld("world"), yamlConfiguration.getDouble("orangeSpawn.x"), yamlConfiguration.getDouble("orangeSpawn.y"), yamlConfiguration.getDouble("orangeSpawn.z"), (float) yamlConfiguration.getDouble("orangeSpawn.yaw"), (float) yamlConfiguration.getDouble("orangeSpawn.pitch"));

            SplatMaps splatMaps = new SplatMaps(spawn, orangeSpawn, blueSpawn, radius, name);
            Splatcube.getMaps().add(splatMaps);
        }
    }

}
