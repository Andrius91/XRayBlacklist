package team.yogurt.xrayblacklist.Commands.Subcommands;

import com.alessiodp.oreannouncer.api.OreAnnouncer;
import com.alessiodp.oreannouncer.api.interfaces.OAPlayer;
import com.alessiodp.oreannouncer.api.interfaces.OreAnnouncerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.xrayblacklist.Managers.CommandManager;
import team.yogurt.xrayblacklist.Managers.XrayerManager;
import team.yogurt.xrayblacklist.Utilities;
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
                Player ptarget = Bukkit.getPlayerExact(target);
                if(ptarget != null){
                    OAPlayer oaPlayer = XRayBlacklist.getOreAnnouncerAPI().getOAPlayer(ptarget.getUniqueId());
                    XrayerManager.clearDiamonds(ptarget);
                    oaPlayer.setWhitelisted(true);
                }else{
                    XRayBlacklist.getQueue_list().add(target);
                    sender.sendMessage(Utilities.color("&8[&bXRB&8]&c El jugador "+target+" está desconectado, cuando vuelva a conectarse se le cambiarán los items automaticamente."));
                }
                XRayBlacklist.getDiscord().sendEmbed("add", target, sender.getName());
            }

        }
    }
}
