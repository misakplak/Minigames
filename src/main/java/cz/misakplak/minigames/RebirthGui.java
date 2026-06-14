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

public class RebirthGui implements Listener {

    public Inventory getInventory() {

        Inventory gui = Bukkit.createInventory(null, 27, "§3§lRebirth GUI");

        ItemStack rebirthButton = new ItemStack(Material.BARRIER);
        ItemMeta rebirthMeta = rebirthButton.getItemMeta();
        rebirthMeta.setDisplayName("§c§lRebirth");
        rebirthButton.setItemMeta(rebirthMeta);

        ItemStack empty = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        ItemStack level = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta levelMeta = level.getItemMeta();
        levelMeta.setDisplayName("§aLevel");
        level.setItemMeta(levelMeta);

        ItemStack requirements = new ItemStack(Material.BOOK);
        ItemMeta reqMeta = requirements.getItemMeta();
        reqMeta.setDisplayName("§eRequirements");
        requirements.setItemMeta(reqMeta);


        gui.setItem(0, empty);
        gui.setItem(1, empty);
        gui.setItem(2, empty);
        gui.setItem(3, empty);
        gui.setItem(4, empty);
        gui.setItem(5, empty);
        gui.setItem(6, empty);
        gui.setItem(7, empty);
        gui.setItem(8, empty);
        gui.setItem(9, empty);
        gui.setItem(10, empty);
        gui.setItem(11, level);
        gui.setItem(12, empty);
        gui.setItem(13, rebirthButton);
        gui.setItem(14, empty);
        gui.setItem(15, requirements);
        gui.setItem(16, empty);
        gui.setItem(17, empty);
        gui.setItem(18, empty);
        gui.setItem(19, empty);
        gui.setItem(20, empty);
        gui.setItem(21, empty);
        gui.setItem(22, empty);
        gui.setItem(23, empty);
        gui.setItem(24, empty);
        gui.setItem(25, empty);
        gui.setItem(26, empty);


        return gui;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§3§lRebirth GUI")) {
            return;
        }
        event.setCancelled(true);

        ItemStack clicked = event.getCurrentItem();

        if (clicked == null) {
            return;
        }

        if (event.getClickedInventory() != event.getView().getTopInventory()) {
            return;
        }

        RebirthConfirmGui RebirthGUI = new RebirthConfirmGui();

        if (clicked.getType() == Material.BARRIER) {
            Player player = (Player) event.getWhoClicked();
            player.openInventory(
                    RebirthGUI.getInventory()
            );

        }

        RequirementsGui RequirementsGUI = new RequirementsGui();

        if (clicked.getType() == Material.BOOK) {
            Player player = (Player) event.getWhoClicked();
            player.openInventory(
                    RequirementsGUI.getInventory()
            );
        }

        LevelGui LevelGUI = new LevelGui();
        if (clicked.getType() == Material.EXPERIENCE_BOTTLE) {
            Player player = (Player) event.getWhoClicked();
            player.openInventory(
                    LevelGUI.getInventory()
            );

        }
    }
}
