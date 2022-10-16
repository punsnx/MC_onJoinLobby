package com.sirisuk.sirisuk_onjoinlobby;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvents implements Listener {
    private String Rank;

    public ChatEvents() {
    }

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent e) {
        this.Rank = "%luckperms_groups%";
        this.Rank = PlaceholderAPI.setPlaceholders(e.getPlayer(), this.Rank);
        Player p = e.getPlayer();
        if (this.Rank.contains("Owner")) {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.RED + "♦" + ChatColor.WHITE + "]" + ChatColor.BLUE + p.getDisplayName() + ChatColor.DARK_AQUA + "✎ " + ChatColor.RESET + e.getMessage());
        } else {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.GREEN + "♦" + ChatColor.WHITE + "]" + ChatColor.BLUE + p.getDisplayName() + ChatColor.DARK_AQUA + "✎ " + ChatColor.RESET + e.getMessage());
        }

    }
}