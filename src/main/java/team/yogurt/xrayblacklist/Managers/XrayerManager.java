package team.yogurt.xrayblacklist.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import team.yogurt.xrayblacklist.Utilities;

public class XrayerManager {

    public static int isOnline(String player){
        Player p = Bukkit.getPlayerExact(player);
        if(p != null){
            return 1;
        }else{
            return 0;
        }
    }
    public static void clearDiamonds(Player player){
        int amount = 0;
        int d_amount = 0;
        for(ItemStack is : player.getInventory().getContents()){
            if(is != null){
                if(is.getType() == Material.DIAMOND){
                    amount += is.getAmount();
                    is.setAmount(is.getAmount());
                    is.setType(Material.COAL);
                }
                if(is.getType() == Material.DEEPSLATE_DIAMOND_ORE){
                    d_amount += is.getAmount();
                    is.setAmount(is.getAmount());
                    is.setType(Material.DEEPSLATE_COAL_ORE);
                }
            }

        }
        Utilities.sendMessage("&8[&bXRB&8]&f Se han cambiado: &bDiamantes &7(&e"+amount+"&7)" +
                "&f, &bMineral de diamantes &7(&e" + d_amount+"&7)&f a &8Carbon&f para &e" + player.getName()+"&f.", true);
    }
}
