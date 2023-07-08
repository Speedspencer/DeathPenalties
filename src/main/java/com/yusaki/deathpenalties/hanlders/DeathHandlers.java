package com.yusaki.deathpenalties.hanlders;

import com.yusaki.deathpenalties.DeathPenalties;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class DeathHandlers implements Listener {

    private final DeathPenalties plugin;
    private double dropPercentage = 50;

    private String prefix;
    private boolean debug = true;

    public DeathHandlers(DeathPenalties plugin){
        this.plugin = DeathPenalties.instance;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        loadConfig();
        Player player = event.getEntity();
        int droppedLevels = (int) Math.round(player.getLevel() * (dropPercentage / 100.0));
        int droppedExp = (int) Math.round(player.getExp() * (dropPercentage / 100.0));


        // Drop the experience orbs at the player's death location
        player.getWorld().spawn(player.getLocation(), ExperienceOrb.class, orb -> orb.setExperience(droppedLevels + droppedExp));

        player.setLevel(Math.abs(player.getLevel() - droppedLevels));
        player.setExp(Math.abs(player.getExp() - droppedExp));

        if (debug){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + dropPercentage + "% of your levels and experience were dropped"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', DeathPenalties.instance.getPrefix() + "Dropped " + droppedLevels + " Levels"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', DeathPenalties.instance.getPrefix() + "Dropped " + droppedExp + " Experience"));
        }

    }


    public void loadConfig() {
        // Load the drop percentage from config (if available)
        if (plugin.getConfig().contains("dropPercentage")) {
            dropPercentage = plugin.getConfig().getDouble("dropPercentage");
        }
        if (plugin.getConfig().contains("debug")) {
            debug = plugin.getConfig().getBoolean("debug");
        }
        if (plugin.getConfig().contains("prefix")) {
            prefix = plugin.getConfig().getString("prefix");
        }
        Bukkit.getLogger().info("Loaded config");
    }
}
