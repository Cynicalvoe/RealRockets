package com.hadenwatne.realrockets.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class OreRefinery implements IBlockUI {
    private Inventory gui;

    public OreRefinery(){
        gui = Bukkit.createInventory(null, InventoryType.FURNACE, ChatColor.translateAlternateColorCodes('&', "&4Ore Refinery"));
    }

    @Override
    public Inventory getGUI() {
        return gui;
    }

    @EventHandler
    public void onClickGUI(InventoryClickEvent e){
        if(e.getClickedInventory() != null && e.getClickedInventory().equals(gui)){
            System.out.println("Clicked slot: "+e.getSlot());
        }
    }

    @Override
    public String getType() {
        return "OreRefinery";
    }
}