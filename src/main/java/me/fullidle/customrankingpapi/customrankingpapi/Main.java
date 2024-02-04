package me.fullidle.customrankingpapi.customrankingpapi;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    CRPapi crPapi = new CRPapi();
    public static Main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        reloadConfig();

        getCommand(plugin.getDescription().getName().toLowerCase()).setExecutor(new CMD());

        crPapi.register();
        getLogger().info("§aPlugin is enabled!");
    }

    @Override
    public void reloadConfig() {
        Ranking.clearCache();

        saveDefaultConfig();
        super.reloadConfig();

        for (String key : getConfig().getKeys(false)) {
            Ranking.getInstance(key);
        }
    }

    @Override
    public void onDisable() {
        crPapi.unregister();
    }
}