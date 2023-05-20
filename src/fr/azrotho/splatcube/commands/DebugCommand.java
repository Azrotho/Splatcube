package fr.azrotho.splatcube.commands;

import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatMaps;
import fr.azrotho.splatcube.object.SplatPlayer;
import fr.azrotho.splatcube.utils.EndFunction;
import fr.azrotho.splatcube.utils.StartGameFunctions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.isOp()) {
            commandSender.sendMessage(Splatcube.tag + " §cVous n'avez pas la permission d'utiliser cette commande");
            return true;
        }
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(Splatcube.tag + " §cVous devez être un joueur pour utiliser cette commande");
            return true;
        }
        Player player = (Player) commandSender;
        switch (strings[0]) {
            case "createMap":
                if (strings.length < 2) {
                    player.sendMessage(Splatcube.tag + " §cVous devez spécifier un nom de map");
                    return true;
                }
                SplatMaps maps = new SplatMaps(player.getLocation(), player.getLocation(), player.getLocation(), 50, strings[1]);
                player.sendMessage(Splatcube.tag + " §aLa map §e" + strings[1] + "§a a été créée");
                Splatcube.getMaps().add(maps);
                break;
            case "setMapSpawn":
                if (strings.length < 2) {
                    player.sendMessage(Splatcube.tag + " §cVous devez spécifier un nom de map");
                    return true;
                }
                for (SplatMaps map : Splatcube.getMaps()) {
                    if (map.getName().equalsIgnoreCase(strings[1])) {
                        map.setSpawn(player.getLocation());
                        player.sendMessage(Splatcube.tag + " §aLe spawn de la map §e" + strings[1] + "§a a été défini");
                        return true;
                    }
                }
                player.sendMessage(Splatcube.tag + " §cLa map §e" + strings[1] + "§c n'existe pas");
                break;
            case "setMapTeamSpawnBlue":
                if(strings.length < 2) {
                    player.sendMessage(Splatcube.tag + " §cVous devez spécifier un nom de map");
                    return true;
                }
                for(SplatMaps map : Splatcube.getMaps()) {
                    if(map.getName().equalsIgnoreCase(strings[1])) {
                        map.setBlueSpawn(player.getLocation());
                        player.sendMessage(Splatcube.tag + " §aLe spawn de l'équipe §9bleue §ade la map §e" + strings[1] + "§a a été défini");
                        return true;
                    }
                }
                player.sendMessage(Splatcube.tag + " §cLa map §e" + strings[1] + "§c n'existe pas");
                break;
            case "setMapTeamSpawnOrange":
                if(strings.length < 2) {
                    player.sendMessage(Splatcube.tag + " §cVous devez spécifier un nom de map");
                    return true;
                }
                for(SplatMaps map : Splatcube.getMaps()) {
                    if(map.getName().equalsIgnoreCase(strings[1])) {
                        map.setOrangeSpawn(player.getLocation());
                        player.sendMessage(Splatcube.tag + " §aLe spawn de l'équipe §6orange §ade la map §e" + strings[1] + "§a a été défini");
                        return true;
                    }
                }
                player.sendMessage(Splatcube.tag + " §cLa map §e" + strings[1] + "§c n'existe pas");
                break;
            case "setMapRadius":
                if(strings.length < 3) {
                    player.sendMessage(Splatcube.tag + " §cVous devez spécifier un nom de map et un rayon");
                    return true;
                }
                for(SplatMaps map : Splatcube.getMaps()) {
                    if(map.getName().equalsIgnoreCase(strings[1])) {
                        map.setRadius(Integer.parseInt(strings[2]));
                        player.sendMessage(Splatcube.tag + " §aLe rayon de vérification de la map §e" + strings[1] + "§a a été défini à §e" + strings[2] + "§a blocs");
                        return true;
                    }
                }
                player.sendMessage(Splatcube.tag + " §cLa map §e" + strings[1] + "§c n'existe pas");
                break;
            case "setMapName":
                if(strings.length < 3) {
                    player.sendMessage(Splatcube.tag + " §cVous devez spécifier un nom de map et un nouveau nom");
                    return true;
                }
                for(SplatMaps map : Splatcube.getMaps()) {
                    if(map.getName().equalsIgnoreCase(strings[1])) {
                        map.setName(strings[2]);
                        player.sendMessage(Splatcube.tag + " §aLe nom de la map §e" + strings[1] + "§a a été défini à §e" + strings[2]);
                        return true;
                    }
                }
                break;
            case "setPlayerTeam":
                if(strings.length < 3) {
                    player.sendMessage(Splatcube.tag + " §cVous devez spécifier un joueur et une équipe");
                    return true;
                }
                for(Player target : Bukkit.getOnlinePlayers()) {
                    if(target.getName().equalsIgnoreCase(strings[1])) {
                        SplatPlayer splatPlayer = SplatPlayer.getSplatPlayer(target);
                        if(strings[2].equalsIgnoreCase("blue")) {
                            splatPlayer.setTeam("blue");
                            player.sendMessage(Splatcube.tag + " §aLe joueur §e" + target.getName() + "§a a été défini dans l'équipe §9bleue");
                            return true;
                        } else if(strings[2].equalsIgnoreCase("orange")) {
                            splatPlayer.setTeam("orange");
                            player.sendMessage(Splatcube.tag + " §aLe joueur §e" + target.getName() + "§a a été défini dans l'équipe §6orange");
                            return true;
                        } else {
                            player.sendMessage(Splatcube.tag + " §cL'équipe §e" + strings[2] + "§c n'existe pas");
                            return true;
                        }
                    }
                }
                break;
            case "start":
                StartGameFunctions.start();
                Splatcube.isStarted = true;
                break;
            case "forceEnd":
                EndFunction.end();
                break;
            case "listMaps":
                player.sendMessage(Splatcube.tag + " §aLa liste des cartes");
                for(SplatMaps map : Splatcube.getMaps()) {
                    player.sendMessage("§a - §e" + map.getName());
                }
                break;
            case "setWeapon":
                if(strings.length < 3) {
                    player.sendMessage(Splatcube.tag + " §cVous devez spécifier un joueur et une arme");
                    return true;
                }
                SplatPlayer splatPlayer = SplatPlayer.getSplatPlayer(Bukkit.getPlayer(strings[1]));
                List<String> weapons = new ArrayList<>();
                weapons.add("rouleau");
                weapons.add("badigeonneur");
                weapons.add("concentrateur");
                weapons.add("blaster");
                weapons.add("liquidateur");

                if(splatPlayer == null) {
                    player.sendMessage(Splatcube.tag + " §cLe joueur §e" + strings[1] + "§c n'existe pas");
                    return true;
                }

                if(weapons.contains(strings[2])) {
                    splatPlayer.setWeapon(strings[2]);
                    player.sendMessage(Splatcube.tag + " §aL'arme du joueur §e" + strings[1] + "§a a été défini à §e" + strings[2]);
                    return true;
                } else {
                    player.sendMessage(Splatcube.tag + " §cL'arme §e" + strings[2] + "§c n'existe pas");
                    return true;
                }
            case "help":
                player.sendMessage(Splatcube.tag + " §aListe des commandes");
                player.sendMessage("§a - §e/debug createMap <nom> §7- Créer une map");
                player.sendMessage("§a - §e/debug setMapSpawn <nom> §7- Définir le spawn de la map");
                player.sendMessage("§a - §e/debug setMapTeamSpawnBlue <nom> §7- Définir le spawn de l'équipe bleue");
                player.sendMessage("§a - §e/debug setMapTeamSpawnOrange <nom> §7- Définir le spawn de l'équipe orange");
                player.sendMessage("§a - §e/debug setMapRadius <nom> <rayon> §7- Définir le rayon de la map");
                player.sendMessage("§a - §e/debug setMapName <nom> <nouveau nom> §7- Définir le nom de la map");
                player.sendMessage("§a - §e/debug setPlayerTeam <joueur> <équipe> §7- Définir l'équipe d'un joueur");
                player.sendMessage("§a - §e/debug start §7- Démarrer la partie");
                player.sendMessage("§a - §e/debug forceEnd §7- Forcer la fin de la partie");
                player.sendMessage("§a - §e/debug listMaps §7- Afficher la liste des maps");
                player.sendMessage("§a - §e/debug setWeapon <joueur> <arme> §7- Définir l'arme d'un joueur (rouleau, badigeonneur, concentrateur, blaster, liquidateur)");
                break;
            default:
                player.sendMessage(Splatcube.tag + " §cCommande inconnue, faites §e/debug help §cpour afficher la liste des commandes");
                break;
        }
    return true;
    }
}
