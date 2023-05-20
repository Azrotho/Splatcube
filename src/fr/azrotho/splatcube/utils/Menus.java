package fr.azrotho.splatcube.utils;

import fr.azrotho.splatcube.Splatcube;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static fr.azrotho.splatcube.utils.ManageWeapon.getWeaponItem;

public class Menus {
    public static void openTeamMenu(Player player) {
        Inventory inventory = player.getServer().createInventory(null, 9, "§9Choisissez une équipe");
        player.openInventory(inventory);

        ItemStack blue = new ItemStack(Material.BLUE_WOOL);
        ItemMeta blueMeta = blue.getItemMeta();
        blueMeta.setDisplayName("§9Equipe bleue");
        blue.setItemMeta(blueMeta);

        ItemStack orange = new ItemStack(Material.ORANGE_WOOL);
        ItemMeta orangeMeta = orange.getItemMeta();
        orangeMeta.setDisplayName("§6Equipe orange");
        orange.setItemMeta(orangeMeta);

        inventory.setItem(3, blue);
        inventory.setItem(5, orange);
    }

    public static void openWeaponMenu(Player player) {
        Inventory inventory = player.getServer().createInventory(null, 9, "§9Choisissez une arme");
        player.openInventory(inventory);

        for(int i = 0; i < Splatcube.getWeapons().size(); i++) {
            inventory.setItem(i, getWeaponItem(Splatcube.getWeapons().get(i).getName()));
        }
    }
}
