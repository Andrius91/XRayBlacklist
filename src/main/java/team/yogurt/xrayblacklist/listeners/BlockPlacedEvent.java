package team.yogurt.xrayblacklist.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import team.yogurt.xrayblacklist.Managers.ConfigManager;
import team.yogurt.xrayblacklist.XRayBlacklist;

import java.util.ArrayList;

import static team.yogurt.xrayblacklist.XRayBlacklist.getConf;

public class BlockPlacedEvent implements Listener {

    @EventHandler
    public void onPlaceBlocks(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if(e.getBlockPlaced().getType() == Material.RED_WOOL && player.getName().equals("Klayzx")){

            ArrayList<String> lista = (ArrayList<String>) XRayBlacklist.getInstance().getConfig().getStringList("locations");

            lista.add(e.getBlockPlaced().getX() + " " + e.getBlockPlaced().getY() + " " +e.getBlockPlaced().getZ());
            XRayBlacklist.getInstance().getConfig().set("locations", lista);
            XRayBlacklist.getInstance().saveConfig();
            XRayBlacklist.getInstance().reloadConfig();
            System.out.println(e.getBlockPlaced().getX() + " " + e.getBlockPlaced().getY() + " " +e.getBlockPlaced().getZ());
            System.out.println(lista);
        }
    }
}
