package com.hadenwatne.realrockets.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class RocketUtil {
    private static final String[] rocketPattern = new String[]{
            "AAAAA/AACAA/ACCCA/AACAA/AAAAA",
            "AAIAA/AAIAA/IIIII/AAIAA/AAIAA",
            "AAAAA/AAIAA/AIIIA/AAIAA/AAAAA",
            "AAAAA/AAAAA/AACAA/AAAAA/AAAAA",
            "AAAAA/AAAAA/AACAA/AAAAA/AAAAA",
            "AAAAA/AAAAA/AACAA/AAAAA/AAAAA",
            "AAAAA/AAAAA/AACAA/AAAAA/AAAAA",
            "AAAAA/AAAAA/AARAA/AAAAA/AAAAA"
    };

    public static void buildRocket(Location bCenter){
        int xc = -2;
        int yc = 1;
        int zc = -2;

        for(String layer : rocketPattern){
            for(String row : layer.split("/")){
                for(char b : row.toCharArray()){
                    switch(b){
                        case 'A':
                            break;
                        case 'C':
                            bCenter.clone().add(xc, yc, zc).getBlock().setType(Material.COAL_BLOCK);
                            //sendBlockUpdates(bCenter.clone().add(xc, yc, zc), Material.COAL_BLOCK);
                            break;
                        case 'I':
                            bCenter.clone().add(xc, yc, zc).getBlock().setType(Material.IRON_BLOCK);
                            break;
                        case 'R':
                            bCenter.clone().add(xc, yc, zc).getBlock().setType(Material.REDSTONE_BLOCK);
                            break;
                    }

                    xc++;
                }

                zc++;
                xc = -2;
            }

            yc++;
            xc = -2;
            zc = -2;
        }
    }

    public static void explodeRocket(Location r){
        for(int y=-2; y<2; y++) {
            for(int x=-15; x<15; x++){
                for(int z=-15; z<15; z++){
                    if(x % 3 == 0 && z % 3 == 0){
                        r.getWorld().createExplosion(r.clone().add(x, y, z), 5f);
                    }
                }
            }
        }
    }

    private static void sendBlockUpdates(Location l, Material t){
        for(Entity e : l.getWorld().getNearbyEntities(l, 100, 100, 100)){
            if(e instanceof Player){
                Player p = (Player)e;

                p.sendBlockChange(l, t.createBlockData());
            }
        }
    }
}
