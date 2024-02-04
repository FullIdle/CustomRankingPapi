package me.fullidle.customrankingpapi.customrankingpapi;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.fullidle.customrankingpapi.customrankingpapi.Main.plugin;

public class MyListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, Ranking::updateAllRanking);
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, Ranking::updateAllRanking);
    }
}
