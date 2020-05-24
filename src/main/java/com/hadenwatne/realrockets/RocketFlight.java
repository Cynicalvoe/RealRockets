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
        if(distance > totalDistance){
            System.out.println("Longer than allowed distance! "+distance+" (max. "+totalDistance+")");
            Vector startVector = start.toVector();
            Vector midpoint = target.toVector().getMidpoint(startVector);

            System.out.println("Trying midpoint: "+startVector.distance(midpoint));
            if(startVector.distance(midpoint) > totalDistance){
                System.out.println("Midpoint is still larger. Shrinking...");

                // TODO this doesn't work great
                // get the midpoint of that (min: start, max:midpoint); repeat until we find a midpoint that's not farther, and use that one.
                Vector newMidpoint;

                do{
                    newMidpoint = startVector.getMidpoint(midpoint);
                }while(startVector.distance(newMidpoint) > totalDistance);

                System.out.println("Settled on a point at distance "+startVector.distance(newMidpoint)+" ("+totalDistance+")");

                target = newMidpoint.toLocation(target.getWorld());


                
            }else{
                // get the midpoint of that (min: midpoint, max: target); repeat until we find a midpoint that's farther, then stop at the previous.
                System.out.println("Pick a target farther away plz. "+startVector.distance(midpoint)+" (max. "+totalDistance+")");
            }
        }else{
            System.out.println("Pick a target farther away plz. "+distance+" (max. "+totalDistance+")");
        }

        // Fire at the target!
    }

    public void run(){

    }
}
