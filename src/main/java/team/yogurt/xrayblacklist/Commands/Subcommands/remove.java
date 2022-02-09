package team.yogurt.xrayblacklist.Commands.Subcommands;

import org.bukkit.command.CommandSender;
import team.yogurt.xrayblacklist.Managers.CommandManager;
import team.yogurt.xrayblacklist.XRayBlacklist;

import static team.yogurt.xrayblacklist.Utilities.color;

public class remove extends CommandManager {
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Elimina a alguien de la lista de xrayers";
    }

    @Override
    public String getSyntax() {
        return "/xrb remove (player)";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        String target = args[1];

        if(XRayBlacklist.getList().contains(target)){
            XRayBlacklist.getList().remove(target);
            sender.sendMessage(color("&8[&bXRB&8]&f Has removido a &c" +target));
        }else{
            sender.sendMessage(color("&cNo se ha encontrado al jugador"));
        }
    }
}
