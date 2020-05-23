package com.hadenwatne.realrockets.ui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RocketBlocks {
    public static ItemStack getOreRefinery(){
        ItemStack item = new ItemStack(Material.FURNACE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &7&lOre Refinery &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refine ore to create"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7various ore chunks"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getBiodieselReactor(){
        ItemStack item = new ItemStack(Material.COMPOSTER);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &2&lBiodiesel Reactor &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Mix and ferment plants to"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7create Biodiesel"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getWarheadForge(){
        ItemStack item = new ItemStack(Material.ANVIL);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &3&lWarhead Forge &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Combine Ore Chunks to craft"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7a warhead"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getEnrichedOre(){
        ItemStack item = new ItemStack(Material.CHARCOAL);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &eEnriched Ore &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Can be refined in an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getPurifiedChunk(){
        ItemStack item = new ItemStack(Material.GRAY_DYE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &6Purified Ore Chunk &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refined through an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getImpureChunk(){
        ItemStack item = new ItemStack(Material.GRAY_DYE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &7Impure Ore Chunk &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refined through an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getUnstableChunk(){
        ItemStack item = new ItemStack(Material.GRAY_DYE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &cUnstable Ore Chunk &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refined through an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getPurifiedWarhead(){
        ItemStack item = new ItemStack(Material.CLAY_BALL);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &6&lPurified Warhead &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7This would make for a fine weapon"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7of mass destruction"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getImpureWarhead(){
        ItemStack item = new ItemStack(Material.CLAY_BALL);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &7&lImpure Warhead &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7This &omight &7explode, if I"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7believe in myself"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getUnstableWarhead(){
        ItemStack item = new ItemStack(Material.CLAY_BALL);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &c&lUnstable Warhead &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Bombs away!"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getBiofuel(){
        ItemStack item = new ItemStack(Material.DRIED_KELP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &aBiofuel &f«"));
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getRocketHull(){
        ItemStack item = new ItemStack(Material.DIORITE_WALL);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &lRocket Hull &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7It's the perfect size for"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7a missile"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getUIBlock(){
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&o?"));
        item.setItemMeta(itemMeta);

        return item;
    }
}
