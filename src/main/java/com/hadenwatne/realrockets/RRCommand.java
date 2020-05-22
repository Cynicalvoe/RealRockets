package com.hadenwatne.realrockets;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RRCommand implements CommandExecutor {
    private RealRockets plugin;

    public RRCommand(RealRockets c){
        plugin = c;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("Command under development");
        return true;
    }
}
