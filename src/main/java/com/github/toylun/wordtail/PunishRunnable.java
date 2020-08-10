package com.github.toylun.wordtail;

import org.bukkit.entity.Player;

public class PunishRunnable implements Runnable {
    private Player player;
    public PunishRunnable(Player player) {
        this.player = player;
    }

    public void run() {
        this.player.damage(this.player.getHealth());
    }
}
