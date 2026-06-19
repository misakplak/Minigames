package cz.misakplak.minigames.spleef;

import cz.misakplak.minigames.Minigames;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;

public class SpleefArenaResettingSystem {

    public void startResetTimer() {

        new BukkitRunnable() {

            int seconds = 10;

            @Override
            public void run() {

                if (seconds <= 0) {

                    for (BlockState state : Minigames.getInstance().getArenaBlocks().values()) {
                        state.update(true, false);
                    }

                    Bukkit.broadcastMessage("§a§lArena has been reset!");

                    cancel(); // Stop this timer
                    return;
                }

                Bukkit.broadcastMessage("§eArena resetting in " + seconds + "...");
                seconds--;
            }

        }.runTaskTimer(Minigames.getInstance(), 0L, 20L);
    }
}