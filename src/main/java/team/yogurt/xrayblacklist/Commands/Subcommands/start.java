package team.yogurt.xrayblacklist.Commands.Subcommands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.xrayblacklist.Managers.CommandManager;
import team.yogurt.xrayblacklist.Utilities;
import team.yogurt.xrayblacklist.XRayBlacklist;

import java.util.ArrayList;
import java.util.List;

public class start extends CommandManager {
    @Override
    public String getName() {
        return "start";
    }

    @Override
    public String getDescription() {
        return "Abre un numero de casilleros";
    }

    @Override
    public String getSyntax() {
        return "/xrb start (casilleros)";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (sender.hasPermission("xrb.start")) {
            int casilleros = Integer.parseInt(args[1]);
            List<String> listas = XRayBlacklist.getConf().getStringList("locations");

            for (String lista : listas) {
                String[] array_list = lista.split(" ");
                double x = Integer.parseInt(array_list[0]);
                double y = Integer.parseInt(array_list[1]);
                double z = Integer.parseInt(array_list[2]);
                Location loc = new Location(((Player) sender).getWorld(), x, y, z);
                loc.getBlock().setType(Material.GOLD_BLOCK);
            }
            ArrayList<String> temp = new ArrayList<>(listas);
            System.out.println(temp.size());
            for (int i = 0; i < casilleros; i++) {
                int j = (int) Math.floor(Math.random() * temp.size());
                double x = Integer.parseInt(temp.get(j).split(" ")[0]);
                double y = Integer.parseInt(temp.get(j).split(" ")[1]);
                double z = Integer.parseInt(temp.get(j).split(" ")[2]);
                Location loc = new Location(((Player) sender).getWorld(), x, y, z);
                loc.getBlock().setType(Material.AIR);
                temp.remove(temp.get(j));
            }
            sender.sendMessage(Utilities.color("&aCasilleros abiertos: " + casilleros));
            System.out.println(temp.size());
        }
    }
}
