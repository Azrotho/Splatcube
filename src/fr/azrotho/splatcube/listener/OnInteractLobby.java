package fr.azrotho.splatcube.listener;

import fr.azrotho.splatcube.utils.Menus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnInteractLobby implements Listener {
    @EventHandler
    public static void onInteractLobby(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(event.getItem().getItemMeta() == null) return;
        if(event.getItem().getItemMeta().getDisplayName().equals("§6Sélecteur d'équipe")) {
            Menus.openTeamMenu(event.getPlayer());
        }
        if(event.getItem().getItemMeta().getDisplayName().equals("§6Sélecteur d'arme")) {
            Menus.openWeaponMenu(event.getPlayer());
        }
    }
}
