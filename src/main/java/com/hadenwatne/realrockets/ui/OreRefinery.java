package com.hadenwatne.realrockets.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
}
