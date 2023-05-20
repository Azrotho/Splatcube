package fr.azrotho.splatcube.listener;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatPlayer;
import fr.azrotho.splatcube.object.SplatWeapon;
import fr.azrotho.splatcube.utils.ConstructionFunctions;
import fr.azrotho.splatcube.utils.TeamFunctions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Set;

public class OnProjectilHit implements Listener {
    @EventHandler
    public static void onProjectilHit(ProjectileHitEvent event) {
        if(event.getEntityType().equals(EntityType.SNOWBALL)) {
            SplatPlayer sp = SplatPlayer.getSplatPlayer((Player) event.getEntity().getShooter());
            if(sp == null) return;
            SplatWeapon sw = SplatWeapon.getSplatWeapon(sp.getWeapon());
            if(sw == null) return;
            Set<Location> sphere = ConstructionFunctions.sphere(event.getEntity().getLocation(), sw.getRadiusPaint(), false);
            for(Location l : sphere) {
                if(!l.getBlock().getType().equals(Material.AIR) && !l.getBlock().getType().equals(Material.BARRIER)) {
                    l.getBlock().setType(TeamFunctions.getBlock((Player) event.getEntity().getShooter()));
                }
            }
            for(Player p : event.getEntity().getWorld().getPlayers()) {
                SplatPlayer target = SplatPlayer.getSplatPlayer(p);
                if(target == null) continue;
                if(target.getTeam().equals(sp.getTeam())) continue;
                if(p.getLocation().distance(event.getEntity().getLocation()) <= sw.getRadiusDamage()) {
                    p.damage(sw.getDamage(), (Player) event.getEntity().getShooter());
                }
            }
        }

    }



}
