package com.hadenwatne.realrockets.listeners;

import com.hadenwatne.realrockets.RealRockets;
import com.hadenwatne.realrockets.ui.*;
import com.hadenwatne.realrockets.utils.RandomUtil;
import com.hadenwatne.realrockets.utils.RocketUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {
    private RealRockets plugin;

    public BlockListener(RealRockets c){
        plugin = c;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockPlace(BlockPlaceEvent e){
        if(!e.isCancelled()) {
            if (e.getItemInHand().isSimilar(RocketBlocks.getOreRefinery())) {
                IBlockUI b = new OreRefinery(plugin);

                plugin.getMapper().getBlockMap().put(e.getBlock().getLocation(), b);
                Bukkit.getPluginManager().registerEvents(b, plugin);
            } else if (e.getItemInHand().isSimilar(RocketBlocks.getBiodieselReactor())) {
                IBlockUI b = new BiodieselReactor(plugin);

                plugin.getMapper().getBlockMap().put(e.getBlock().getLocation(), b);
                Bukkit.getPluginManager().registerEvents(b, plugin);
            } else if (e.getItemInHand().isSimilar(RocketBlocks.getWarheadForge())) {
                IBlockUI b = new WarheadForge(plugin);

                plugin.getMapper().getBlockMap().put(e.getBlock().getLocation(), b);
                Bukkit.getPluginManager().registerEvents(b, plugin);
            } else if (e.getItemInHand().isSimilar(RocketBlocks.getRocketFoundry())) {
                IBlockUI b = new RocketFoundry(plugin);

                plugin.getMapper().getBlockMap().put(e.getBlock().getLocation(), b);
                Bukkit.getPluginManager().registerEvents(b, plugin);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockBreak(BlockBreakEvent e){
        if(!e.isCancelled()) {
            IBlockUI b = plugin.getMapper().getBlockMap().get(e.getBlock().getLocation());

            if(b != null){
                for(ItemStack i : b.getGUI().getContents()){
                    if(i != null) {
                        if(!i.isSimilar(RocketBlocks.getUIBlock())
                                && !i.isSimilar(RocketBlocks.getUILaunch())
                                && !i.isSimilar(RocketBlocks.getUIAddFuel())
                                && !i.isSimilar(RocketBlocks.getUISetTarget())
                                && !i.isSimilar(RocketBlocks.getUIBuildRocket())) {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), i);
                        }
                    }
                }

                b.unregister();
                plugin.getMapper().getBlockMap().remove(e.getBlock().getLocation());
            }else{
                if(e.getBlock().getType() == Material.STONE && e.isDropItems()){
                    if(RandomUtil.getInt(50) == 0){
                        e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), RocketBlocks.getEnrichedOre());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClickBlock(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            IBlockUI b = plugin.getMapper().getBlockMap().get(e.getClickedBlock().getLocation());

            if(b != null){
                e.setCancelled(true);
                e.getPlayer().openInventory(b.getGUI());
                return;
            }

            ItemStack br = RocketBlocks.getRocketItem();
            ItemStack h = e.getItem();

            if (h.getType() == br.getType() && h.getItemMeta().hasDisplayName() && h.getItemMeta().getDisplayName().equals(br.getItemMeta().getDisplayName())) {
                // TODO do this instead RocketUtil.buildRocket(e.getClickedBlock().getLocation());
                RocketUtil.explodeRocket(e.getClickedBlock().getLocation());
                // TODO launch!
                // TODO if the target is underground, strike the topmost block. Rockets can't phase through the ground!
            }
        }
    }
}
