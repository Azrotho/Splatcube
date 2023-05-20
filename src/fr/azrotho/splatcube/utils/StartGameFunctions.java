package fr.azrotho.splatcube.utils;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatMaps;
import fr.azrotho.splatcube.object.SplatPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.api.NickAPI;

public class StartGameFunctions {
    public static void start() {
            SplatMaps splatMaps = Splatcube.getMaps().get(0);
            for(SplatMaps splatMapst : Splatcube.getMaps()) {
                if(splatMapst.getName().equals(Splatcube.map)) {
                    splatMaps = splatMapst;
                }
            }
            Splatcube.map = splatMaps.getName();
            for(World world: Bukkit.getWorlds()) {
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                world.setTime(6000);
                world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                world.setStorm(false);
                world.setThundering(false);
                world.setGameRule(GameRule.KEEP_INVENTORY, true);
            }
        for(Player player : Bukkit.getOnlinePlayers()) {
            SplatPlayer splatPlayer = SplatPlayer.getSplatPlayer(player);
            player.getInventory().clear();
            if(splatPlayer.getTeam().equals("blue")) {
                BossBar bossBar = Bukkit.createBossBar("§1§lEncre bleue", org.bukkit.boss.BarColor.BLUE, org.bukkit.boss.BarStyle.SOLID);
                bossBar.setProgress(splatPlayer.getInk() / 100.0);
                Splatcube.getBossBars().put(player.getUniqueId(), bossBar);
                bossBar.addPlayer(player);
                player.teleport(splatMaps.getBlueSpawn());
                NickAPI.nick(player, "§9" + player.getName());
                NickAPI.refreshPlayer(player);
            }
            if(splatPlayer.getTeam().equals("orange")) {
                BossBar bossBar = Bukkit.createBossBar("§6§lEncre orange", org.bukkit.boss.BarColor.RED, org.bukkit.boss.BarStyle.SOLID);
                bossBar.setProgress(splatPlayer.getInk() / 100.0);
                Splatcube.getBossBars().put(player.getUniqueId(), bossBar);
                bossBar.addPlayer(player);
                player.teleport(splatMaps.getOrangeSpawn());
                NickAPI.nick(player, "§6" + player.getName());
                NickAPI.refreshPlayer(player);
            }
            player.setGameMode(org.bukkit.GameMode.ADVENTURE);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setExp(0);
            player.setLevel(0);
            player.getInventory().addItem(ManageWeapon.getWeaponItem(splatPlayer.getWeapon()));
        }
    }
}
