package team.yogurt.xrayblacklist.Commands.Subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.xrayblacklist.Managers.CommandManager;

import static team.yogurt.xrayblacklist.Utilities.color;
import static team.yogurt.xrayblacklist.XRayBlacklist.getList;

public class list extends CommandManager {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Mirar la lista de xrayers";
    }

    @Override
    public String getSyntax() {
        return "/xrb list";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length == 1){
            if(getList().isEmpty()){
                sender.sendMessage(color("&7Lista vacia."));
            }else{
                sender.sendMessage(color("&eLista de xrayers:"));
                for(String player : getList()){
                    Player online_player = Bukkit.getPlayerExact(player);
                    if(online_player != null){
                        sender.sendMessage(color("&7- &b" + player + " &8("+ "&a•" +"&8)"));
                    }
                }
                for(String player : getList()){
                    Player offline_player = Bukkit.getPlayerExact(player);
                    if(offline_player == null){
                        sender.sendMessage(color("&7- &b" + player + " &8("+ "&c•" +"&8)"));
                    }
                }
                }

        }
    }
}
