package fr.azrotho.splatcube.runnable;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatPlayer;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CooldownManagerRunnable extends BukkitRunnable {
    @Override
    public void run() {
        if(!Splatcube.isStarted) return;
        for(UUID uuid : Splatcube.getCooldowns().keySet()) {
            if(Splatcube.getCooldowns().get(uuid) > 0) {
                Splatcube.getCooldowns().put(uuid, Splatcube.getCooldowns().get(uuid) - 1);
            }
            if(Splatcube.getCooldowns().get(uuid) == 0) {
                Splatcube.getCooldowns().remove(uuid);
            }
        }
    }
}
