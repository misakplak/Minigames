package cz.misakplak.minigames.rebirth;

import cz.misakplak.minigames.Minigames;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class RequirementsGui implements Listener {

    public Inventory getInventory(Player player) {

        int getRebirthLevel = Minigames.getInstance().getRebirthLevel(player);

        Inventory RequirementsGUI = Bukkit.createInventory(null, 27, "§3§lRequirements GUI");

        ItemStack requirements = new ItemStack(Material.PALE_OAK_SIGN);
        ItemMeta requirementsMeta = requirements.getItemMeta();
        requirementsMeta.setDisplayName("§c§lRequirements:");

        if (getRebirthLevel == 0) {
            requirementsMeta.setLore(Arrays.asList(
                    "§7Requirements:",
                    "§f32 Oak Logs",
                    "§f64 Cobblestone",
                    "§f4 Iron Ingots"
            ));
        } else if (getRebirthLevel == 1) {
            requirementsMeta.setLore(Arrays.asList(
                    "§7Requirements:",
                    "§f1 Ender Pearl",
                    "§f5 Netherracks",
                    "§f1 Coal Block"
            ));
        } else if (getRebirthLevel == 2) {
            requirementsMeta.setLore(Arrays.asList(
                    "§cThere are No more Rebirth Levels."
            ));
        }

        requirements.setItemMeta(requirementsMeta);


        ItemStack empty = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        ItemStack level = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta levelMeta = level.getItemMeta();
        levelMeta.setDisplayName("§aLevel");
        level.setItemMeta(levelMeta);

        ItemStack info = new ItemStack(Material.BOOK);
        ItemMeta infoMeta = info.getItemMeta();
        infoMeta.setDisplayName("§c§lKEEP IN MIND YOU WILL LOSE YOUR PROGRESS");
        if (getRebirthLevel == 0) {
            infoMeta.setLore(Arrays.asList(
                    "§7Rewards:",
                    "§f1 x Iron Axe",
                    "§f3 x Golden Apple",
                    "§f1 x Full iron set",
                    "§f35 XP levels"
            ));
        } else if (getRebirthLevel == 1) {
            infoMeta.setLore(Arrays.asList(
                    "§7Rewards:",
                    "§f10 x Golden Apple",
                    "§f1 x Enchanting Table",
                    "§f64 x Lapis Lazuli",
                    "§f1x Full diamond set",
                    "§f50 XP levels"
            ));
        } else if (getRebirthLevel == 2) {
            infoMeta.setLore(Arrays.asList(
                    "§cThere are No more Rebirth Levels."
            ));
        }
        info.setItemMeta(infoMeta);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§c§lBack");
        back.setItemMeta(backMeta);



        RequirementsGUI.setItem(0, empty);
        RequirementsGUI.setItem(1, empty);
        RequirementsGUI.setItem(2, empty);
        RequirementsGUI.setItem(3, empty);
        RequirementsGUI.setItem(4, empty);
        RequirementsGUI.setItem(5, empty);
        RequirementsGUI.setItem(6, empty);
        RequirementsGUI.setItem(7, empty);
        RequirementsGUI.setItem(8, empty);
        RequirementsGUI.setItem(9, empty);
        RequirementsGUI.setItem(10, level);
        RequirementsGUI.setItem(11, empty);
        RequirementsGUI.setItem(12, back);
        RequirementsGUI.setItem(13, requirements);
        RequirementsGUI.setItem(14, back);
        RequirementsGUI.setItem(15, empty);
        RequirementsGUI.setItem(16, info);
        RequirementsGUI.setItem(17, empty);
        RequirementsGUI.setItem(18, empty);
        RequirementsGUI.setItem(19, empty);
        RequirementsGUI.setItem(20, empty);
        RequirementsGUI.setItem(21, empty);
        RequirementsGUI.setItem(22, empty);
        RequirementsGUI.setItem(23, empty);
        RequirementsGUI.setItem(24, empty);
        RequirementsGUI.setItem(25, empty);
        RequirementsGUI.setItem(26, empty);

        return RequirementsGUI;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§3§lRequirements GUI")) {
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        {


            if (event.getClickedInventory() != event.getView().getTopInventory()) {
                return;
            }

            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || clicked.getType() == Material.AIR) {
                return;
            }

            if (clicked.getType() == Material.BARRIER) {
                RebirthGui gui = new RebirthGui();
                player.openInventory(
                        gui.getInventory()
                );
            }

            LevelGui gui = new LevelGui();
            if (clicked.getType() == Material.EXPERIENCE_BOTTLE) {
                player.openInventory(
                        gui.getInventory(player)
                );

            }
        }

    }
}
