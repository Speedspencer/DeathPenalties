package com.yusaki.deathpenalties.commands.subcommands;

import com.yusaki.deathpenalties.DeathPenalties;
import com.yusaki.deathpenalties.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the config";
    }

    @Override
    public String getSyntax() {
        return "/deathpenalties reload";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length == 1) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', DeathPenalties.instance.getPrefix() + "&cPlugin reloaded"));
            DeathPenalties.instance.reloadConfiguration();
        }
        else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', DeathPenalties.instance.getPrefix() + "&cInvalid arguments"));
        }
    }


}
