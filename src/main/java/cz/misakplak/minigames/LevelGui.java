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


public class LevelGui implements Listener {


    public Inventory getInventory(Player player) {

        Inventory LevelGUI = Bukkit.createInventory(null, 27, "§3§lLevel GUI");


        ItemStack empty = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        ItemStack level = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta levelMeta = level.getItemMeta();
        levelMeta.setDisplayName("§aYour Level is : " + +Minigames.getInstance().getRebirthLevel(player));
        level.setItemMeta(levelMeta);


        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§c§lBack");
        back.setItemMeta(backMeta);


        LevelGUI.setItem(0, empty);
        LevelGUI.setItem(1, empty);
        LevelGUI.setItem(2, empty);
        LevelGUI.setItem(3, empty);
        LevelGUI.setItem(4, empty);
        LevelGUI.setItem(5, empty);
        LevelGUI.setItem(6, empty);
        LevelGUI.setItem(7, empty);
        LevelGUI.setItem(8, empty);
        LevelGUI.setItem(9, empty);
        LevelGUI.setItem(10, back);
        LevelGUI.setItem(11, empty);
        LevelGUI.setItem(12, empty);
        LevelGUI.setItem(13, level);
        LevelGUI.setItem(14, empty);
        LevelGUI.setItem(15, empty);
        LevelGUI.setItem(16, back);
        LevelGUI.setItem(17, empty);
        LevelGUI.setItem(18, empty);
        LevelGUI.setItem(19, empty);
        LevelGUI.setItem(20, empty);
        LevelGUI.setItem(21, empty);
        LevelGUI.setItem(22, empty);
        LevelGUI.setItem(23, empty);
        LevelGUI.setItem(24, empty);
        LevelGUI.setItem(25, empty);
        LevelGUI.setItem(26, empty);

        return LevelGUI;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§3§lLevel GUI")) {
            return;
        }
        event.setCancelled(true);

        ItemStack clicked = event.getCurrentItem();

        if (clicked == null) {
            return;
        }

        if (clicked.getType() == Material.AIR) {
            return;
        }

        if (event.getClickedInventory() != event.getView().getTopInventory()) {
            return;
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
