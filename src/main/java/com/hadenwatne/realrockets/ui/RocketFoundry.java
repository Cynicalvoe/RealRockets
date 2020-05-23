package com.hadenwatne.realrockets.ui;

import com.hadenwatne.realrockets.RealRockets;
import com.hadenwatne.realrockets.utils.ItemStackSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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

                    List<ItemStack> center = getItemsInCenter();
                    ItemStack warhead = null;
                    int warheadType = -1;

                    for(ItemStack c : center){
                        if(c != null) {
                            if (c.isSimilar(RocketBlocks.getImpureWarhead())) {
                                warhead = c;
                                warheadType = 0;
                                break;
                            } else if (c.isSimilar(RocketBlocks.getUnstableWarhead())) {
                                warhead = c;
                                warheadType = 1;
                                break;
                            } else if (c.isSimilar(RocketBlocks.getPurifiedWarhead())) {
                                warhead = c;
                                warheadType = 2;
                                break;
                            }
                        }
                    }

                    if(warhead != null){
                        ItemStack hull = RocketBlocks.getRocketHull();

                        if(center.contains(hull)) {
                            List<String> lore = new ArrayList<>();

                            gui.remove(warhead);
                            gui.remove(hull);

                            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Type: &e"+warheadType));

                            // Can this one target something?
                            ItemStack tpc = RocketBlocks.getTargetingComputer();

                            if(center.contains(tpc)) {
                                gui.remove(tpc);
                                lore.add(ChatColor.translateAlternateColorCodes('&', "&7Targeting: &aREADY"));
                            }

                            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Fuel: 0"));
                            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Primed: &cNO"));

                            ItemStack rocket = RocketBlocks.getRocketItem();
                            ItemMeta im = rocket.getItemMeta();
                            im.setLore(lore);
                            rocket.setItemMeta(im);

                            gui.setItem(13, rocket);
                        }
                    }

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

                    List<ItemStack> center = getItemsInCenter();
                    ItemStack r = RocketBlocks.getRocketItem();

                    for(ItemStack c : center) {
                        if (c != null) {
                            if(c.getType() == r.getType() && c.getItemMeta().hasDisplayName() && c.getItemMeta().getDisplayName().equals(r.getItemMeta().getDisplayName())){
                                if(c.getItemMeta().hasLore()){
                                    ItemMeta im = c.getItemMeta();
                                    List<String> lore = im.getLore();
                                    lore.set(im.getLore().size()-1, ChatColor.translateAlternateColorCodes('&', "&7Primed: &aYES"));
                                    im.setLore(lore);
                                    c.setItemMeta(im);

                                    gui.remove(c);
                                    gui.setItem(13, c);
                                }

                                break;
                            }
                        }
                    }
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

    private List<ItemStack> getItemsInCenter(){
        List<ItemStack> items = new ArrayList<>();

        for(int i=3; i<6; i++){
            items.add(gui.getItem(i));
        }

        for(int i=12; i<15; i++){
            items.add(gui.getItem(i));
        }

        for(int i=21; i<24; i++){
            items.add(gui.getItem(i));
        }

        return items;
    }
}