package fr.azrotho.splatcube.listener;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnQuit implements Listener {
    @EventHandler
    public static void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        SplatPlayer spPlayer = SplatPlayer.getSplatPlayer(player);
        Splatcube.getPlayers().remove(spPlayer);
    }
}
