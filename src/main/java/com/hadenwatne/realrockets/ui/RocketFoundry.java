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
        gui = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&e&lRocket Foundry"));

        gui.setItem(0, RocketBlocks.getUIBuildRocket());
        gui.setItem(1, RocketBlocks.getUISetTarget());
        gui.setItem(2, RocketBlocks.getUIBlock());
        gui.setItem(6, RocketBlocks.getUIBlock());
        gui.setItem(7, RocketBlocks.getUIBlock());
        gui.setItem(8, RocketBlocks.getUIBlock());
        gui.setItem(9, RocketBlocks.getUIAddFuel());
        gui.setItem(10, RocketBlocks.getUIBlock());
        gui.setItem(11, RocketBlocks.getUIBlock());
        gui.setItem(15, RocketBlocks.getUIBlock());
        gui.setItem(16, RocketBlocks.getUILaunch());
        gui.setItem(17, RocketBlocks.getUIBlock());
        gui.setItem(18, RocketBlocks.getUIBlock());
        gui.setItem(19, RocketBlocks.getUIBlock());
        gui.setItem(20, RocketBlocks.getUIBlock());
        gui.setItem(24, RocketBlocks.getUIBlock());
        gui.setItem(25, RocketBlocks.getUIBlock());
        gui.setItem(26, RocketBlocks.getUIBlock());
    }

    @Override
    public Inventory getGUI() {
        return gui;
    }

    @EventHandler
    public void onClickGUI(InventoryClickEvent e){
        if(e.getClickedInventory() != null && e.getClickedInventory().equals(gui)){
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().isSimilar(RocketBlocks.getUIBlock())){
                    e.setCancelled(true);
                    return;
                }

                if(e.getCurrentItem().isSimilar(RocketBlocks.getUIBuildRocket())){
                    e.setCancelled(true);
                    // TODO try to build a rocket using the items in slots 3-5, 12-14, 21-23
                    return;
                }

                if(e.getCurrentItem().isSimilar(RocketBlocks.getUISetTarget())){
                    e.setCancelled(true);
                    // TODO open another GUI or Book to collect coordinates
                    return;
                }

                if(e.getCurrentItem().isSimilar(RocketBlocks.getUIAddFuel())){
                    e.setCancelled(true);
                    // TODO combine Biofuel into the rocket, up to a certain amount, increasing how long it will stay
                    // TODO in flight (in blocks).
                    return;
                }

                if(e.getCurrentItem().isSimilar(RocketBlocks.getUILaunch())){
                    e.setCancelled(true);
                    // TODO set the item to be "Primed" - it can be placed down, and will immediately begin a launch
                    // TODO sequence
                    return;
                }
            }
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