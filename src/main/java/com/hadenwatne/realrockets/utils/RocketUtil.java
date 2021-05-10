package com.hadenwatne.realrockets.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Location> buildRocket(Location bCenter){
        int xc = -2;
        int yc = 1;
        int zc = -2;

        List<Location> rocketBlocks = new ArrayList<>();

        for(String layer : rocketPattern){
            for(String row : layer.split("/")){
                for(char b : row.toCharArray()){
                    Location bc = bCenter.clone().add(xc, yc, zc);

                    switch(b){
                        case 'A':
                            break;
                        case 'C':
                            bc.getBlock().setType(Material.COAL_BLOCK);
                            rocketBlocks.add(bc);
                            //sendBlockUpdates(bCenter.clone().add(xc, yc, zc), Material.COAL_BLOCK);
                            break;
                        case 'I':
                            bc.getBlock().setType(Material.IRON_BLOCK);
                            rocketBlocks.add(bc);
                            break;
                        case 'R':
                            bc.getBlock().setType(Material.REDSTONE_BLOCK);
                            rocketBlocks.add(bc);
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

        return rocketBlocks;
    }

    public static void explodeRocket(Location r, int type){
        switch(type){
            case 0: // Impure
                for(int y=-2; y<2; y++) {
                    for(int x=-5; x<5; x++){
                        for(int z=-5; z<5; z++){
                            if(x % 4 == 0 && z % 4 == 0){
                                r.getWorld().createExplosion(r.clone().add(x, y, z), 5f);
                            }
                        }
                    }
                }

                break;
            case 1: // Unstable
                int multiplier = RandomUtil.getInt(10)+1;

                for(int y=-2; y<2; y++) {
                    for(int x=-multiplier; x<multiplier; x++){
                        for(int z=-multiplier; z<multiplier; z++){
                            if(x % 4 == 0 && z % 4 == 0){
                                r.getWorld().createExplosion(r.clone().add(x, y, z), 5f);
                            }
                        }
                    }
                }

                break;
            case 2: // Purified
                for(int y=-3; y<3; y++) {
                    for(int x=-15; x<15; x++){
                        for(int z=-15; z<15; z++){
                            if(x % 4 == 0 && z % 4 == 0){
                                r.getWorld().createExplosion(r.clone().add(x, y, z), 5f);
                            }
                        }
                    }

                    // Explode a smaller layer at the bottom
                    if(y == -3){
                        for(int x=-8; x<8; x++){
                            for(int z=-8; z<8; z++){
                                if(x % 4 == 0 && z % 4 == 0){
                                    r.getWorld().createExplosion(r.clone().add(x, y, z), 5f);
                                }
                            }
                        }
                    }
                }

                break;
            case 3: // F.L.E.I.J.A.
                for(int y=-5; y<5; y++) {
                    for(int x=-25; x<25; x++){
                        for(int z=-25; z<25; z++){
                            if(x % 5 == 0 && z % 5 == 0){
                                r.getWorld().createExplosion(r.clone().add(x, y, z), 6f);
                            }
                        }
                    }

                    // Explode a smaller layer at the bottom
                    if(y == -5){
                        for(int x=-10; x<10; x++) {
                            for (int z = -10; z < 10; z++) {
                                if (x % 5 == 0 && z % 5 == 0) {
                                    r.getWorld().createExplosion(r.clone().add(x, y, z), 6f);
                                }
                            }
                        }
                    }
                }

                break;
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
