package com.github.toylun.wordtail;

import org.bukkit.Bukkit;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Pattern;

public class WordTailListener implements Listener {
    final WordTail plugin;

    public WordTailListener (WordTail pl) {
        plugin = pl;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        String sender = e.getPlayer().getName();
        List<String> words = plugin.getConfig().getStringList("wordtail." + sender);
        if (words == null) {
            return;
        }
        Boolean flg = false;
        for (String word : words) {
            plugin.getLogger().info(word + "$");
            if(msg.endsWith(word)) {
                return;
            }
        }
        e.setMessage("[不適切な発言がありました]");
        return;
    }
}
