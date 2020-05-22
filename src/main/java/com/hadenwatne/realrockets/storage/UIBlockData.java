package com.hadenwatne.realrockets.storage;

import com.google.gson.annotations.Expose;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UIBlockData {
    @Expose
    private HashMap<Map<String, Object>, Integer> inventoryData;
    @Expose
    private String type;
    @Expose
    private String world;
    @Expose
    private int x;
    @Expose
    private int y;
    @Expose
    private int z;

    public UIBlockData(String bt, String bw, int bx, int by, int bz){
        inventoryData = new HashMap<Map<String, Object>, Integer>();
        type = bt;
        world = bw;
        x = bx;
        y = by;
        z = bz;
    }

    public HashMap<Map<String, Object>, Integer> getInventoryData(){
        return inventoryData;
    }

    public String getType(){
        return type;
    }

    public Location getLocation(){
        return new Location(Bukkit.getWorld(world), x, y, z);
    }
}
