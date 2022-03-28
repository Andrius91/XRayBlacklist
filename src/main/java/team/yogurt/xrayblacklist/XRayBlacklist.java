package team.yogurt.xrayblacklist;

import com.alessiodp.oreannouncer.api.OreAnnouncer;
import com.alessiodp.oreannouncer.api.interfaces.OreAnnouncerAPI;
import me.ulrich.clans.packets.interfaces.UClans;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import team.yogurt.xrayblacklist.Commands.XRayBlacklistCommand;
import team.yogurt.xrayblacklist.Commands.acs;
import team.yogurt.xrayblacklist.Discord.Discord;
import team.yogurt.xrayblacklist.Managers.ConfigManager;
import team.yogurt.xrayblacklist.Managers.SaveList;
import team.yogurt.xrayblacklist.listeners.BlockBreakListener;
import team.yogurt.xrayblacklist.listeners.JoinAndLeaveListener;

import java.io.IOException;
import java.util.ArrayList;

import static team.yogurt.xrayblacklist.Utilities.sendMessage;

public final class XRayBlacklist extends JavaPlugin {

    private static final ArrayList<String> list = new ArrayList<>();
    private static final ArrayList<String> queue_list = new ArrayList<>();
    private static XRayBlacklist instance;
    private static final Discord discord = new Discord();
    private static UClans clanAPI = null;
    private static OreAnnouncerAPI oreAnnouncerAPI = null;



    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        sendMessage("&7-------------------------");
        sendMessage("&d@YoGurT Development");
        sendMessage("&fVersión:&5 1.1-SNAPSHOT");
        sendMessage("&7-------------------------");
        getConf();
        registerCommands();
        registerListeners();
        sendMessage("&dCargando lista...");
        try {
            SaveList.loadList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendMessage("&dLista cargada.");
        sendMessage("&dConectado a discord...");
        discord.connect();
        sendMessage("&dSe ha conectado a discord.");
        if (Bukkit.getPluginManager().isPluginEnabled("UltimateClans")) {
            UClans clan = (UClans) Bukkit.getPluginManager().getPlugin("UltimateCLans");
            if(clan!=null) {
                clanAPI = clan;
            }
        }
        if (getServer().getPluginManager().getPlugin("OreAnnouncer") != null) {
            if (getServer().getPluginManager().getPlugin("OreAnnouncer").isEnabled()) {
                oreAnnouncerAPI = OreAnnouncer.getApi();
            }
        }
    }

    @Override
    public void onDisable() {
        sendMessage("&dGuardando lista...");
        SaveList.saveList();
        sendMessage("&dLista guardada.");
        // Plugin shutdown logic
    }
    public static XRayBlacklist getInstance() {
        return instance;
    }
    public static ArrayList<String> getList() {
        return list;
    }
    public static UClans getClanAPI() {
        return clanAPI;
    }
    public static OreAnnouncerAPI getOreAnnouncerAPI() {
        return oreAnnouncerAPI;
    }
    public static Discord getDiscord(){
        return discord;
    }
    public static ArrayList<String> getQueue_list() {
        return queue_list;
    }

    private void registerCommands(){
        getCommand("xrb").setExecutor(new XRayBlacklistCommand("xrb.staff"));
        getCommand("xrb").setTabCompleter(new XRayBlacklistCommand(null));
        getCommand("acs").setExecutor(new acs());
    }
    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new JoinAndLeaveListener(), this);

    }
    public static FileConfiguration getConf(){
        return ConfigManager.getFile("config.yml");
    }

}
