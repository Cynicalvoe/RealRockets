package com.hadenwatne.realrockets.ui;

import com.hadenwatne.realrockets.RealRockets;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class WarheadForge implements IBlockUI {
    private Inventory gui;
    private RealRockets plugin;
    private boolean isBuilding;
    private boolean active;

    public WarheadForge(RealRockets p){
        plugin = p;
        isBuilding = false;
        active = true;
        gui = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&3&lWarhead Forge"));

        gui.setItem(1, RocketBlocks.getUIBlock());
        gui.setItem(2, RocketBlocks.getUIBlock());
        gui.setItem(3, RocketBlocks.getUIBlock());
        gui.setItem(4, RocketBlocks.getUIBlock());
        gui.setItem(5, RocketBlocks.getUIBlock());
        gui.setItem(6, RocketBlocks.getUIBlock());
        gui.setItem(7, RocketBlocks.getUIBlock());
    }

    @Override
    public Inventory getGUI() {
        return gui;
    }

    @EventHandler
    public void onClickGUI(InventoryClickEvent e){
        if(e.getClickedInventory() != null && e.getClickedInventory().equals(gui)){
            if(e.getCurrentItem() != null && e.getCurrentItem().isSimilar(RocketBlocks.getUIBlock()))
                e.setCancelled(true);

            startTask();
        }
    }

    @Override
    public String getType() {
        return "WarheadForge";
    }

    @Override
    public void unregister() {
        active = false;
        InventoryClickEvent.getHandlerList().unregister(this);
    }

    @Override
    public void startTask() {
        if(!isBuilding) {
            isBuilding = true;

            new WarheadForgeTask(this).runTaskTimer(plugin, 20, 20);
        }
    }

    public void setBuilding(boolean r){
        isBuilding = r;
    }

    public boolean isActive(){
        return active;
    }

    public void playTick(){
        for(HumanEntity he : gui.getViewers()){
            Player p = (Player)he;

            p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 3f, 1f);
        }
    }

    public void playFinish(){
        for(HumanEntity he : gui.getViewers()){
            Player p = (Player)he;

            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3f, 1f);
        }
    }
}