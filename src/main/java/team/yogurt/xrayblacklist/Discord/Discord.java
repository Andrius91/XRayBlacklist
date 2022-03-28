package team.yogurt.xrayblacklist.Discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import team.yogurt.xrayblacklist.Managers.DiscordManager;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.Date;

public class Discord extends DiscordManager {
    private JDA jda;

    @Override
    public TextChannel getChannel() {
        return getJDA().getTextChannelById("947166866093252699");
    }

    @Override
    public JDA getJDA() {
        return jda;
    }

    @Override
    public String getToken() {
        return "OTQyMDU4NzA0MTM0NjA2ODc4.Yge-iQ.URR78giuEonOBWwuExKpORVdm5U";
    }

    @Override
    public void getStatus() {
    }

    @Override
    public void connect() {
        try {
            this.jda = JDABuilder.createDefault(getToken()).build().awaitReady();
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        getJDA().shutdownNow();
    }
    public void sendEmbed(String type, String xrayer, String staff){
        EmbedBuilder embed = new EmbedBuilder();
        Date date = new Date();
        long unixTime = date.getTime() / 1000L;
        embed.setTitle("XRay Blacklist");
        if(type.equalsIgnoreCase("add")){
            embed.setDescription("Se ha agregado a un nuevo usuario a la lista de xrayers.");
            embed.setColor(Color.green);
        }else if(type.equalsIgnoreCase("remove")){
            embed.setDescription("Se ha removido a un nuevo usuario a la lista de xrayers.");
            embed.setColor(Color.red);
        }
        embed.setFooter("Feryx Network", "https://cdn.discordapp.com/attachments/945780709451235359/946169079612469298/IMG_20220205_195823.png");
        embed.addField("Usuario", xrayer, false);
        embed.addField("Staff", staff, false);
        embed.addField("Fecha", "<t:"+ unixTime +":f>", false);
        embed.setTimestamp(new Date().toInstant());
        System.out.println(new Date().toInstant().toEpochMilli());
        getChannel().sendMessageEmbeds(embed.build()).queue();
    }
}
