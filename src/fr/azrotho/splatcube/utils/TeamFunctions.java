package fr.azrotho.splatcube.utils;

import fr.azrotho.splatcube.object.SplatPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TeamFunctions {
    public static Material getBlock(Player player) {
        SplatPlayer splatPlayer = SplatPlayer.getSplatPlayer(player);
        if(splatPlayer.getTeam().equals("blue")) {
            return Material.BLUE_CONCRETE;
        }
        if(splatPlayer.getTeam().equals("orange")) {
            return Material.ORANGE_CONCRETE;
        }
        return Material.WHITE_CONCRETE;
    }
}
