package com.hadenwatne.realrockets.ui;

import com.hadenwatne.realrockets.RealRockets;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class RocketFoundry implements IBlockUI {
    private Inventory gui;
    private RealRockets plugin;

    public RocketFoundry(RealRockets p){
        plugin = p;
        gui = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', "&e&lRocket Foundry"));

        gui.setItem(0, RocketBlocks.getUIBlock());
        gui.setItem(1, RocketBlocks.getUIBlock());
        gui.setItem(2, RocketBlocks.getUIBlock());
    }

    @Override
    public Inventory getGUI() {
        return gui;
    }

    @EventHandler
    public void onClickGUI(InventoryClickEvent e){
        if(e.getClickedInventory() != null && e.getClickedInventory().equals(gui)){
            if(e.getCurrentItem().isSimilar(RocketBlocks.getUIBlock()))
                e.setCancelled(true);

            // TODO this class will be a multi-purpose UI for building a rocket, adding fuel to the rocket, and launching
            // TODO the rocket. Rockets won't need to sync anymore - tied to the station they were built with.
        }
    }

    @Override
    public String getType() {
        return "RocketFoundry";
    }

    @Override
    public void unregister() {
        InventoryClickEvent.getHandlerList().unregister(this);
    }
}