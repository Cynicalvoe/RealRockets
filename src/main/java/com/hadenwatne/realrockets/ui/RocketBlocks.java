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

    public static ItemStack getBiofuel(){
        ItemStack item = new ItemStack(Material.DRIED_KELP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f» &aBiofuel &f«"));
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
