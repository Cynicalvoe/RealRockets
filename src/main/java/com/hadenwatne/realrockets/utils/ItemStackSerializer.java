package com.hadenwatne.realrockets.utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
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
 */
public class ItemStackSerializer {
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
                meta.put("displayname", im.getDisplayName());

            if(im.hasLore()){
                meta.put("lore", im.getLore());
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
                im.setDisplayName(meta.getString("displayname"));

            if (meta.has("lore")) {
                JSONArray lore = meta.getJSONArray("lore");
                List<String> newlore = new ArrayList<>();

                for (int i = 0; i < lore.length(); i++) {
                    newlore.add(lore.getString(i));
                }

                im.setLore(newlore);
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
