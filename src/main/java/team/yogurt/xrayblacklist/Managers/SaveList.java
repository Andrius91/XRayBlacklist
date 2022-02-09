package team.yogurt.xrayblacklist.Managers;

import team.yogurt.xrayblacklist.Utilities;
import team.yogurt.xrayblacklist.XRayBlacklist;

import java.io.*;

public class SaveList {

    private static final File list = new File(XRayBlacklist.getInstance().getDataFolder(), "players.txt");

    public static void loadList() throws IOException {
        if(list.exists()){
            BufferedReader br = new BufferedReader(new FileReader(list));
            String st;
            int amount = 0;
            while ((st = br.readLine()) != null) {
                String[] player = st.split("- ");
                XRayBlacklist.getList().add(player[1]);
                amount++;
            }
            Utilities.sendMessage("&fSe han agregado &b"+amount+"&f xrayer(s) al caché");
        }
    }

    public static void saveList() {
        try {
            FileWriter writer = new FileWriter(list);
            if (!list.exists()) {
                list.mkdir();
            }
            for (String player : XRayBlacklist.getList()) {
                writer.write("- " + player + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
