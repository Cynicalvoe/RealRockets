package com.hadenwatne.realrockets.ui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RocketBlocks {
    private ItemStack oreRefinery;

    public RocketBlocks(){
        createOreRefinery();
    }

    public ItemStack getOreRefinery(){
        return oreRefinery;
    }

    public void createOreRefinery(){
        oreRefinery = new ItemStack(Material.FURNACE);
        List<String> lore = new ArrayList<>();
        ItemMeta oreRefineryMeta = oreRefinery.getItemMeta();
        oreRefineryMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "» &7&lOre Refinery &f«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refine ore to create"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7various ore chunks"));

        oreRefineryMeta.setLore(lore);
        oreRefinery.setItemMeta(oreRefineryMeta);
    }
}
