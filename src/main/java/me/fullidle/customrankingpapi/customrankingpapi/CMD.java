package me.fullidle.customrankingpapi.customrankingpapi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.fullidle.customrankingpapi.customrankingpapi.Main.plugin;

public class CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length >= 1){
            String arg = args[0];
            System.out.println(PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer("FullIdle"),arg));
        }
        plugin.reloadConfig();
        sender.sendMessage("§aPlugin config.yml reloaded!");
        return false;
    }
}
