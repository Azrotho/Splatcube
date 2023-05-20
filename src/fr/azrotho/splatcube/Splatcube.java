package fr.azrotho.splatcube;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import fr.azrotho.splatcube.commands.DebugCommand;
import fr.azrotho.splatcube.listener.*;
import fr.azrotho.splatcube.object.SplatMaps;
import fr.azrotho.splatcube.object.SplatPlayer;
import fr.azrotho.splatcube.object.SplatWeapon;
import fr.azrotho.splatcube.runnable.*;
import fr.azrotho.splatcube.utils.ManageMaps;
import fr.azrotho.splatcube.utils.ManageWeapon;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Splatcube extends JavaPlugin {

    private static Splatcube instance;
    private static List<SplatMaps> maps = new ArrayList<>();
    private static List<SplatPlayer> players = new ArrayList<>();
    private static List<SplatWeapon> weapons = new ArrayList<>();
    private static HashMap<UUID, Integer> cooldowns = new HashMap<>();
    private static HashMap<UUID, BossBar> bossBars = new HashMap<>();
    public static String map = "default";
    public static String tag = IridiumColorAPI.process("<GRADIENT:002BFF>§lSplatcube</GRADIENT:FF6F00> §f➤");
    public static Boolean isStarted = false;
    public static int timer = 360;
    @Override
    public void onEnable() {
        instance = this;
        ManageMaps.load();
        ManageWeapon.createWeapon();
        getLogger().info("Splatcube enabled");
        getCommand("debug").setExecutor(new DebugCommand());
        Bukkit.getPluginManager().registerEvents(new OnInteractWeapon(), this);
        Bukkit.getPluginManager().registerEvents(new OnProjectilHit(), this);
        Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getPluginManager().registerEvents(new ManageDamage(), this);
        Bukkit.getPluginManager().registerEvents(new PingServerListener(), this);
        Bukkit.getPluginManager().registerEvents(new OnInteractLobby(), this);
        Bukkit.getPluginManager().registerEvents(new OnMenuInteraction(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerRespawn(), this);
        SneakRunnable sneakRunnable = new SneakRunnable();
        sneakRunnable.runTaskTimer(this, 0, 2);
        CooldownManagerRunnable cooldownManagerRunnable = new CooldownManagerRunnable();
        cooldownManagerRunnable.runTaskTimer(this, 0, 1);
        TimerRunnable timerRunnable = new TimerRunnable();
        timerRunnable.runTaskTimer(this, 0, 20);
        BossBarRunnable bossBarRunnable = new BossBarRunnable();
        bossBarRunnable.runTaskTimer(this, 0, 4);
        StarterRunnable starterRunnable = new StarterRunnable();
        starterRunnable.runTaskTimer(this, 0, 20);
    }

    public void onDisable() {
        try {
            ManageMaps.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getLogger().info("Splatcube disabled");
    }

    public static Splatcube getInstance() {
        return instance;
    }

    public static List<SplatMaps> getMaps() {
        return maps;
    }

    public static List<SplatPlayer> getPlayers() {
        return players;
    }

    public static List<SplatWeapon> getWeapons() {
        return weapons;
    }

    public static HashMap<UUID, Integer> getCooldowns() {
        return cooldowns;
    }

    public static HashMap<UUID, BossBar> getBossBars() {
        return bossBars;
    }
}
