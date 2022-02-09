package team.yogurt.xrayblacklist;

import net.md_5.bungee.api.ChatColor;

public class Utilities {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void sendMessage(String message){
        XRayBlacklist.getInstance().getLogger().info(color(message));
    }
}
