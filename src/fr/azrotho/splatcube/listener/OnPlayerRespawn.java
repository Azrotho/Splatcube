package fr.azrotho.splatcube.listener;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatMaps;
import fr.azrotho.splatcube.object.SplatPlayer;
import fr.azrotho.splatcube.utils.ManageWeapon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerRespawn implements Listener {
    @EventHandler
    public static void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        SplatPlayer spPlayer = SplatPlayer.getSplatPlayer(player);
        SplatMaps spMap = SplatMaps.getMap(Splatcube.map);
        if(spPlayer.getTeam().equals("blue")) {
            event.setRespawnLocation(spMap.getBlueSpawn());
        }
        if(spPlayer.getTeam().equals("orange")) {
            event.setRespawnLocation(spMap.getOrangeSpawn());
        }
        player.getInventory().clear();
        player.getInventory().addItem(ManageWeapon.getWeaponItem(spPlayer.getWeapon()));
    }
}
