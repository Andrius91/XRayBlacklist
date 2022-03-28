package team.yogurt.xrayblacklist.Commands.Subcommands;

import com.alessiodp.oreannouncer.api.OreAnnouncer;
import com.alessiodp.oreannouncer.api.interfaces.OAPlayer;
import com.alessiodp.oreannouncer.api.interfaces.OreAnnouncerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.xrayblacklist.Managers.CommandManager;
import team.yogurt.xrayblacklist.Managers.XrayerManager;
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
        Player ptarget = Bukkit.getPlayerExact(target);
        if(XRayBlacklist.getList().contains(target)){
            XRayBlacklist.getList().remove(target);
            if(ptarget != null){
                OAPlayer oaPlayer = XRayBlacklist.getOreAnnouncerAPI().getOAPlayer(ptarget.getUniqueId());
                XrayerManager.clearDiamonds(ptarget);
                oaPlayer.setWhitelisted(false);
            }
            sender.sendMessage(color("&8[&bXRB&8]&f Has removido a &c" +target));
            XRayBlacklist.getDiscord().sendEmbed("remove", target, sender.getName());
        }else{
            sender.sendMessage(color("&cNo se ha encontrado al jugador"));
        }
    }
}
