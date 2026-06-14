package cz.misakplak.minigames;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RebirthConfirmGui implements Listener {

    public Inventory getInventory() {

        Inventory RebirthGUI = Bukkit.createInventory(null, 9, "§3§lRebirth Confirm GUI");

        ItemStack confirmButton = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirmMeta = confirmButton.getItemMeta();
        confirmMeta.setDisplayName("§a§lConfirm");
        confirmButton.setItemMeta(confirmMeta);

        ItemStack empty = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        ItemStack cancel = new ItemStack(Material.RED_WOOL);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName("§c§lcancel");
        cancel.setItemMeta(cancelMeta);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§c§lBack");
        back.setItemMeta(backMeta);


        RebirthGUI.setItem(0, cancel);
        RebirthGUI.setItem(1, cancel);
        RebirthGUI.setItem(2, cancel);
        RebirthGUI.setItem(3, empty);
        RebirthGUI.setItem(4, back);
        RebirthGUI.setItem(5, empty);
        RebirthGUI.setItem(6, confirmButton);
        RebirthGUI.setItem(7, confirmButton);
        RebirthGUI.setItem(8, confirmButton);

        return RebirthGUI;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§3§lRebirth Confirm GUI")) {
            return;
        }
        event.setCancelled(true);


        if (event.getClickedInventory() != event.getView().getTopInventory()) {
            return;
        }

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) {
            return;
        }

        if (clicked.getType() == Material.GREEN_WOOL) {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("§aRebirth Confirmed");
            player.closeInventory();
        }
        if (clicked.getType() == Material.RED_WOOL) {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("Rebirth Cancelled");
            player.closeInventory();
        }

        if (clicked.getType() == Material.BARRIER) {
            Player player = (Player) event.getWhoClicked();
            RebirthGui gui = new RebirthGui();
            player.openInventory(
                    gui.getInventory()
            );
        }
    }
}

