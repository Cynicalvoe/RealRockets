package com.hadenwatne.realrockets.listeners;

import com.hadenwatne.realrockets.RealRockets;
import com.hadenwatne.realrockets.RocketFlight;
import com.hadenwatne.realrockets.ui.*;
import com.hadenwatne.realrockets.utils.RandomUtil;
import com.hadenwatne.realrockets.utils.RocketUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                //RocketUtil.explodeRocket(e.getClickedBlock().getLocation());
                // TODO launch!
                // TODO if the target is underground, strike the topmost block. Rockets can't phase through the ground!
                // TODO must be primed in order to begin
                Location l = e.getClickedBlock().getLocation();

                if(h.getItemMeta().hasLore()){
                    List<String> lore = h.getItemMeta().getLore();

                    int type = Integer.parseInt(getEndNumbers(lore.get(0)));
                    String target = l.getBlockX()+" "+l.getBlockY()+" "+l.getBlockZ();
                    int fuel = 0;
                    boolean primed = false;

                    if(lore.size() == 4){
                        target = getEndNumbers(lore.get(1));
                        fuel = Integer.parseInt(getEndNumbers(lore.get(2)));
                        primed = lore.get(3).endsWith("YES");
                    }else{
                        fuel = Integer.parseInt(getEndNumbers(lore.get(1)));
                        primed = lore.get(2).endsWith("YES");
                    }

                    if(primed) {
                        new RocketFlight(fuel, target, l, type, e.getPlayer()).runTaskTimer(plugin, 20, 20);
                    }else{
                        e.setCancelled(true);
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 5f, 1f);
                    }
                }
            }
        }
    }

    private String getEndNumbers(String s){
        Pattern p = Pattern.compile("((-?\\d+)(\\s(-?\\d+)\\s(-?\\d+))?)$");
        Matcher m = p.matcher(s);

        if(m.find()){
            return m.group(1);
        }else{
            return null;
        }
    }
}
