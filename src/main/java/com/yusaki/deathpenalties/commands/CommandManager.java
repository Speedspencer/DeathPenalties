package com.yusaki.deathpenalties.commands;

import com.yusaki.deathpenalties.DeathPenalties;
import com.yusaki.deathpenalties.commands.subcommands.ReloadCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();
    public CommandManager(){
        subCommands.add(new ReloadCommand());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length > 0){
                for(int i = 0; i < getSubCommands().size(); i++){
                    if(args[0].equalsIgnoreCase(getSubCommands().get(i).getName())){
                        getSubCommands().get(i).perform(player, args);
                    }
                    else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', DeathPenalties.instance.getPrefix() + "&cInvalid arguments"));
                    }
                }
            }
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', DeathPenalties.instance.getPrefix() + "&aDeathpenalties v1.0.0"));
            }
        }
        return true;
    }

    public ArrayList<SubCommand> getSubCommands(){
        return subCommands;
    }
}
