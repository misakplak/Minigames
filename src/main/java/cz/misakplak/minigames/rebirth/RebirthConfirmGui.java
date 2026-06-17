package cz.misakplak.minigames.rebirth;

import cz.misakplak.minigames.Minigames;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

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

            int rebirthLevel = Minigames.getInstance()
                    .getRebirthsConfig()
                    .getInt("players." + player.getUniqueId(), 0);

            int level = Minigames.getInstance().getRebirthLevel(player);

            if (level == 0) {
                if (!Minigames.getInstance().checkRequirementsRebirthLevel1(player, 0)) {
                    player.sendMessage("§c§l§oYou dont have all requirements to rebirth!");
                    return;
                }

                rebirth1Player(player);
                player.closeInventory();
            }

            if (level == 1) {
                if (!Minigames.getInstance().checkRequirementsRebirthLevel2(player, 1)) {
                    player.sendMessage("§c§l§oYou dont have all requirements to rebirth!");
                    return;
                }

                rebirth2Player(player);
                player.closeInventory();
            }

            if (level == 2) {
                player.sendMessage("§c§lThere are no more rebirth levels.");
                player.closeInventory();
            }


        }


        if (clicked.getType() == Material.RED_WOOL) {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("§c§o§lRebirth Cancelled");
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
    public void rebirth1Player(Player player) {

        player.getInventory().clear();
        player.getInventory().setHelmet(ItemStack.of(Material.IRON_HELMET));
        player.getInventory().setChestplate(ItemStack.of(Material.IRON_CHESTPLATE));
        player.getInventory().setLeggings(ItemStack.of(Material.IRON_LEGGINGS));
        player.getInventory().setBoots(ItemStack.of(Material.IRON_BOOTS));
        player.getInventory().setItem(5, new ItemStack(Material.DIAMOND_AXE));
        player.getInventory().setItem(6, new ItemStack(Material.GOLDEN_APPLE, 3));
        player.setLevel(35);
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 2, 2);
        player.sendMessage("§a§lRebirth Complete!");
        Minigames.getInstance().addRebirthLevel(player);
        player.closeInventory();

    }

    public void rebirth2Player(Player player) {

        player.getInventory().clear();
        player.getInventory().setHelmet(ItemStack.of(Material.AIR));
        player.getInventory().setChestplate(ItemStack.of(Material.AIR));
        player.getInventory().setLeggings(ItemStack.of(Material.AIR));
        player.getInventory().setBoots(ItemStack.of(Material.AIR));
        player.getInventory().setHelmet(ItemStack.of(Material.DIAMOND_HELMET));
        player.getInventory().setChestplate(ItemStack.of(Material.DIAMOND_CHESTPLATE));
        player.getInventory().setLeggings(ItemStack.of(Material.DIAMOND_LEGGINGS));
        player.getInventory().setBoots(ItemStack.of(Material.DIAMOND_BOOTS));
        player.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
        player.getInventory().setItem(1, new ItemStack(Material.GOLDEN_APPLE, 10));
        player.getInventory().setItem(2, new ItemStack(Material.ENCHANTING_TABLE));
        player.getInventory().setItem(20, new ItemStack(Material.LAPIS_LAZULI, 64));
        player.setLevel(50);
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 2, 2);
        player.sendMessage("§a§lRebirth Complete!");
        Minigames.getInstance().addRebirthLevel(player);
        player.closeInventory();

    }
    private boolean checkRequirements(Player player, Map<Material, Integer> requirements) {
        for (Map.Entry<Material, Integer> entry : requirements.entrySet()) {
            int count = player.getInventory().all(entry.getKey())
                    .values().stream().mapToInt(ItemStack::getAmount).sum();
            if (count < entry.getValue()) {
                player.sendMessage("§cNeed " + entry.getValue() + " " + entry.getKey().name());
                player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BREAK, 1, 1);
                player.closeInventory();
                return false;
            }
        }
        return true;
    }
}

