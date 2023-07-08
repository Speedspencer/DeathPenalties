package com.yusaki.deathpenalties;

import com.yusaki.deathpenalties.commands.CommandManager;
import com.yusaki.deathpenalties.hanlders.DeathHandlers;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public final class DeathPenalties extends JavaPlugin {

    public static DeathPenalties instance;
    FileConfiguration config;
    Double dropPercentage = 50.0;

    @Override
    public void onEnable() {
        // Plugin startup logic
        reloadConfig();
        instance = this;
        Bukkit.getLogger().info("Enabling");
        DeathHandlers deathHandlers = new DeathHandlers(this);
        getCommand("deathpenalties").setExecutor(new CommandManager());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutdown");
    }

    public void reloadConfiguration() {
        super.reloadConfig();

        saveDefaultConfig();
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
        Bukkit.getLogger().info("Loaded config");
    }

    public String getPrefix() {
        return getConfig().getString("prefix") + " ";
    }
}
