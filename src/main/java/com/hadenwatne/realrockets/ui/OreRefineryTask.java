package com.hadenwatne.realrockets.ui;

import com.hadenwatne.realrockets.utils.RandomUtil;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class OreRefineryTask extends BukkitRunnable {
    private OreRefinery refinery;
    private int seconds;
    private int maxSeconds;

    public OreRefineryTask(OreRefinery r){
        refinery = r;
        maxSeconds = 10;
        seconds = 0;
    }

    @Override
    public void run() {
        if(refinery.isActive()) {
            if (refinery.getGUI().containsAtLeast(RocketBlocks.getEnrichedOre(), 1) && refinery.getGUI().containsAtLeast(RocketBlocks.getBiofuel(), 1)) {
                if (seconds < maxSeconds) {
                    seconds++;
                    refinery.playTick();
                } else {
                    // Remove one ore
                    for (ItemStack item : refinery.getGUI().getContents()) {
                        if (item != null && item.isSimilar(RocketBlocks.getEnrichedOre())) {
                            if (item.getAmount() > 1)
                                item.setAmount(item.getAmount() - 1);
                            else
                                refinery.getGUI().remove(item);

                            break;
                        }
                    }

                    // Remove one fuel
                    for (ItemStack item : refinery.getGUI().getContents()) {
                        if (item != null && item.isSimilar(RocketBlocks.getBiofuel())) {
                            if (item.getAmount() > 1)
                                item.setAmount(item.getAmount() - 1);
                            else
                                refinery.getGUI().remove(item);

                            break;
                        }
                    }

                    int cap = refinery.getPlugin().getRocketMan().getEnableFleija() ? 7 : 6;
                    ItemStack toAdd = null;

                    switch (RandomUtil.getInt(cap)) {
                        case 0:
                        case 1:
                            toAdd = RocketBlocks.getUnstableChunk();
                            break;
                        case 2:
                        case 3:
                        case 4:
                            toAdd = RocketBlocks.getImpureChunk();
                            break;
                        case 5:
                            toAdd = RocketBlocks.getPurifiedChunk();
                            break;
                        case 6:
                            toAdd = RocketBlocks.getFleijaChunk();
                            break;
                    }

                    if(refinery.getGUI().addItem(toAdd).size() == 0){
                        refinery.playFinish();
                    }else{
                        refinery.setRefining(false);
                        this.cancel();
                    }

                    seconds = 0;
                }
            } else {
                refinery.setRefining(false);
                this.cancel();
            }
        }else{
            // Likely removed.
            this.cancel();
        }
    }
}
