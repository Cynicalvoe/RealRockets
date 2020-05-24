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
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                Player p = (Player)e.getWhoClicked();

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

                            playGoodSound(p);
                            return;
                        }
                    }

                    playBadSound(p);

                    return;
                }

                if(e.getCurrentItem().isSimilar(RocketBlocks.getUISetTarget())){
                    e.setCancelled(true);

                    List<ItemStack> center = getItemsInCenter();
                    ItemStack r = RocketBlocks.getRocketItem();

                    for(ItemStack c : center) {
                        if (c != null) {
                            if (c.getType() == r.getType() && c.getItemMeta().hasDisplayName() && c.getItemMeta().getDisplayName().equals(r.getItemMeta().getDisplayName())) {
                                if(c.getItemMeta().hasLore()) {
                                    ItemMeta im = c.getItemMeta();
                                    List<String> lore = im.getLore();
                                    boolean primed = lore.get(lore.size()-1).endsWith("YES");

                                    if(lore.get(1).contains("Targeting") && !primed){
                                        for(ItemStack cb : center) {
                                            if (cb != null && (cb.getType()==Material.WRITABLE_BOOK || cb.getType()==Material.WRITTEN_BOOK)) {
                                                BookMeta bm = (BookMeta)cb.getItemMeta();
                                                String coords = getCoordsFromBook(bm.getPage(1));

                                                if(coords != null){
                                                    lore.set(1, ChatColor.translateAlternateColorCodes('&', "&7Targeting: &a"+coords));
                                                    im.setLore(lore);
                                                    c.setItemMeta(im);

                                                    gui.remove(c);
                                                    gui.remove(cb);
                                                    gui.setItem(3, cb);
                                                    gui.setItem(13, c);

                                                    playGoodSound(p);
                                                }

                                                break;
                                            }
                                        }
                                    }else{
                                        playBadSound(p);
                                    }
                                }

                                break;
                            }
                        }
                    }

                    return;
                }

                if(e.getCurrentItem().isSimilar(RocketBlocks.getUIAddFuel())){
                    e.setCancelled(true);

                    List<ItemStack> center = getItemsInCenter();
                    ItemStack r = RocketBlocks.getRocketItem();
                    ItemStack f = RocketBlocks.getBiofuel();

                    for(ItemStack c : center) {
                        if (c != null) {
                            if(c.getType() == r.getType() && c.getItemMeta().hasDisplayName() && c.getItemMeta().getDisplayName().equals(r.getItemMeta().getDisplayName())){
                                if(c.getItemMeta().hasLore()){
                                    ItemMeta im = c.getItemMeta();
                                    List<String> lore = im.getLore();
                                    boolean primed = lore.get(lore.size()-1).endsWith("YES");

                                    if(!primed) {
                                        int startingFuel = extractNumberFromEnd(lore.get(im.getLore().size() - 2));
                                        int addingFuel = 0;

                                        for (ItemStack cb : center) {
                                            if (cb != null && cb.isSimilar(f)) {
                                                addingFuel += cb.getAmount();
                                                gui.remove(cb);
                                            }
                                        }

                                        lore.set(im.getLore().size() - 2, ChatColor.translateAlternateColorCodes('&', "&7Fuel: " + (startingFuel + addingFuel)));
                                        im.setLore(lore);
                                        c.setItemMeta(im);

                                        gui.remove(c);
                                        gui.setItem(13, c);

                                        playGoodSound(p);
                                    }else{
                                        playBadSound(p);
                                    }
                                }

                                break;
                            }
                        }
                    }


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
                                    boolean primed = lore.get(lore.size()-1).endsWith("YES");

                                    if(!primed){
                                        lore.set(im.getLore().size()-1, ChatColor.translateAlternateColorCodes('&', "&7Primed: &aYES"));
                                        im.setLore(lore);
                                        c.setItemMeta(im);

                                        gui.remove(c);
                                        gui.setItem(13, c);

                                        playGoodSound(p);
                                    }else{
                                        playBadSound(p);
                                    }
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

    private int extractNumberFromEnd(String s){
        Pattern p = Pattern.compile("(\\d+)$");
        Matcher m = p.matcher(s);

        if(m.find()){
            return Integer.parseInt(m.group(1));
        }else{
            return 0;
        }
    }

    private String getCoordsFromBook(String page){
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("((-?\\d+)(\\.\\d+)?,?\\s?)");
        Matcher m = p.matcher(page);

        while(m.find()){
            if(sb.length() > 0)
                sb.append(" ");

            sb.append(m.group(2));
        }

        if(sb.length()>0){
            String s = sb.toString();
            return s.split(" ").length == 3 ? s : null;
        }else{
            return null;
        }
    }

    private void playGoodSound(Player p){
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 5f, 5f);
    }

    private void playBadSound(Player p){
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 5f, 1f);
    }
}