package com.sirisuk.sirisuk_onjoinlobby;

import java.util.ArrayList;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class MyEvents implements Listener {
    private String Rank;
    private String Balance;
    private String NowSongStatus;
    private String NowSongPlaying;
    private String NowSongVolumn;
    private String showIP;
    private String showPing;
    private Inventory gui;
    private ItemStack MyHeadProfile;
    private ItemStack HeadProfile;
    private ItemStack ServerMusic;
    private ItemStack hidePlayer;
    private ItemStack showPlayer;
    private ItemStack flyItem;
    private ItemMeta flyItemMeta;

    public MyEvents() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        boolean hasJoined = p.hasPlayedBefore();
        if (!hasJoined) {
            event.setJoinMessage(ChatColor.BLUE + "Welcome> " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " has Joined for " + ChatColor.LIGHT_PURPLE + "the first time!");
        } else {
            event.setJoinMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "✦" + ChatColor.WHITE + "]" + ChatColor.BLUE + p.getName() + ChatColor.GRAY + " has Joined!");
        }

        this.MyHeadProfile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta MyHeadMeta = (SkullMeta)this.MyHeadProfile.getItemMeta();
        MyHeadMeta.setDisplayName(ChatColor.GREEN + p.getName() + "'s " + ChatColor.YELLOW + "profile");
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.AQUA + "Click Me!");
        lore.add(ChatColor.YELLOW + "to view your profile");
        MyHeadMeta.setLore(lore);
        MyHeadMeta.setOwner(p.getName());
        this.MyHeadProfile.setItemMeta(MyHeadMeta);
        p.getInventory().setItem(5, this.MyHeadProfile);
    }

    @EventHandler
    public void getClickType(PlayerInteractEvent e) {
        ItemStack itemStack = e.getItem();
        Player p = e.getPlayer();
        if (itemStack != null) {
            if (itemStack.isSimilar(this.MyHeadProfile)) {
                this.Rank = "%luckperms_groups%";
                this.Balance = "%uecon_balance_Smile_total_unformatted%";
                this.showIP = "%player_ip%";
                this.showPing = "%player_ping%";
                if (Bukkit.getPluginManager().getPlugin("GMusic") != null) {
                    this.NowSongStatus = ChatColor.MAGIC + "xxxxx";
                    this.NowSongPlaying = ChatColor.MAGIC + "xxxxx";
                    this.NowSongVolumn = ChatColor.MAGIC + "xxxxx";
                } else {
                    this.NowSongStatus = ChatColor.MAGIC + "xxxxx";
                    this.NowSongPlaying = ChatColor.MAGIC + "xxxxx";
                    this.NowSongVolumn = ChatColor.MAGIC + "xxxxx";
                }

                this.Rank = PlaceholderAPI.setPlaceholders(e.getPlayer(), this.Rank);
                this.Balance = PlaceholderAPI.setPlaceholders(e.getPlayer(), this.Balance);
                this.showIP = PlaceholderAPI.setPlaceholders(e.getPlayer(), this.showIP);
                this.showPing = PlaceholderAPI.setPlaceholders(e.getPlayer(), this.showPing);
                if (this.Rank.contains("Owner")) {
                    this.gui = Bukkit.createInventory(p, 27, ChatColor.BLUE + p.getName() + "'s Profile " + ChatColor.RED + "[Owner]");
                } else {
                    this.gui = Bukkit.createInventory(p, 27, ChatColor.BLUE + p.getName() + "'s Profile");
                }

                this.HeadProfile = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta HeadMeta = (SkullMeta)this.HeadProfile.getItemMeta();
                HeadMeta.setDisplayName(ChatColor.RED + p.getName() + "'s profile");
                ArrayList<String> Headlore = new ArrayList();
                Headlore.add(ChatColor.YELLOW + "Name : " + ChatColor.WHITE + p.getName());
                Headlore.add(ChatColor.YELLOW + "UUID : " + ChatColor.WHITE + p.getUniqueId());
                Headlore.add(ChatColor.YELLOW + "Rank : " + ChatColor.WHITE + this.Rank);
                Headlore.add(ChatColor.YELLOW + "Balance : " + ChatColor.WHITE + this.Balance + ChatColor.GREEN + " Smile");
                Headlore.add(ChatColor.YELLOW + "Flight : " + ChatColor.WHITE + e.getPlayer().getAllowFlight());
                Headlore.add(ChatColor.YELLOW + "IP Address : " + ChatColor.MAGIC + this.showIP);
                Headlore.add(ChatColor.YELLOW + "Ping : " + ChatColor.MAGIC + this.showPing);
                HeadMeta.setLore(Headlore);
                HeadMeta.setOwner(p.getName());
                this.HeadProfile.setItemMeta(HeadMeta);
                this.ServerMusic = new ItemStack(Material.EMERALD);
                ItemMeta ServerMusicMeta = this.ServerMusic.getItemMeta();
                ServerMusicMeta.setDisplayName(ChatColor.DARK_AQUA + "Server Music");
                ArrayList<String> ServerMusicLore = new ArrayList();
                ServerMusicLore.add(ChatColor.GREEN + "Status : " + ChatColor.WHITE + this.NowSongStatus);
                ServerMusicLore.add(ChatColor.GREEN + "Song : " + ChatColor.WHITE + this.NowSongPlaying);
                ServerMusicLore.add(ChatColor.GREEN + "Volumn : " + ChatColor.WHITE + this.NowSongVolumn);
                ServerMusicMeta.setLore(ServerMusicLore);
                this.ServerMusic.setItemMeta(ServerMusicMeta);
                this.flyItem = new ItemStack(Material.FEATHER);
                this.flyItemMeta = this.flyItem.getItemMeta();
                this.gui.setItem(11, this.HeadProfile);
                this.gui.setItem(15, this.ServerMusic);
                if (this.Rank.contains("Owner")) {
                    if (!e.getPlayer().getAllowFlight()) {
                        this.flyItemMeta.setDisplayName(ChatColor.WHITE + "Enable Flight?" + ChatColor.DARK_AQUA + " NOW : " + ChatColor.RED + "✘");
                        this.flyItem.setItemMeta(this.flyItemMeta);
                        this.gui.setItem(22, this.flyItem);
                    } else {
                        this.flyItemMeta.setDisplayName(ChatColor.WHITE + "Disable Flight?" + ChatColor.DARK_AQUA + " NOW : " + ChatColor.GREEN + "✔");
                        this.flyItem.setItemMeta(this.flyItemMeta);
                        this.gui.setItem(22, this.flyItem);
                    }

                    this.gui.setItem(13, this.showPlayer);
                } else {
                    if (!e.getPlayer().getAllowFlight()) {
                        this.flyItemMeta.setDisplayName(ChatColor.WHITE + "Enable Flight?" + ChatColor.DARK_AQUA + " NOW : " + ChatColor.RED + "✘");
                        this.flyItem.setItemMeta(this.flyItemMeta);
                        this.gui.setItem(22, this.flyItem);
                    } else {
                        this.flyItemMeta.setDisplayName(ChatColor.WHITE + "Disable Flight?" + ChatColor.DARK_AQUA + " NOW : " + ChatColor.GREEN + "✔");
                        this.flyItem.setItemMeta(this.flyItemMeta);
                        this.gui.setItem(22, this.flyItem);
                    }

                    this.gui.setItem(13, this.showPlayer);
                }

                p.openInventory(this.gui);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2.0F, 12.0F);
            }

            if (itemStack.getType() == Material.ENDER_CHEST || itemStack.getType() == Material.COMPASS) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2.0F, 12.0F);
            }

        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        this.hidePlayer = new ItemStack(Material.GRAY_DYE);
        this.showPlayer = new ItemStack(Material.LIME_DYE);
        ItemMeta hidePLayerMeta = this.hidePlayer.getItemMeta();
        ItemMeta showPLayerMeta = this.hidePlayer.getItemMeta();
        hidePLayerMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Show Player");
        showPLayerMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Hide Player");
        this.hidePlayer.setItemMeta(hidePLayerMeta);
        this.showPlayer.setItemMeta(showPLayerMeta);
        if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
            if (!e.getInventory().equals(this.gui)) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Server Selector")) {
                    e.setCancelled(true);
                }

                if (e.getCurrentItem().isSimilar(this.MyHeadProfile)) {
                    e.setCancelled(true);
                }
            } else if (e.getInventory().equals(this.gui)) {
                if (e.getCurrentItem().isSimilar(this.HeadProfile)) {
                    Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).playSound(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2.0F, 12.0F);
                    e.setCancelled(true);
                }

                if (e.getCurrentItem().isSimilar(this.ServerMusic)) {
                    Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).playSound(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2.0F, 12.0F);
                    e.setCancelled(true);
                }

                if (e.getCurrentItem().isSimilar(this.showPlayer)) {
                    e.getWhoClicked().sendMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + "All players " + ChatColor.RED + "Invisable!");
                    this.gui.setItem(13, this.hidePlayer);
                    Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).playSound(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2.0F, 12.0F);
                    e.setCancelled(true);
                } else if (e.getCurrentItem().isSimilar(this.hidePlayer)) {
                    e.getWhoClicked().sendMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GRAY + "All players " + ChatColor.GREEN + "Visable!");
                    this.gui.setItem(13, this.showPlayer);
                    Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).playSound(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2.0F, 12.0F);
                    e.setCancelled(true);
                }

                if (e.getCurrentItem().isSimilar(this.flyItem)) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Enable")) {
                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).setAllowFlight(true);
                        e.getWhoClicked().sendMessage(ChatColor.BLUE + "Lobby> " + ChatColor.GREEN + "Allow " + ChatColor.GRAY + "Flight!");
                        this.flyItemMeta.setDisplayName(ChatColor.WHITE + "Disable Flight?" + ChatColor.DARK_AQUA + " NOW : " + ChatColor.GREEN + "✔");
                        this.flyItem.setItemMeta(this.flyItemMeta);
                        this.gui.setItem(22, this.flyItem);
                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).playSound(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2.0F, 12.0F);
                        e.setCancelled(true);
                    } else {
                        if (!e.getCurrentItem().getItemMeta().getDisplayName().contains("Disable")) {
                            return;
                        }

                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).setAllowFlight(false);
                        e.getWhoClicked().sendMessage(ChatColor.BLUE + "Lobby> " + ChatColor.RED + "Not Allow " + ChatColor.GRAY + "Flight!");
                        this.flyItemMeta.setDisplayName(ChatColor.WHITE + "Enable Flight?" + ChatColor.DARK_AQUA + " NOW : " + ChatColor.RED + "✘");
                        this.flyItem.setItemMeta(this.flyItemMeta);
                        this.gui.setItem(22, this.flyItem);
                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).playSound(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2.0F, 12.0F);
                        e.setCancelled(true);
                    }
                }
            }

        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        event.setQuitMessage(ChatColor.RED + "✦" + ChatColor.BLUE + p.getName() + ChatColor.GRAY + "has left!");
    }
}