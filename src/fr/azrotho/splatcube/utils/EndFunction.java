package fr.azrotho.splatcube.utils;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatMaps;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class EndFunction extends BukkitRunnable {


    int timerBeforeStop = 20;

    public static void end() {
        Splatcube.isStarted = true;
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.setGameMode(GameMode.SPECTATOR);
            SplatMaps splatMaps = SplatMaps.getMap(Splatcube.map);
            p.teleport(splatMaps.getSpawn().setDirection(new Vector(0,-1,0)));
            p.sendTitle("§6Fin de la partie", "§6Comptage des blocs...", 20, 60, 20);
        }
        HashMap<String, Integer> blocks = countBlocks();
        int percentBlue = (int) ((blocks.get("blue") * 100) / (blocks.get("blue") + blocks.get("orange")));
        int percentOrange = (int) ((blocks.get("orange") * 100) / (blocks.get("blue") + blocks.get("orange")));
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle("§6" + percentOrange + "% §f- §9" + percentBlue + "%", "§6" + blocks.get("orange") + " §f- §9" + blocks.get("blue"), 20, 60, 20);
        }
        if(blocks.get("orange") > blocks.get("blue")) {
            Bukkit.broadcastMessage(Splatcube.tag  + " §6L'équipe orange a gagné !");
        } else if(blocks.get("orange") < blocks.get("blue")) {
            Bukkit.broadcastMessage(Splatcube.tag + " §6L'équipe bleue a gagné !");
        } else {
            Bukkit.broadcastMessage(Splatcube.tag + " §6Egalité !");
        }
        EndFunction endFunction = new EndFunction();
        endFunction.runTaskTimer(Splatcube.getInstance(), 0, 20);
    }


    public static HashMap<String, Integer> countBlocks() {
        SplatMaps maps = SplatMaps.getMap(Splatcube.map);
        int orange = 0;
        int blue = 0;
        World world = Bukkit.getWorld("world");
        for(int x = maps.getSpawn().getBlockX() - maps.getRadius(); x < maps.getSpawn().getBlockX() + maps.getRadius(); x++) {
            for(int y = maps.getSpawn().getBlockY() - maps.getRadius(); y < maps.getSpawn().getBlockY() + maps.getRadius(); y++) {
                for(int z = maps.getSpawn().getBlockZ() - maps.getRadius(); z < maps.getSpawn().getBlockZ() + maps.getRadius(); z++) {
                    if(world.getBlockAt(x, y, z).getType().equals(Material.ORANGE_CONCRETE)) {
                        orange++;
                    }
                    if(world.getBlockAt(x, y, z).getType().equals(Material.BLUE_CONCRETE)) {
                        blue++;
                    }
                }
            }
        }
        HashMap<String, Integer> blocks = new HashMap<>();
        blocks.put("orange", orange);
        blocks.put("blue", blue);
        return blocks;
    }

    @Override
    public void run() {
        if(timerBeforeStop == 0) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.kickPlayer("§6Redémarrage du serveur");
            }
            Bukkit.shutdown();
        }
        timerBeforeStop--;
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new net.md_5.bungee.api.chat.TextComponent("§9Redémarrage dans §b" + timerBeforeStop + "§9s"));
        }
    }
}
