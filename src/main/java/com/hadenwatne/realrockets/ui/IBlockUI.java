package com.hadenwatne.realrockets.ui;

import org.bukkit.inventory.Inventory;

/*
Represents a block UI. Only one of these can exist for a given Location (i.e. tied to a Block). A mapper will record which
child of IBlockUI exists at the specified location. This interface exposes methods needed to perform the UI tasks.
 */
public interface IBlockUI {
    public Inventory getGUI();
}
