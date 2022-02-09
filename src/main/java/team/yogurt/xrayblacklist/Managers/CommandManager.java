package team.yogurt.xrayblacklist.Managers;

import org.bukkit.command.CommandSender;

public abstract class CommandManager {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void perform(CommandSender sender, String[] args);

}