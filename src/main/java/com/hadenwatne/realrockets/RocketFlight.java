package com.hadenwatne.realrockets;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class RocketFlight extends BukkitRunnable {
    private int fuel;
    private Location target;
    private Location start;
    private int type;

    public RocketFlight(int f, String t, Location s, int wt){
        fuel = f;
        start = s;
        type = wt;

        String[] coords = t.split(" ", 3);
        target = new Location(s.getWorld(), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));

        // Calculate total possible flight distance.
        double totalDistance = (double)fuel * 50d;

        // Calculate distance to target block.
        double distance = target.distance(start);

        // If the distance is longer than fuel allows, pick the farthest block we can still reach.
        // TODO this algorithm sucks
        if(distance > totalDistance){
            Vector startVector = start.toVector();
            Vector midpoint = target.toVector().getMidpoint(startVector);

            if(startVector.distance(midpoint) > totalDistance){
                Vector newMidpoint = startVector.getMidpoint(midpoint);

                while(startVector.distance(newMidpoint) > totalDistance){
                    newMidpoint = newMidpoint.getMidpoint(startVector);
                }

                target = newMidpoint.toLocation(target.getWorld());

            }else{
                Vector newMidpoint = midpoint.getMidpoint(target.toVector());

                while(startVector.distance(newMidpoint) < totalDistance){
                    newMidpoint = newMidpoint.getMidpoint(target.toVector());
                };

                target = newMidpoint.toLocation(target.getWorld());
            }
        }
        // Fire at the target!
    }

    public void run(){

    }
}
