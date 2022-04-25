package team.yogurt.xrayblacklist.Managers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SillasManager {

    public static ItemStack getItem(){
        ItemStack is = new ItemStack(Material.RED_WOOL);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("Casilleros");
        im.addEnchant(Enchantment.DURABILITY, 1, false);
        is.setItemMeta(im);
        return is;
    }
}
