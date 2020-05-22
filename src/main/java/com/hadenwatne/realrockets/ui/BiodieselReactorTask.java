package com.hadenwatne.realrockets.ui;

import com.hadenwatne.realrockets.utils.RandomUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BiodieselReactorTask extends BukkitRunnable {
    private BiodieselReactor reactor;
    private int seconds;
    private int maxSeconds;

    public BiodieselReactorTask(BiodieselReactor r){
        reactor = r;
        maxSeconds = 10;
        seconds = 0;
    }

    @Override
    public void run() {
        if(reactor.isActive()) {
            if (reactor.getGUI().contains(Material.SUGAR_CANE, 1) && reactor.getGUI().contains(Material.POTATO, 1) && reactor.getGUI().contains(Material.BONE_MEAL, 1)) {
                if (seconds < maxSeconds) {
                    seconds++;
                    reactor.playTick();
                } else {
                    // Remove one sugar cane
                    for (ItemStack item : reactor.getGUI().getContents()) {
                        if (item != null && item.getType() == Material.SUGAR_CANE) {
                            if (item.getAmount() > 1)
                                item.setAmount(item.getAmount() - 1);
                            else
                                reactor.getGUI().remove(item);

                            break;
                        }
                    }

                    // Remove one potato
                    for (ItemStack item : reactor.getGUI().getContents()) {
                        if (item != null && item.getType() == Material.POTATO) {
                            if (item.getAmount() > 1)
                                item.setAmount(item.getAmount() - 1);
                            else
                                reactor.getGUI().remove(item);

                            break;
                        }
                    }

                    // Remove one bone meal
                    for (ItemStack item : reactor.getGUI().getContents()) {
                        if (item != null && item.getType() == Material.BONE_MEAL) {
                            if (item.getAmount() > 1)
                                item.setAmount(item.getAmount() - 1);
                            else
                                reactor.getGUI().remove(item);

                            break;
                        }
                    }

                    if (reactor.getGUI().firstEmpty() > -1) {
                        reactor.getGUI().addItem(RocketBlocks.getBiofuel());
                        reactor.playFinish();
                    } else {
                        reactor.setReacting(false);
                        this.cancel();
                    }

                    seconds = 0;
                }
            } else {
                reactor.setReacting(false);
                this.cancel();
            }
        }else{
            // Likely removed.
            this.cancel();
        }
    }
}
