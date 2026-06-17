package cz.misakplak.minigames.rebirth;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class NoCrafting implements Listener {

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {

        if (event.getRecipe() == null) {
            return;
        }

        Material result = event.getRecipe().getResult().getType();

        if (result != Material.CRAFTING_TABLE
                && result != Material.WOODEN_AXE
                && result != Material.FURNACE
                && result != Material.BLAST_FURNACE
                && result != Material.STONE_AXE
                && result != Material.STICK
                && result != Material.OAK_PLANKS
                && result != Material.SPRUCE_PLANKS
                && result != Material.BIRCH_PLANKS
                && result != Material.DARK_OAK_PLANKS
                && result != Material.JUNGLE_PLANKS
                && result != Material.STONE_PICKAXE) {
            event.getInventory().setResult(new ItemStack(Material.AIR));
        }
    }
}
