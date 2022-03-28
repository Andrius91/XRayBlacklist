package team.yogurt.xrayblacklist.Commands;

import me.ulrich.clans.data.ClanData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import team.yogurt.xrayblacklist.Utilities;
import team.yogurt.xrayblacklist.XRayBlacklist;

public class acs implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0){
            Player p = Bukkit.getPlayerExact(args[0]);
            int extraslots = Integer.parseInt(args[1]);
            ClanData clanData = XRayBlacklist.getClanAPI().getPlayerAPI().getPlayerClan(p.getUniqueId());
            if(clanData != null){
                clanData.setExtraSlots(clanData.getExtraSlots() + extraslots);
                System.out.println(Utilities.color("&7(Slots)&f se han añadido &a+"+extraslots+" &fal clan de &b" + p.getName()));
                sender.sendMessage(Utilities.color("&7(Slots)&f se han añadido &a+"+extraslots+" &fal clan de &b" + p.getName()));
            }else{
                System.out.println(p.getName()+  " log " +extraslots+ " slots");
            }

        }
        return true;
    }
}
