package team.yogurt.xrayblacklist.listeners;

import jdk.jshell.execution.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerMoveEvent;
import team.yogurt.xrayblacklist.Utilities;
import team.yogurt.xrayblacklist.XRayBlacklist;

public class MovementListener implements Listener {

    @EventHandler
    public void onJoin(PlayerMoveEvent e){
        if(XRayBlacklist.getAlspawn().contains(e.getPlayer().getName())){
            Location loc = new Location(Bukkit.getWorld("world"), 17.5, 81.0, -47.5, 90.08695983886719F, 0.1500115543603897F);
            e.getPlayer().teleport(loc);
            e.getPlayer().sendMessage(Utilities.color("&cPedime disculpas"));
        }
    }
}
