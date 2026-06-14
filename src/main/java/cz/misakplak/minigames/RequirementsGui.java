package cz.misakplak.minigames;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class RequirementsGui implements Listener {

    public Inventory getInventory() {

        Inventory RequirementsGUI = Bukkit.createInventory(null, 27, "§3§lRequirements GUI");

        ItemStack requirements = new ItemStack(Material.PALE_OAK_SIGN);
        ItemMeta requirementsMeta = requirements.getItemMeta();
        requirementsMeta.setDisplayName("§c§lRequirements:");
        requirementsMeta.setLore(Arrays.asList(

                "§7Requirements:",
                "§f64 Oak Logs",
                "§f32 Cobblestone",
                "§f16 Iron Ingots"
        ));
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
        infoMeta.setLore(Collections.singletonList("§7You will get 35 XP and full iron armor, and 6 diamonds"));
        info.setItemMeta(infoMeta);



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
        RequirementsGUI.setItem(12, empty);
        RequirementsGUI.setItem(13, requirements);
        RequirementsGUI.setItem(14, empty);
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


        if (event.getClickedInventory() != event.getView().getTopInventory()) {
            return;
        }

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) {
            return;
        }

    }
}
