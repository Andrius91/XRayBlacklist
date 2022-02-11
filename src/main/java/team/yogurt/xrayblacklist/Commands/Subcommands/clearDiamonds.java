package team.yogurt.xrayblacklist.Commands.Subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.xrayblacklist.Managers.CommandManager;
import team.yogurt.xrayblacklist.Managers.XrayerManager;
import team.yogurt.xrayblacklist.Utilities;
import team.yogurt.xrayblacklist.XRayBlacklist;

public class clearDiamonds extends CommandManager {
    @Override
    public String getName() {
        return "cleardiamonds";
    }

    @Override
    public String getDescription() {
        return "Cambia los diamantes a carbón del jugador";
    }

    @Override
    public String getSyntax() {
        return "/xrb cleardiamonds (player)";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length > 1){
            if(sender.hasPermission("xrb.admin")){
                String target = args[1];
                Player ptarget = Bukkit.getPlayerExact(target);
                if(ptarget != null){
                    XrayerManager.clearDiamonds(ptarget);
                }else{
                    XRayBlacklist.getQueue_list().add(target);
                    sender.sendMessage(Utilities.color("&8[&bXRB&8]&c El jugador "+target+" está desconectado, cuando vuelva a conectarse se le cambiarán los items automaticamente."));
                }
            }
        }
    }
}
