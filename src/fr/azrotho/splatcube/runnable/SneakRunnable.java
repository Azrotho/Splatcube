package fr.azrotho.splatcube.runnable;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatPlayer;
import fr.azrotho.splatcube.utils.TeamFunctions;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SneakRunnable extends BukkitRunnable {
    @Override
    public void run() {
        if(!Splatcube.isStarted) return;
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.isSneaking()) {
                if(player.getLocation().add(0, -1, 0).getBlock().getType().equals(TeamFunctions.getBlock(player))) {
                    if(player.hasPotionEffect(PotionEffectType.SPEED)) {
                        player.removePotionEffect(PotionEffectType.SPEED);
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*2, 4, false, false, false));
                    if(player.hasPotionEffect(PotionEffectType.JUMP)) {
                        player.removePotionEffect(PotionEffectType.JUMP);
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 5, false, false, false));
                    if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                        player.removePotionEffect(PotionEffectType.INVISIBILITY);
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*2, 1, false, false, false));
                    SplatPlayer sp = SplatPlayer.getSplatPlayer(player);
                    if(sp == null) continue;
                    sp.setInk(sp.getInk() + 5);
                    if(sp.getInk() > 100) sp.setInk(100);
                }
            } else {
                if(player.getLocation().add(0, -1, 0).getBlock().getType().equals(TeamFunctions.getBlock(player))) {
                    if(player.hasPotionEffect(PotionEffectType.SPEED)) {
                        player.removePotionEffect(PotionEffectType.SPEED);
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*2, 0, false, false, false));
                }
            }
        }
    }
}
