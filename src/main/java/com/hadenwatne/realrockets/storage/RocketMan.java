package com.hadenwatne.realrockets.storage;

import com.hadenwatne.realrockets.RealRockets;
import com.hadenwatne.realrockets.utils.RocketUtil;

import java.util.HashMap;

public class RocketMan {
    private RealRockets plugin;
    private HashMap<String, String> configValues;

    public RocketMan(RealRockets c){
        plugin = c;
        configValues = new HashMap<String, String>();

        plugin.saveDefaultConfig();
        loadFromFile();
    }

    public boolean getEnableFleija() {
        try {
            return Boolean.parseBoolean(configValues.get("enableFleija"));
        }catch (Exception e){
            configValues.put("enableFleija", "true");
            plugin.getConfig().set("enableFleija", true);
            return true;
        }
    }

    public int getOreDropChance() {
        try {
            int c = Integer.parseInt(configValues.get("oreDropChance"));
            return c > 0 ? c : 45;
        }catch (Exception e){
            configValues.put("oreDropChance", "45");
            plugin.getConfig().set("oreDropChance", 45);
            return 45;
        }
    }

    public void loadFromFile(){
        for(String key : plugin.getConfig().getKeys(true)){
            configValues.put(key, plugin.getConfig().get(key).toString());
        }
    }
}
