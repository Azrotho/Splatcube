package fr.azrotho.splatcube.listener;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatPlayer;
import fr.azrotho.splatcube.object.SplatWeapon;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.block.data.type.Snow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;


public class OnInteractWeapon implements Listener {
    @EventHandler
    public static void onInteract(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(event.getItem().getType() == Material.STICK) {
            SplatPlayer sp = SplatPlayer.getSplatPlayer(event.getPlayer());
            if(sp == null) return;
            Player player = event.getPlayer();
            SplatWeapon sw = SplatWeapon.getSplatWeapon(sp.getWeapon());
            if(sw == null) return;
            if(Splatcube.getCooldowns().containsKey(event.getPlayer().getUniqueId())) {
                return;
            }
            if(sp.getInk() < sw.getInkCost()) {
                event.getPlayer().sendMessage("§cVous n'avez pas assez d'encre");
                return;
            }
            Splatcube.getCooldowns().put(event.getPlayer().getUniqueId(), sw.getCooldown());
            sp.setInk(sp.getInk() - sw.getInkCost());
            switch(sp.getWeapon()) {
                case "liquidateur":
                    Snowball snowballL = player.launchProjectile(Snowball.class);
                    snowballL.setVelocity(player.getLocation().getDirection().multiply(3));
                    break;
                case "rouleau":
                    Snowball snowballR = player.launchProjectile(Snowball.class);

                    // Vecteur vers le sol
                    Vector v = new Vector(0.1, -1, 0);
                    snowballR.setVelocity((v).multiply(5));
                    break;
                case "blaster":
                    Snowball snowballB = player.launchProjectile(Snowball.class);
                    snowballB.setVelocity(player.getLocation().getDirection().multiply(2));
                    break;
                case "badigeonneur":
                    Snowball snowballBa1 = player.launchProjectile(Snowball.class);
                    snowballBa1.setVelocity(player.getLocation().getDirection().multiply(2));
                    Snowball snowballBa2 = player.launchProjectile(Snowball.class);
                    snowballBa2.setVelocity(player.getLocation().getDirection().multiply(2.2));
                    Snowball snowballBa3 = player.launchProjectile(Snowball.class);
                    snowballBa3.setVelocity(player.getLocation().getDirection().multiply(1.8));
                    break;
                case "concentrateur":
                    Snowball snowballC = player.launchProjectile(Snowball.class);
                    snowballC.setVelocity(player.getLocation().getDirection().multiply(5));
                    break;
            }



        }
    }
}
