package team.yogurt.xrayblacklist.Managers;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import team.yogurt.xrayblacklist.Utilities;
import team.yogurt.xrayblacklist.XRayBlacklist;


import java.io.File;
import java.io.IOException;

public class ConfigManager {


    public static void createFolder(){
        if (!XRayBlacklist.getInstance().getDataFolder().exists()){
            XRayBlacklist.getInstance().getDataFolder().mkdir();
        }
    }
    public static FileConfiguration getFile(String filename){

        File file = new File(XRayBlacklist.getInstance().getDataFolder(), filename);
        FileConfiguration config = new YamlConfiguration();
        if (!file.exists()){
            file.getParentFile().mkdir();
            XRayBlacklist.getInstance().saveResource(filename, true);
            XRayBlacklist.getInstance().getLogger().info(Utilities.color(filename + " &aregistered!"));
        }
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return config;
    }
    public static void reloadConfig(String filename){
        File file = new File(XRayBlacklist.getInstance().getDataFolder(), filename);
        FileConfiguration config = new YamlConfiguration();
        try{
            config.load(file);
        }catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }
}