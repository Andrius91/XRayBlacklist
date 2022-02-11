package team.yogurt.xrayblacklist.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import team.yogurt.xrayblacklist.Managers.XrayerManager;
import team.yogurt.xrayblacklist.XRayBlacklist;

import static team.yogurt.xrayblacklist.Utilities.color;

public class JoinAndLeaveListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(XRayBlacklist.getList().contains(p.getName())){
            for(Player player : Bukkit.getOnlinePlayers()){
                if(player.hasPermission("xrb.staff")){
                    player.sendMessage(color("&8[&bXRB&8]&a "+p.getName()+"&f ha entrado"));
                }
            }
        }
        if(XRayBlacklist.getQueue_list().contains(p.getName())){
            XrayerManager.clearDiamonds(p);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(XRayBlacklist.getList().contains(p.getName())){
            for(Player player : Bukkit.getOnlinePlayers()){
                if(player.hasPermission("xrb.staff")){
                    player.sendMessage(color("&8[&bXRB&8]&c "+p.getName()+"&f ha salido"));
                }
            }
        }
    }
}
