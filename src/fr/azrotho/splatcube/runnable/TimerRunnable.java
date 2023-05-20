package fr.azrotho.splatcube.runnable;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.utils.EndFunction;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerRunnable extends BukkitRunnable {
    @Override
    public void run() {
        if(!Splatcube.isStarted) return;
        Splatcube.timer--;
        if(Splatcube.timer == 0) {
            EndFunction.end();
            this.cancel();
        }
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new net.md_5.bungee.api.chat.TextComponent("§9Temps restant: §b" + Splatcube.timer / 60 + "§9m§b" + Splatcube.timer % 60 + "§9s"));
        }
    }
}
