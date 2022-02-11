package team.yogurt.xrayblacklist;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utilities {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void sendMessage(String message){
        XRayBlacklist.getInstance().getLogger().info(color(message));
    }
    public static void sendMessage(String message, boolean toStaffs){
        if(toStaffs){
            for(Player player : Bukkit.getOnlinePlayers()){
                if(player.hasPermission("xrb.staff")){
                    player.sendMessage(color(message));
                }
            }
        }

    }
}
