package fr.azrotho.splatcube.runnable;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatPlayer;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class BossBarRunnable extends BukkitRunnable {
    @Override
    public void run() {
        if (!Splatcube.isStarted) return;
        for (Player player : Bukkit.getOnlinePlayers()) {
            BossBar bossBar = Splatcube.getBossBars().get(player.getUniqueId());
            SplatPlayer splatPlayer = SplatPlayer.getSplatPlayer(player);
            bossBar.setProgress(splatPlayer.getInk() / 100.0);
            bossBar.addPlayer(player);
        }
    }
}
