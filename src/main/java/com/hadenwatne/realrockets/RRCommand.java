package com.hadenwatne.realrockets;

import com.hadenwatne.realrockets.ui.RocketBlocks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RRCommand implements CommandExecutor {
    private RealRockets plugin;

    public RRCommand(RealRockets c){
        plugin = c;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("Command under development");

        if(sender instanceof Player){
            Player p = (Player)sender;

            p.getInventory().addItem(RocketBlocks.getBiofuel());
            p.getInventory().addItem(RocketBlocks.getEnrichedOre());
            p.getInventory().addItem(RocketBlocks.getImpureChunk());
            p.getInventory().addItem(RocketBlocks.getUnstableChunk());
            p.getInventory().addItem(RocketBlocks.getPurifiedChunk());
            p.getInventory().addItem(RocketBlocks.getOreRefinery());
            p.getInventory().addItem(RocketBlocks.getBiodieselReactor());
            p.getInventory().addItem(RocketBlocks.getImpureWarhead());
            p.getInventory().addItem(RocketBlocks.getPurifiedWarhead());
            p.getInventory().addItem(RocketBlocks.getUnstableWarhead());
            p.getInventory().addItem(RocketBlocks.getWarheadForge());
            p.getInventory().addItem(RocketBlocks.getRocketHull());
            p.getInventory().addItem(RocketBlocks.getRocketFoundry());
        }

        return true;
    }
}
