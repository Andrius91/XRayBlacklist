package team.yogurt.xrayblacklist.Managers;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Comparator;

public class SortByOnline implements Comparator<String> {
    @Override
    public int compare(String p1, String p2) {
        Player player1 = Bukkit.getPlayerExact(p1);
        Player player2 = Bukkit.getPlayerExact(p2);
        if(player1 != null && player2 == null){
            return -2;
        }else if(player1 != null && player2 != null){
            return -1;
        }else if(player1 == null && player2 == null){
            return 1;
        }else if(player1 == null && player2 != null){
            return 1;
        }else{
            return 0;
        }
    }
}
