package fr.azrotho.splatcube.object;

import fr.azrotho.splatcube.Splatcube;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SplatPlayer {
    private String name;
    private UUID uuid;
    private String team;
    private int kills;
    private int blockPainted;
    private int ink;
    private String weapon;

    public SplatPlayer(String name, UUID uuid, String team, int kills, int blockPainted, int ink, String weapon) {
        this.name = name;
        this.team = team;
        this.kills = kills;
        this.blockPainted = blockPainted;
        this.ink = ink;
        this.uuid = uuid;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getBlockPainted() {
        return blockPainted;
    }

    public void setBlockPainted(int blockPainted) {
        this.blockPainted = blockPainted;
    }

    public int getInk() {
        return ink;
    }

    public void setInk(int ink) {
        this.ink = ink;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public static SplatPlayer createPlayer(String name, UUID uuid) {
        SplatPlayer player = new SplatPlayer(name, uuid, "none", 0, 0, 100, "liquidateur");
        return player;
    }

    public static SplatPlayer getSplatPlayer(UUID uuid) {
        for(SplatPlayer player : Splatcube.getPlayers()) {
            if(player.getUuid().equals(uuid)) {
                return player;
            }
        }
        return null;
    }

    public static SplatPlayer getSplatPlayer(Player player) {
        for(SplatPlayer SPplayer : Splatcube.getPlayers()) {
            if(player.getUniqueId().equals(SPplayer.getUuid())) {
                return SPplayer;
            }
        }
        return null;
    }
}
