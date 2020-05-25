package com.hadenwatne.realrockets;

import com.hadenwatne.realrockets.utils.RocketUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class RocketFlight extends BukkitRunnable {
    private int fuel;
    private Location target;
    private Location start;
    private int type;
    private List<Location> rocket;
    private Player p;
    private BossBar bar;
    private boolean hasLaunched;
    private int secondsToLand;

    public RocketFlight(int f, String t, Location s, int wt, Player pl){
        fuel = f;
        start = s;
        type = wt;
        p = pl;
        hasLaunched = false;
        secondsToLand = 10;

        String[] coords = t.split(" ", 3);
        target = new Location(s.getWorld(), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));

        // Calculate total possible flight distance.
        double totalDistance = (double)fuel * 50d;

        // Calculate distance to target block.
        double distance = target.distance(start);

        // If the target is underground, adjust aim to the topmost block.
        for(int i=0; i<(256-target.getBlockY()); i++){
            Location targetRe = target.clone().add(0, i, 0);

            if(targetRe.getBlock().getType() != Material.AIR){
                target = targetRe;
            }
        }

        // If the target is in the air, adjust aim to the topmost block.
        if(target.getBlock().getType()==Material.AIR) {

            for (int i = 0; i < target.getBlockY(); i++) {
                Location targetRe = target.clone().subtract(0, i, 0);

                if (targetRe.getBlock().getType() != Material.AIR) {
                    target = targetRe;
                    break;
                }
            }
        }

        // If the distance is longer than fuel allows, pick the farthest block we can still reach.
        // TODO this algorithm sucks
        if(distance > totalDistance){
            if(totalDistance > 0) {
                Vector startVector = start.toVector();
                Vector midpoint = target.toVector().getMidpoint(startVector);

                if (startVector.distance(midpoint) > totalDistance) {
                    Vector newMidpoint = startVector.getMidpoint(midpoint);

                    while (startVector.distance(newMidpoint) > totalDistance) {
                        newMidpoint = newMidpoint.getMidpoint(startVector);
                    }

                    target = newMidpoint.toLocation(target.getWorld());

                } else {
                    Vector newMidpoint = midpoint.getMidpoint(target.toVector());

                    while (startVector.distance(newMidpoint) < totalDistance) {
                        newMidpoint = newMidpoint.getMidpoint(target.toVector());
                    }
                    ;

                    target = newMidpoint.toLocation(target.getWorld());
                }
            }else{
                target = start;
            }
        }

        // Fire at the target!
        rocket = RocketUtil.buildRocket(start);
        bar = Bukkit.createBossBar("Launch Countdown", BarColor.RED, BarStyle.SEGMENTED_10, BarFlag.DARKEN_SKY);
        bar.setProgress(1d);
        bar.addPlayer(p);
    }

    public void run(){
        if(!hasLaunched) {
            if (bar.getProgress() > 0.1d) {
                bar.setProgress(bar.getProgress() - 0.1d);

                if(p != null && p.isOnline())
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 5f, 5f);
            } else {
                bar.removeAll();

                for (Location l : rocket) {
                    l.getBlock().setType(Material.AIR);
                }

                for (int i = 0; i < 5; i++) {
                    start.getWorld().createExplosion(start, 1f);
                }

                hasLaunched = true;
            }
        }else{
            if(secondsToLand > 0) {
                secondsToLand--;
            }else{
                RocketUtil.explodeRocket(target, type);

                this.cancel();
            }
        }
    }
}
