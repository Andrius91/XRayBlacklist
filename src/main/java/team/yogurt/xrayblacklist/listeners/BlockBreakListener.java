package team.yogurt.xrayblacklist.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import team.yogurt.xrayblacklist.XRayBlacklist;

import static team.yogurt.xrayblacklist.XRayBlacklist.getConf;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (XRayBlacklist.getList().contains(player.getName())) {
            Block block = e.getBlock();
            String troll_item = getConf().getString("config.troll.item").toUpperCase();
            int troll_item_amount = getConf().getInt("config.troll.amount");
            for (String item : getConf().getStringList("config.items")) {
                if (block.getType() == Material.getMaterial(item.toUpperCase())) {
                    Location location = block.getLocation();
                    ItemStack itemStack = new ItemStack(Material.getMaterial(troll_item.toUpperCase()), troll_item_amount);
                    e.setExpToDrop(0);
                    e.setDropItems(false);
                    location.getWorld().dropItemNaturally(location, itemStack);
                }
            }
        }
    }

    @EventHandler
    public void onPlaceBlocks(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if(XRayBlacklist.getList().contains(player.getName()) && e.getBlock().getType() == Material.TNT){
            e.setCancelled(true);
        }

    }

}
