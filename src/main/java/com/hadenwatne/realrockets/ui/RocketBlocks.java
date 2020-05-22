package com.hadenwatne.realrockets.ui;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

    public static ItemStack getEnrichedOre(){
        ItemStack item = new ItemStack(Material.CHARCOAL);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eEnriched Ore"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Can be refined in an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getPurifiedChunk(){
        ItemStack item = new ItemStack(Material.GRAY_DYE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Purified Ore Chunk"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refined through an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getImpureChunk(){
        ItemStack item = new ItemStack(Material.GRAY_DYE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Impure Ore Chunk"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refined through an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getUnstableChunk(){
        ItemStack item = new ItemStack(Material.GRAY_DYE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cUnstable Ore Chunk"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refined through an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getBiofuel(){
        ItemStack item = new ItemStack(Material.DRIED_KELP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aBiofuel"));
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
