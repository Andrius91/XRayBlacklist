package team.yogurt.xrayblacklist;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import team.yogurt.xrayblacklist.Commands.XRayBlacklistCommand;
import team.yogurt.xrayblacklist.Managers.ConfigManager;
import team.yogurt.xrayblacklist.Managers.SaveList;
import team.yogurt.xrayblacklist.listeners.BlockBreakListener;

import java.io.IOException;
import java.util.ArrayList;

import static team.yogurt.xrayblacklist.Utilities.sendMessage;

public final class XRayBlacklist extends JavaPlugin {

    private static final ArrayList<String> list = new ArrayList<>();


    private static XRayBlacklist instance;
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
    private void registerCommands(){
        getCommand("xrb").setExecutor(new XRayBlacklistCommand("xrb.staff"));
        getCommand("xrb").setTabCompleter(new XRayBlacklistCommand(null));
    }
    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

    }
    public static FileConfiguration getConf(){
        return ConfigManager.getFile("config.yml");
    }

}
