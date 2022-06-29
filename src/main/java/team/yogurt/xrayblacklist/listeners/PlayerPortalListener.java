package team.yogurt.xrayblacklist.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerPortalListener implements Listener {

    @EventHandler
    public void portal(PlayerPortalEvent event){
        event.setCancelled(true);
    }
}
