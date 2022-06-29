package team.yogurt.xrayblacklist.Commands.Subcommands;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.xrayblacklist.Managers.CommandManager;
import team.yogurt.xrayblacklist.Utilities;

import java.util.ArrayList;

public class kick extends CommandManager {
    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public String getDescription() {
        return "Expulso a todos los jugadores fuera que no están en un casillero";
    }

    @Override
    public String getSyntax() {
        return "/xrb kick";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (sender.hasPermission("xrb.start")) {
            Player p = (Player) sender;
            World world = p.getWorld();
            ArrayList<String> descalificados = new ArrayList<>();
            for(Player player : world.getPlayers()){
                Material loc = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
                if((loc != Material.EMERALD_BLOCK) && (player.getGameMode() != GameMode.SPECTATOR)){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + player.getName());
                    descalificados.add(player.getName());
                }
            }
            String a = String.join(", ", descalificados);
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(Utilities.color("&e&lDESCALIFICADOS: &f" + a));
            }
            descalificados.clear();
        }
    }
}
