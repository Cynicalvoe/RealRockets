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
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &7&lOre Refinery &8«"));

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
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &2&lBiodiesel Reactor &8«"));

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
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &3&lWarhead Forge &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Combine Ore Chunks to craft"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7a warhead"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getRocketFoundry(){
        ItemStack item = new ItemStack(Material.GRAY_GLAZED_TERRACOTTA);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &e&lRocket Foundry &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Construct rockets, add fuel,"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7set targets, and launch"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getEnrichedOre(){
        ItemStack item = new ItemStack(Material.CHARCOAL);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &eEnriched Ore &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Can be refined in an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getPurifiedChunk(){
        ItemStack item = new ItemStack(Material.GRAY_DYE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &6Purified Ore Chunk &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refined through an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getImpureChunk(){
        ItemStack item = new ItemStack(Material.GRAY_DYE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &7Impure Ore Chunk &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refined through an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getUnstableChunk(){
        ItemStack item = new ItemStack(Material.GRAY_DYE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &cUnstable Ore Chunk &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Refined through an Ore Refinery"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getPurifiedWarhead(){
        ItemStack item = new ItemStack(Material.CLAY_BALL);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &6&lPurified Warhead &8«"));

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
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &7&lImpure Warhead &8«"));

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
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &c&lUnstable Warhead &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Bombs away!"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getBiofuel(){
        ItemStack item = new ItemStack(Material.DRIED_KELP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &aBiofuel &8«"));
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getRocketHull(){
        ItemStack item = new ItemStack(Material.DIORITE_WALL);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &lRocket Hull &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7It's the perfect size for"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7a missile"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getUIBlock(){
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&o?"));
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getUIBuildRocket(){
        ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &a&lBuild Rocket &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Build a Rocket from the parts"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7in the center"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getUISetTarget(){
        ItemStack item = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &6&lSet Target &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Set the Rocket's target, if equipped"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7with a Targeting Computer"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getUIAddFuel(){
        ItemStack item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &lAdd Fuel &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Combine the Rocket with Biofuel"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7to increase flight time"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getUILaunch(){
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        List<String> lore = new ArrayList<>();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8» &4&lPrime for Launch &8«"));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Seal the chamber and ready the"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Rocket for launch"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }
}
