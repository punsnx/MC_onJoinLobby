package com.sirisuk.sirisuk_onjoinlobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public Main() {
    }

    public void onEnable() {
        System.out.println("onJoinLobby is Enabled!");
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.getServer().getPluginManager().registerEvents(new MyEvents(), this);
            this.getServer().getPluginManager().registerEvents(new ChatEvents(), this);
        } else {
            this.getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

    }

    public void onDisable() {
        System.out.println("onJoinLobby is Disabled!");
    }
}
