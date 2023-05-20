package fr.azrotho.splatcube.listener;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatPlayer;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnJoin implements Listener {
    @EventHandler
    public static void onJoin(PlayerJoinEvent event) {
        if(Splatcube.isStarted) {
            event.getPlayer().kickPlayer("§cLa partie a déjà commencé !");
            event.setJoinMessage(null);
            return;
        }

        event.setJoinMessage("§7(§a+§7) §a" + event.getPlayer().getName());
        SplatPlayer spPlayer = SplatPlayer.createPlayer(event.getPlayer().getName(), event.getPlayer().getUniqueId());
        Splatcube.getPlayers().add(spPlayer);

        event.getPlayer().getInventory().clear();

        ItemStack banner = new ItemStack(new ItemStack(Material.BLACK_BANNER));
        ItemMeta bannerMeta = banner.getItemMeta();
        bannerMeta.setDisplayName("§6Sélecteur d'équipe");
        banner.setItemMeta(bannerMeta);
        event.getPlayer().getInventory().setItem(0, banner);

        ItemStack weapon = new ItemStack(new ItemStack(Material.NETHER_STAR));
        ItemMeta weaponMeta = weapon.getItemMeta();
        weaponMeta.setDisplayName("§6Sélecteur d'arme");
        weapon.setItemMeta(weaponMeta);
        event.getPlayer().getInventory().setItem(4, weapon);
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
        event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), 0, 200, 0));
    }
}
