package team.yogurt.xrayblacklist.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import team.yogurt.xrayblacklist.Utilities;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerClickSign(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(!p.hasPermission("xrb.sellall")){
            p.sendMessage(Utilities.color("&cNo tienes permisos para el sell all, puedes adquirirlo en nuestra " +
                    "tienda: &bhttps://tienda.feryx.net"));
            return;
        }
        if(e.getClickedBlock() != null){
            if(e.getClickedBlock().getType() == Material.OAK_SIGN
                    || e.getClickedBlock().getType() == Material.OAK_WALL_SIGN){
                if(e.getAction() == Action.RIGHT_CLICK_BLOCK && p.isSneaking()){
                    Sign sign = (Sign) e.getClickedBlock().getState();
                    for (int i = 0; i < sign.getLines().length; i++) {
                        String amount = sign.getLines()[1];
                        String item_name = sign.getLines()[2];
                        String price = sign.getLines()[3].replace("$", "");

                    }

                }
            }
        }
    }
}
