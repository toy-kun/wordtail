package com.github.toylun.wordtail;

import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public final class WordTail extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new WordTailListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("setwt")){
            if (args.length != 2) {
                sender.sendMessage(ChatColor.RED + "error: setwd [player] [語尾]");
            }
            String player = args[0];
            String word = args[1];
            if (Bukkit.getPlayer(player) == null) {
                getLogger().info(ChatColor.RED + "error: This player is not found.");
                return false;
            }
            List<String> words = getConfig().getStringList("wordtail." + player);
            if (words == null) {
                words = new ArrayList<String>(Arrays.asList(word));
            } else {
                words.add(word);
            }
            getConfig().set("wordtail." + player, words);
            saveConfig();
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("listwt")){
            ConfigurationSection wt_list = getConfig().getConfigurationSection("wordtail");
            if (wt_list == null) {
                sender.sendMessage(ChatColor.GREEN + "No registered words.");
                return true;
            }
            Set<String> players = wt_list.getKeys(false);
            for (Object player : players) {
                String words = player + " : ";
                for (String word : getConfig().getStringList("wordtail." + player)) {
                    words += word + ", ";
                }
                sender.sendMessage(ChatColor.GREEN + words);
            }
            return true;
        }

        return false;
    }

}
