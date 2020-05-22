package com.hadenwatne.realrockets;

import org.bukkit.plugin.java.JavaPlugin;

public class RealRockets extends JavaPlugin {
    public void onEnable(){
        RRCommand rrc = new RRCommand(this);

        this.getCommand("rr").setExecutor(rrc);
        this.getCommand("realrockets").setExecutor(rrc);
    }
}