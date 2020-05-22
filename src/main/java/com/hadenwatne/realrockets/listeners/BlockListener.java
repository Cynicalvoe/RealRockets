package com.hadenwatne.realrockets.listeners;

import com.hadenwatne.realrockets.RealRockets;
import com.hadenwatne.realrockets.ui.BiodieselReactor;
import com.hadenwatne.realrockets.ui.IBlockUI;
import com.hadenwatne.realrockets.ui.OreRefinery;
import com.hadenwatne.realrockets.ui.RocketBlocks;
import com.hadenwatne.realrockets.utils.RandomUtil;
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
            // TODO add other types
            if (e.getItemInHand().isSimilar(RocketBlocks.getOreRefinery())) {
                IBlockUI b = new OreRefinery(plugin);

                plugin.getMapper().getBlockMap().put(e.getBlock().getLocation(), b);
                Bukkit.getPluginManager().registerEvents(b, plugin);
            }else if (e.getItemInHand().isSimilar(RocketBlocks.getBiodieselReactor())) {
                IBlockUI b = new BiodieselReactor(plugin);

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
                    if(i != null && !i.isSimilar(RocketBlocks.getUIBlock()))
                        e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), i);
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
            }
        }
    }
}
