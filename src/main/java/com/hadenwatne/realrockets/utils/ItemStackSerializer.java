package com.hadenwatne.realrockets.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
A class that serializes and un-serializes ItemStack objects, preserving enchantments, lore, amounts, type, durability,
etc.

// TODO: doesn't work with potions yet
 */
public class ItemStackSerializer {
    public static final String cc = Character.toString(ChatColor.COLOR_CHAR);
    public static final String ccr = "#CC";
    
    public static String serialize(ItemStack item){
        JSONObject json = new JSONObject();

        json.put("type", item.getType());
        json.put("amount", item.getAmount());

        if(item.hasItemMeta()){
            JSONObject meta = new JSONObject();
            ItemMeta im = item.getItemMeta();
            Damageable dim = (Damageable)im;

            meta.put("damage", dim.getDamage());

            if(im.hasDisplayName())
                meta.put("displayname", im.getDisplayName().replaceAll(cc, ccr));

            if(im.hasLore()){
                List<String> oldLore = im.getLore();
                List<String> newLore = new ArrayList<>();

                for(String s : oldLore) {
                    newLore.add(s.replaceAll(cc, ccr));
                }

                meta.put("lore", newLore);
            }

            if(item.getType() == Material.WRITABLE_BOOK || item.getType()== Material.WRITTEN_BOOK){
                BookMeta bm = (BookMeta)im;
                JSONObject bookData = new JSONObject();
                List<String> oldPages = bm.getPages();
                List<String> newPages = new ArrayList<>();

                if(bm.hasTitle())
                    bookData.put("title", bm.getTitle().replaceAll(cc, ccr));

                if(bm.hasAuthor())
                    bookData.put("author", bm.getAuthor().replaceAll(cc, ccr));

                for(String s : oldPages) {
                    newPages.add(s.replaceAll(cc, ccr));
                }

                bookData.put("pages", newPages);
                meta.put("bookData", bookData);
            }

            json.put("meta", meta);
        }

        if(item.getEnchantments().size() > 0){
            JSONObject ench = new JSONObject();

            for(Enchantment e : item.getEnchantments().keySet()){
                ench.put(e.getKey().getKey(), item.getEnchantments().get(e));
            }

            json.put("enchantments", ench);
        }

        return json.toString();
    }

    public static ItemStack deserialize(String j){
        JSONObject json = new JSONObject(j);
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(json.getString("type"))));
        ItemMeta im = item.getItemMeta();
        Damageable dim = (Damageable)im;

        item.setAmount(json.getInt("amount"));

        if(json.has("meta")) {
            JSONObject meta = json.getJSONObject("meta");

            if (meta.has("damage"))
                dim.setDamage(meta.getInt("damage"));

            if (meta.has("displayname"))
                im.setDisplayName(meta.getString("displayname").replaceAll(ccr, cc));

            if (meta.has("lore")) {
                JSONArray lore = meta.getJSONArray("lore");
                List<String> newlore = new ArrayList<>();

                for (int i = 0; i < lore.length(); i++) {
                    newlore.add(lore.getString(i).replaceAll(ccr, cc));
                }

                im.setLore(newlore);
            }

            if(meta.has("bookData")){
                JSONObject bookData = meta.getJSONObject("bookData");
                BookMeta bm = (BookMeta)im;

                if(bookData.has("title"))
                    bm.setTitle(bookData.getString("title"));

                if(bookData.has("author"))
                    bm.setAuthor(bookData.getString("author"));

                JSONArray pages = bookData.getJSONArray("pages");
                List<String> newPages = new ArrayList<>();

                for (int i = 0; i < pages.length(); i++) {
                    newPages.add(pages.getString(i).replaceAll(ccr, cc));
                }

                bm.setPages(newPages);
            }
        }

        item.setItemMeta(im);

        if(json.has("enchantments")){
            JSONObject meta = json.getJSONObject("enchantments");

            for(String key : meta.keySet()){
                Enchantment e = Enchantment.getByKey(NamespacedKey.minecraft(key));

                if(e != null)
                    item.addUnsafeEnchantment(e, meta.getInt(key));
            }
        }

        return item;
    }
}
