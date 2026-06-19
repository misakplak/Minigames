package cz.misakplak.minigames.spleef;

import org.bukkit.entity.Player;

public class SpleefWinSystem {

    public void playerWon(Player player) {
        player.sendTitle("§a§lYou Won!", "", 5, 5, 5);
    }

    public void playerLost(Player player) {

    }
}