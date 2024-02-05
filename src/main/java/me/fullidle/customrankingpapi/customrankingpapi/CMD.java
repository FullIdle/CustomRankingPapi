package me.fullidle.customrankingpapi.customrankingpapi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.fullidle.customrankingpapi.customrankingpapi.Main.plugin;

public class CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length >= 1){
            if (sender instanceof Player){
                sender.sendMessage(PlaceholderAPI.setPlaceholders(((Player) sender),args[0]));
            }
        }

        plugin.reloadConfig();
        sender.sendMessage("§aPlugin config.yml reloaded!");
        return false;
    }
}
