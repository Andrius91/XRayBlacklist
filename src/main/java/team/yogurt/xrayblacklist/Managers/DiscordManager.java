package team.yogurt.xrayblacklist.Managers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

public abstract class DiscordManager {
    public abstract TextChannel getChannel();
    public abstract JDA getJDA();
    public abstract String getToken();
    public abstract void getStatus();
    public abstract void connect();
    public abstract void disconnect();
}