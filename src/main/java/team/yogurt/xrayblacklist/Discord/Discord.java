package team.yogurt.xrayblacklist.Discord;

import com.google.gson.Gson;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import team.yogurt.xrayblacklist.Managers.DiscordManager;
import team.yogurt.xrayblacklist.XRayBlacklist;
import team.yogurt.xrayblacklist.listeners.MessageListener;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Discord extends DiscordManager {
    private JDA jda;
    public boolean EXIST_MSG = false;
    public String id = null;

    @Override
    public TextChannel getChannel() {
        return getJDA().getTextChannelById(XRayBlacklist.getConf().getString("config.channel_id"));
    }


    @Override
    public JDA getJDA() {
        return jda;
    }

    @Override
    public String getToken() {
        return XRayBlacklist.getConf().getString("config.token");
    }

    @Override
    public void getStatus() {
    }

    @Override
    public void connect() {
        try {
            this.jda = JDABuilder.createDefault(getToken()).build().awaitReady();
            jda.addEventListener(new MessageListener());
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        getJDA().shutdownNow();
    }

    public void sendEmbed(String type, String xrayer, String staff) {
        EmbedBuilder embed = new EmbedBuilder();
        Date date = new Date();
        long unixTime = date.getTime() / 1000L;
        embed.setTitle("XRay Blacklist");
        if (type.equalsIgnoreCase("add")) {
            embed.setDescription("Se ha agregado a un nuevo usuario a la lista de xrayers.");
            embed.setColor(Color.green);
        } else if (type.equalsIgnoreCase("remove")) {
            embed.setDescription("Se ha removido a un nuevo usuario a la lista de xrayers.");
            embed.setColor(Color.red);
        }
        embed.setFooter("Feryx Network", "https://cdn.discordapp.com/attachments/945780709451235359/946169079612469298/IMG_20220205_195823.png");
        embed.addField("Usuario", xrayer, false);
        embed.addField("Staff", staff, false);
        embed.addField("Fecha", "<t:" + unixTime + ":f>", false);
        embed.setTimestamp(new Date().toInstant());
        System.out.println(new Date().toInstant().toEpochMilli());
        getChannel().sendMessageEmbeds(embed.build()).queue();
    }

    public void sendStaffOnline(long unixTime) {
        ArrayList<String> staffs = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(p -> {
            if(p.hasPermission("xrb.staff")) {
                staffs.add(p.getName());
            }
        });
        System.out.println(staffs);
        try {
            URL url = new URL("http://localhost/api/staff/online/?key=zfBYC9ggD6Jl8GDS");
            Gson gson = new Gson();
            String onlineStaffs = gson.toJson(staffs);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("PUT");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");
            System.out.println(onlineStaffs);
            String data = "{\n  \"onlineStaff\": " + onlineStaffs + "\n}";
            System.out.println(data);
            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
            http.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
