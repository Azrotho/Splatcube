package fr.azrotho.splatcube.runnable;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatPlayer;
import fr.azrotho.splatcube.utils.StartGameFunctions;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StarterRunnable extends BukkitRunnable {
    int timerBeforeStart = 10;
    @Override
    public void run() {
        if(Splatcube.isStarted) {
            cancel();
        }
        if(timerBeforeStart == 0) {

            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendTitle("§6La partie commence !", "", 0, 20, 0);
                SplatPlayer spPlayer = SplatPlayer.getSplatPlayer(p);
                if(spPlayer.getTeam().equals("none")) {
                    // Mettre le joueur dans l'équipe avec le moins de joueurs
                    int blue = 0;
                    int orange = 0;
                    for(SplatPlayer sp : Splatcube.getPlayers()) {
                        if(sp.getTeam().equals("blue")) {
                            blue++;
                        }
                        if(sp.getTeam().equals("orange")) {
                            orange++;
                        }
                    }
                    if(blue < orange) {
                        spPlayer.setTeam("blue");
                    } else {
                        spPlayer.setTeam("orange");
                    }
                }
            }

            StartGameFunctions.start();
        }

        if(Bukkit.getOnlinePlayers().size() < 34) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText("§cIl n'y a pas assez de joueurs pour commencer la partie !"));
            }
            timerBeforeStart = 10;
        } else {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendTitle("§6Début de la partie dans", "§e" + timerBeforeStart + " secondes", 0, 20, 0);
            }
            timerBeforeStart--;
        }


    }
}
