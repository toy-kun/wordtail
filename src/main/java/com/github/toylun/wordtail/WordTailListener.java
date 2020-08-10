package com.github.toylun.wordtail;

import org.bukkit.Bukkit;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;

public class WordTailListener implements Listener {
    final WordTail plugin;

    public WordTailListener (WordTail pl) {
        plugin = pl;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        Player sender = e.getPlayer();
        List<String> words = plugin.getConfig().getStringList("wordtail." + sender.getName());
        if (words == null || words.size() == 0) {
            return;
        }
        Boolean flg = false;
        for (String word : words) {
            plugin.getLogger().info(word + "$");
            if(msg.endsWith(word)) {
                return;
            }
        }
        // Action.
        switch (sender.getGameMode()) {
            case ADVENTURE:
            case SURVIVAL:
                e.setMessage("[不適切な発言]");
                Bukkit.getScheduler().runTask(plugin, new PunishRunnable(sender));
                break;
            case CREATIVE:
            case SPECTATOR:
                e.setMessage("[不適切な発言]");
                break;
        }

        return;
    }

}
