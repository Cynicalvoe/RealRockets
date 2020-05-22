package com.hadenwatne.realrockets.storage;

import com.hadenwatne.realrockets.RealRockets;
import com.hadenwatne.realrockets.storage.UIMapperData;
import com.hadenwatne.realrockets.ui.BiodieselReactor;
import com.hadenwatne.realrockets.ui.IBlockUI;
import com.hadenwatne.realrockets.ui.OreRefinery;
import com.hadenwatne.realrockets.ui.WarheadForge;
import com.hadenwatne.realrockets.utils.ItemStackSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/*
This class maps a block Location to the type of IBlockUI associated. It also manages inventory contents for those GUIs.
This class should not do anything else - handle block breaking and placing somewhere else.
 */
public class UIMapper {
    private RealRockets plugin;
    private HashMap<Location, IBlockUI> blockMap;
    private UIMapperData data;

    public UIMapper(RealRockets c){
        plugin=c;
        blockMap = new HashMap<>();

        data = deserializeData();

        for(UIBlockData bd : data.getBlockData()) {
            IBlockUI b = null;

            // TODO add each UI type here as needed.
            switch(bd.getType()){
                case "OreRefinery":
                    b = new OreRefinery(plugin);
                    break;
                case "BiodieselReactor":
                    b = new BiodieselReactor(plugin);
                    break;
                case "WarheadForge":
                    b = new WarheadForge(plugin);
                    break;
            }

            if(b != null) {
                for (int slot : bd.getInventoryData().keySet()) {
                    ItemStack i = ItemStackSerializer.deserialize(bd.getInventoryData().get(slot));

                    b.getGUI().setItem(slot, i);
                }

                blockMap.put(bd.getLocation(), b);
                Bukkit.getPluginManager().registerEvents(b, plugin);
            }
        }

        data = null;
    }

    public HashMap<Location, IBlockUI> getBlockMap(){
        return blockMap;
    }

    public IBlockUI getUIAt(Location l){
        for(Location bl : blockMap.keySet()){
            if(bl.toVector().equals(l.toVector())){
                return blockMap.get(bl);
            }
        }

        return null;
    }

    public void serializeData(){
        data = new UIMapperData();

        // Add blocks
        for(Location l : blockMap.keySet()){
            IBlockUI b = blockMap.get(l);
            UIBlockData bd = new UIBlockData(b.getType(), l.getWorld().getName(), l.getBlockX(), l.getBlockY(), l.getBlockZ());

            for(int i=0; i<b.getGUI().getSize(); i++){
                ItemStack item = b.getGUI().getItem(i);

                if(item != null)
                    bd.getInventoryData().put(i, ItemStackSerializer.serialize(item));
            }

            data.getBlockData().add(bd);
        }

        String json = plugin.getGson().toJson(data);
        StorageController.saveToFile(plugin.getDataFolder().getPath(), json);
    }

    public UIMapperData deserializeData(){
        String data = StorageController.loadFromFile(plugin.getDataFolder().getPath());

        if(data != null){
            return plugin.getGson().fromJson(data, UIMapperData.class);
        }else{
            return new UIMapperData();
        }
    }
}
