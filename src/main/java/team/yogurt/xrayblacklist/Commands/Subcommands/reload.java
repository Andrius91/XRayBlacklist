package team.yogurt.xrayblacklist.Commands.Subcommands;

import org.bukkit.command.CommandSender;
import team.yogurt.xrayblacklist.Managers.CommandManager;
import team.yogurt.xrayblacklist.Managers.ConfigManager;

import static team.yogurt.xrayblacklist.Utilities.color;

public class reload extends CommandManager {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Recarga la configuración";
    }

    @Override
    public String getSyntax() {
        return "/xrb reload";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length == 1){
            if(sender.hasPermission("xrb.admin")){
                ConfigManager.reloadConfig("config.yml");
                sender.sendMessage(color("&bConfiguración recargada."));
            }
        }
    }
}
