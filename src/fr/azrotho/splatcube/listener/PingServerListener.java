package fr.azrotho.splatcube.listener;

import fr.azrotho.splatcube.Splatcube;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingServerListener implements Listener {
    @EventHandler
    public static void onPing(ServerListPingEvent event) {
        event.setMaxPlayers(16);
        if(Splatcube.isStarted) {
            event.setMotd("En cours");
        } else {
            event.setMotd("READY");
        }
    }
}
