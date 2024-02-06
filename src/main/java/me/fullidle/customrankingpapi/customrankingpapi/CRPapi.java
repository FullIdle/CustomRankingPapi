package me.fullidle.customrankingpapi.customrankingpapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

import java.util.List;

import static me.fullidle.customrankingpapi.customrankingpapi.Main.plugin;

public class CRPapi extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return plugin.getDescription().getName().toLowerCase();
    }

    @Override
    public String getAuthor() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        List<String> authors = plugin.getDescription().getAuthors();
        for (int i = 0; i < authors.size(); i++) {
            String s = authors.get(i);
            builder.append(s);
            if (i != authors.size()-1){
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        String[] split = params.split("_");
        final String paKey = split[0];
        final Ranking ranking = Ranking.getInstance(paKey);
        if (ranking == null){
            plugin.getServer().getConsoleSender().sendMessage("non-existent key:"+paKey);
            return null;
        }
        if (ranking.isDisconnect()){
            if (split.length != 2){
                return null;
            }
            int i;
            try {
                i = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                return null;
            }
            return ranking.getRankingFormatInformation(i);
        }
        return ranking.getNoDisconnectInformation();
    }
}
