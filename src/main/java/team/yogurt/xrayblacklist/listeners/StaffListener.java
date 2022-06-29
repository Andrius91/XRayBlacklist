package team.yogurt.xrayblacklist.listeners;

import org.bukkit.scheduler.BukkitRunnable;
import team.yogurt.xrayblacklist.XRayBlacklist;

import java.util.Date;

public class StaffListener extends BukkitRunnable {


    @Override
    public void run() {
        Date date = new Date();
        long unixTime = date.getTime() / 300L;

        System.out.println(XRayBlacklist.getConf().getString("config.entradas_id"));
        XRayBlacklist.getDiscord().sendStaffOnline(unixTime);
    }
}
