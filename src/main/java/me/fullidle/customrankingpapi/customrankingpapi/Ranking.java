package me.fullidle.customrankingpapi.customrankingpapi;

import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.stream.Collectors;

import static me.fullidle.customrankingpapi.customrankingpapi.Main.plugin;

@Getter
public class Ranking {
    public static final Comparator<PlayerValue> comparing = Comparator.comparing(PlayerValue::getValue);
    public static final Map<String,Ranking> cache = new HashMap<>();

    private final boolean disconnect;
    private final String format;
    private final int rankingVolume;
    private final boolean reverseOrder;
    private final String judgmentPapi;
    private List<PlayerValue> rankingList;
    private final String name;
    private final String exceedRanking;
    private final BukkitRunnable runnable;

    private Ranking(String key){
        this.name = key;
        disconnect = plugin.getConfig().getBoolean(key + ".disconnect");
        rankingVolume = plugin.getConfig().getInt(key + ".rankingVolume");
        reverseOrder = plugin.getConfig().getBoolean(key + ".reverseOrder");
        judgmentPapi = plugin.getConfig().getString(key + ".judgmentPapi");
        format = plugin.getConfig().getString(key + ".format");
        exceedRanking = plugin.getConfig().getString(key + ".exceedRanking");
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                ranking();
            }
        };
        runnable.runTaskTimerAsynchronously(plugin,0,20*10);
    }

    public String getRankingFormatInformation(int ranking){
        if (ranking > rankingList.size()-1){
            return exceedRanking.replace("{ranking}",String.valueOf(ranking));
        }
        PlayerValue playerValue = rankingList.get(ranking);
        return PlaceholderAPI
                .setPlaceholders(playerValue.getPlayer(),
                        format.replace("{ranking}",
                                        String.valueOf(ranking))
                .replace("&", "§"));
    }
    public String getNoDisconnectInformation(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getRankingVolume(); i++) {
            builder.append(getRankingFormatInformation(reverseOrder?rankingList.size()-(i+1):i));
            if (i != getRankingVolume()-1){
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public void ranking(){
        rankingList = Arrays.stream(Bukkit.getOfflinePlayers()).map(p ->
                new PlayerValue(p, Double.parseDouble(
                        PlaceholderAPI.setPlaceholders(p, judgmentPapi).equalsIgnoreCase("")?
                                "0":
                                PlaceholderAPI.setPlaceholders(p, judgmentPapi))))
                .sorted(reverseOrder?comparing.reversed():comparing).collect(Collectors.toList());
    }
    public void cancelRunnable(){
        this.runnable.cancel();
    }

    public static void clearCache(){
        for (Ranking value : cache.values()) {
            value.cancelRunnable();
        }
        cache.clear();
    }

    public static Ranking getInstance(String key){
        return cache.computeIfAbsent(key, Ranking::new);
    }
}
