package com.hadenwatne.realrockets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hadenwatne.realrockets.storage.UIMapper;
import org.bukkit.plugin.java.JavaPlugin;

public class RealRockets extends JavaPlugin {
    private Gson gson;
    private UIMapper mapper;

    public void onEnable(){
        GsonBuilder gb = new GsonBuilder();
        gson = gb.excludeFieldsWithoutExposeAnnotation().create();
        mapper = new UIMapper(this);

        RRCommand rrc = new RRCommand(this);

        this.getCommand("rr").setExecutor(rrc);
        this.getCommand("realrockets").setExecutor(rrc);
    }

    public void onDisable(){
        mapper.serializeData();
    }

    public UIMapper getMapper(){
        return mapper;
    }

    public Gson getGson(){
        return gson;
    }
}