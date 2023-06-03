package fr.azrotho.splatcube.listener;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatPlayer;
import fr.azrotho.splatcube.object.SplatWeapon;
import fr.azrotho.splatcube.utils.ManageWeapon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.haoshoku.nick.api.NickAPI;

public class OnMenuInteraction implements Listener {
    @EventHandler
    public static void onInventory(InventoryClickEvent event) {
        if(event.getView().getTitle().equals("§9Choisissez une équipe")) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null) return;
            if(event.getCurrentItem().getItemMeta() == null) return;
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§9Equipe bleue")) {
                event.getWhoClicked().sendMessage("§9Vous avez rejoint l'équipe bleue !");
                SplatPlayer spPlayer = SplatPlayer.getSplatPlayer((Player) event.getWhoClicked());
                spPlayer.setTeam("blue");
                NickAPI.nick((Player) event.getWhoClicked(), "§9" + event.getWhoClicked().getName());
                NickAPI.refreshPlayer((Player) event.getWhoClicked());
                event.getWhoClicked().closeInventory();
            }
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Equipe orange")) {
                event.getWhoClicked().sendMessage("§6Vous avez rejoint l'équipe orange !");
                SplatPlayer spPlayer = SplatPlayer.getSplatPlayer((Player) event.getWhoClicked());
                spPlayer.setTeam("orange");
                NickAPI.nick((Player) event.getWhoClicked(), "§6" + event.getWhoClicked().getName());
                NickAPI.refreshPlayer((Player) event.getWhoClicked());
                event.getWhoClicked().closeInventory();
            }
        }
        if(event.getView().getTitle().equals("§9Choisissez une arme")) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null) return;
            if(event.getCurrentItem().getItemMeta() == null) return;
            event.getWhoClicked().sendMessage("§9Vous avez choisi l'arme " + event.getCurrentItem().getItemMeta().getDisplayName());
            SplatPlayer spPlayer = SplatPlayer.getSplatPlayer((Player) event.getWhoClicked());
            SplatWeapon spWeapon = ManageWeapon.getWeaponByDisplayName(event.getCurrentItem().getItemMeta().getDisplayName());
            spPlayer.setWeapon(spWeapon.getName());
            event.getWhoClicked().closeInventory();
        }
    }
}
