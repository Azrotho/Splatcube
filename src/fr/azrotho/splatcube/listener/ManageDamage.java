package fr.azrotho.splatcube.listener;

import fr.azrotho.splatcube.Splatcube;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ManageDamage implements Listener {
    @EventHandler
    public static void onDamage(EntityDamageEvent event) {
        if(event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            event.setCancelled(true);
        }
        if(!Splatcube.isStarted) {
            event.setCancelled(true);
        }
    }
}
