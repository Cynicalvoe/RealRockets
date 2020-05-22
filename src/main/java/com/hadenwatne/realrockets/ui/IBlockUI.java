package com.hadenwatne.realrockets.ui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

/*
Represents a block UI. Only one of these can exist for a given Location (i.e. tied to a Block). A mapper will record which
child of IBlockUI exists at the specified location. This interface exposes methods needed to perform the UI tasks.
 */
public interface IBlockUI extends Listener {
    public Inventory getGUI();
    public void onClickGUI(InventoryClickEvent e);
    public String getType();
}
