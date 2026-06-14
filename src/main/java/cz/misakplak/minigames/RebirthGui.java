package cz.misakplak.minigames;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RebirthGuiListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Inventory gui = Bukkit.createInventory(null, 9, "Rebirth GUI");
        

    }
}
