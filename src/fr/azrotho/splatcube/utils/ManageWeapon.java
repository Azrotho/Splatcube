package fr.azrotho.splatcube.utils;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import fr.azrotho.splatcube.Splatcube;
import fr.azrotho.splatcube.object.SplatWeapon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ManageWeapon {
    public static void createWeapon() {
        String displayNameRouleau = IridiumColorAPI.process("<GRADIENT:002BFF>§lRouleau</GRADIENT:FF6F00>");
        SplatWeapon rouleau = new SplatWeapon("rouleau", displayNameRouleau, 10, 5, 4, 3, 1, 1);

        String displayNameConcentrateur = IridiumColorAPI.process("<GRADIENT:002BFF>§lConcentrateur</GRADIENT:FF6F00>");
        SplatWeapon concentrateur = new SplatWeapon("concentrateur", displayNameConcentrateur, 25, 8, 5, 4, 10, 2);

        String displayNameBadigeonneur = IridiumColorAPI.process("<GRADIENT:002BFF>§lBadigeonneur</GRADIENT:FF6F00>");
        SplatWeapon badigeonneur = new SplatWeapon("badigeonneur", displayNameBadigeonneur, 15, 5, 4, 3, 5, 3);

        String displayNameLiquidateur = IridiumColorAPI.process("<GRADIENT:002BFF>§lLiquidateur</GRADIENT:FF6F00>");
        SplatWeapon liquidateur = new SplatWeapon("liquidateur", displayNameLiquidateur, 10, 3, 4, 3, 3, 4);

        String displayNameBlaster = IridiumColorAPI.process("<GRADIENT:002BFF>§lBlaster</GRADIENT:FF6F00>");
        SplatWeapon blaster = new SplatWeapon("blaster", displayNameBlaster, 10, 2, 3, 2, 1, 5);

        Splatcube.getWeapons().add(rouleau);
        Splatcube.getWeapons().add(concentrateur);
        Splatcube.getWeapons().add(badigeonneur);
        Splatcube.getWeapons().add(liquidateur);
        Splatcube.getWeapons().add(blaster);
    }

    public static SplatWeapon getWeapon(String name) {
        for(SplatWeapon sw : Splatcube.getWeapons()) {
            if(sw.getName().equalsIgnoreCase(name)) {
                return sw;
            }
        }
        return null;
    }

    public static ItemStack getWeaponItem(String name) {
        SplatWeapon sw = getWeapon(name);
        if(sw == null) return null;
        ItemStack is = new ItemStack(Material.STICK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(sw.getDisplayName());
        im.setCustomModelData(sw.getCustomModelData());
        is.setItemMeta(im);
        return is;
    }

    public static SplatWeapon getWeaponByDisplayName(String displayName) {
        for(SplatWeapon sw : Splatcube.getWeapons()) {
            if(sw.getDisplayName().equalsIgnoreCase(displayName)) {
                return sw;
            }
        }
        return null;
    }
}
