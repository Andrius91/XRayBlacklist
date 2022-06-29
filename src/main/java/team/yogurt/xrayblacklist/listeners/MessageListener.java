package team.yogurt.xrayblacklist.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import team.yogurt.xrayblacklist.XRayBlacklist;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.getAuthor().isBot()){
            XRayBlacklist.getDiscord().EXIST_MSG = true;
            XRayBlacklist.getDiscord().id = event.getMessageId();
        }
    }
    }
