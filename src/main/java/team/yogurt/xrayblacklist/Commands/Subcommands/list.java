package team.yogurt.xrayblacklist.Commands.Subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.xrayblacklist.Managers.CommandManager;
import team.yogurt.xrayblacklist.Managers.SortByOnline;

import static team.yogurt.xrayblacklist.Utilities.color;
import static team.yogurt.xrayblacklist.XRayBlacklist.getList;

public class list extends CommandManager {

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Mirar la lista de xrayers";
    }

    @Override
    public String getSyntax() {
        return "/xrb list";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (getList().isEmpty()) {
            sender.sendMessage(color("&7Lista vacia."));
        } else {
            int pages = ((getList().size() - 1) / 10) + 1;
            int page = 0;
            getList().sort(new SortByOnline());
            if (args.length == 1) {
                sender.sendMessage(color("&eLista de xrayers:"));
                for (int entryid = page * 10; entryid < (page + 1) * 10 && entryid < getList().size(); entryid++) {
                    String entry = getList().get(entryid);
                    Player player = Bukkit.getPlayerExact(entry);
                    if(player != null){
                        sender.sendMessage(color("&7- &b" +entry + " &8(&a•" + "&8)"));
                    }else{
                        sender.sendMessage(color("&7- &b" +entry + " &8(&c•" + "&8)"));
                    }
                }
            } else if (args.length == 2) {
                page = Integer.parseInt(args[1]) -1;
                if(page > pages -1){
                    sender.sendMessage(color("&cNo hay más usuarios que mostrar."));
                    return;
                }
                sender.sendMessage(color("&eLista de xrayers:"));
                for (int entryid = page * 10; entryid < (page + 1) * 10 && entryid < getList().size(); entryid++) {
                    String entry = getList().get(entryid);
                    Player player = Bukkit.getPlayerExact(entry);
                    if(player != null){
                        sender.sendMessage(color("&7- &b" +entry + " &8(&a•" + "&8)"));
                    }else{
                        sender.sendMessage(color("&7- &b" +entry + " &8(&c•" + "&8)"));
                    }
                }
            }
            if(pages > 0){
                if(page < pages -1){
                    sender.sendMessage(color("&fSiguiente lista: &e/xrb list " + (page+2)));
                }
            }

        }

        //System.out.println("Paginas: " + (pages-1));


    }
}

