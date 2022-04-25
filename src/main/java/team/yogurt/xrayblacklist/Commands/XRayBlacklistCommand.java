package team.yogurt.xrayblacklist.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import team.yogurt.xrayblacklist.Commands.Subcommands.*;
import team.yogurt.xrayblacklist.Managers.CommandManager;

import java.util.*;

import static team.yogurt.xrayblacklist.Utilities.color;

public class XRayBlacklistCommand implements TabExecutor {
    private static final ArrayList<CommandManager> commands = new ArrayList<>();
    String permission;
    public XRayBlacklistCommand(String permission){
        if(permission != null){
            commands.add(new add());
            commands.add(new list());
            commands.add(new remove());
            commands.add(new reload());
            commands.add(new clearDiamonds());
            commands.add(new blacklist());
            commands.add(new start());
            commands.add(new kick());
            this.permission = permission;

        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission(permission)) {
            if (args.length > 0) {
                for (CommandManager cmd : getCommands()) {
                    if (args[0].equalsIgnoreCase(cmd.getName())) {
                        cmd.perform(sender, args);
                        return true;
                    }
                }
            }else{
                sendHelpList(sender);
            }
        }else{
            sender.sendMessage(color("&cNo tienes permisos, lol"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();

        if(sender.hasPermission("xrb.staff")){
            String[] subcommands = {"add", "remove", "list", "reload", "cleardiamonds"};
            if(args.length == 1){
                tabs.addAll(Arrays.asList(subcommands));
            }else{
                String players = args[1].toLowerCase();
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(player.getName().toLowerCase().startsWith(players)){
                        tabs.add(player.getName());
                    }
                }
            }
            Collections.sort(tabs);
            return tabs;
        }
        return tabs;
    }
    private void sendHelpList(CommandSender sender){
        sender.sendMessage(color("&5Commands:"));
        sender.sendMessage(" ");
        for(CommandManager cmd : getCommands()) {
            sender.sendMessage(color("&d" + cmd.getSyntax() + "&f - &7" + cmd.getDescription()));
        }
    }
    public static ArrayList<CommandManager> getCommands() {
        return commands;
    }
}
