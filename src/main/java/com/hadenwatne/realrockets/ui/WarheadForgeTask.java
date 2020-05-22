package com.hadenwatne.realrockets.ui;

import com.hadenwatne.realrockets.utils.RandomUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class WarheadForgeTask extends BukkitRunnable {
    private WarheadForge forge;
    private int seconds;
    private int maxSeconds;

    public WarheadForgeTask(WarheadForge r){
        forge = r;
        maxSeconds = 30;
        seconds = 0;
    }

    @Override
    public void run() {
        if(forge.isActive()) {
            ItemStack item0 = forge.getGUI().getItem(0);
            if (item0 != null && item0.getAmount() >= 9 && (item0.isSimilar(RocketBlocks.getUnstableChunk()) || item0.isSimilar(RocketBlocks.getImpureChunk()) || item0.isSimilar(RocketBlocks.getPurifiedChunk()))) {
                if (seconds < maxSeconds) {
                    seconds++;
                    forge.playTick();
                } else {
                    // Remove 9 chunks
                    if(item0.getAmount() > 9) {
                        item0.setAmount(item0.getAmount() - 9);
                    } else {
                        forge.getGUI().remove(item0);
                    }

                    if (forge.getGUI().firstEmpty() > -1) {
                        if(item0.isSimilar(RocketBlocks.getUnstableChunk()))
                            forge.getGUI().setItem(8, RocketBlocks.getUnstableWarhead());

                        if(item0.isSimilar(RocketBlocks.getImpureChunk()))
                            forge.getGUI().setItem(8, RocketBlocks.getImpureWarhead());

                        if(item0.isSimilar(RocketBlocks.getPurifiedChunk()))
                            forge.getGUI().setItem(8, RocketBlocks.getPurifiedWarhead());

                        forge.playFinish();
                    } else {
                        forge.setBuilding(false);
                        this.cancel();
                    }

                    seconds = 0;
                }
            } else {
                forge.setBuilding(false);
                this.cancel();
            }
        }else{
            // Likely removed.
            this.cancel();
        }
    }
}
