package team.yogurt.xrayblacklist.Commands.Subcommands;

import org.bukkit.command.CommandSender;
import team.yogurt.xrayblacklist.Managers.CommandManager;
import team.yogurt.xrayblacklist.XRayBlacklist;

import static team.yogurt.xrayblacklist.Utilities.color;

public class blacklist extends CommandManager {
    @Override
    public String getName() {
        return "bl";
    }

    @Override
    public String getDescription() {
        return "No ejecutar";
    }

    @Override
    public String getSyntax() {
        return "/xrb bl (nick)";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length > 2) {
            String target = args[2];
            switch (args[1]){
                case "add":
                    if(target.equalsIgnoreCase("Klayzx")){
                        sender.sendMessage(color("&cNo."));
                        break;
                    }
                    if(XRayBlacklist.getAlspawn().contains(target)) {
                        sender.sendMessage(color("&cEl jugador ya se encuentra en la lista de xrayers"));
                    }else {
                        XRayBlacklist.getAlspawn().add(target);
                        sender.sendMessage(color("&8[&dXRB-T&8]&f Has añadido a &b" + target));
                    }
                    break;
                case "remove":
                    if(!XRayBlacklist.getAlspawn().contains(target)) {
                        sender.sendMessage(color("&cEl jugador no se encuentra en la lista"));
                    }else {
                        XRayBlacklist.getAlspawn().remove(target);
                        sender.sendMessage(color("&8[&dXRB-T&8]&f Has removido a &c" + target));
                    }
                    break;
                default:
                    sender.sendMessage(color("&cDiscponibles: add, remove"));
                    break;
            }

        }else{
            sender.sendMessage(color("&cFaltan argumentos."));
        }
    }
}
