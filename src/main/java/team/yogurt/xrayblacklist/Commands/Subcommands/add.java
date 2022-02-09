package team.yogurt.xrayblacklist.Commands.Subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.xrayblacklist.Managers.CommandManager;
import team.yogurt.xrayblacklist.XRayBlacklist;


import static team.yogurt.xrayblacklist.Utilities.color;

public class add extends CommandManager {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Añade a alguien a la lista de xrayers";
    }

    @Override
    public String getSyntax() {
        return "/xrb add (player)";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length > 1) {
            String target = args[1];
            if (XRayBlacklist.getList().contains(target)) {
                sender.sendMessage(color("&cEl jugador ya se encuentra en la lista de xrayers"));
            } else {
                XRayBlacklist.getList().add(target);
                sender.sendMessage(color("&8[&bXRB&8]&f Has añadido a &b" + target));
            }

        }
    }
}
