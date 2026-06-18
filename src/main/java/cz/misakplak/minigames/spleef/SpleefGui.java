package cz.misakplak.minigames.spleef;

import cz.misakplak.minigames.Minigames;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;



public class SpleefGui implements Listener {
    private final Set<UUID> frozenPlayers = new HashSet<>();
    public Inventory getInventory(Player player) {



        Inventory SpleefGUI = Bukkit.createInventory(null, 27, "§3§lSpleef");

        ItemStack spleef = new ItemStack(Material.NETHERITE_SHOVEL);
        ItemMeta spleefMeta = spleef.getItemMeta();
        spleefMeta.setDisplayName("§3§l§ospleef");

        spleef.setItemMeta(spleefMeta);


        ItemStack empty = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);


        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§c§lBack");
        back.setItemMeta(backMeta);


        SpleefGUI.setItem(0, empty);
        SpleefGUI.setItem(1, empty);
        SpleefGUI.setItem(2, empty);
        SpleefGUI.setItem(3, empty);
        SpleefGUI.setItem(4, empty);
        SpleefGUI.setItem(5, empty);
        SpleefGUI.setItem(6, empty);
        SpleefGUI.setItem(7, empty);
        SpleefGUI.setItem(8, empty);
        SpleefGUI.setItem(9, empty);
        SpleefGUI.setItem(10, empty);
        SpleefGUI.setItem(11, empty);
        SpleefGUI.setItem(12, empty);
        SpleefGUI.setItem(13, spleef);
        SpleefGUI.setItem(14, empty);
        SpleefGUI.setItem(15, empty);
        SpleefGUI.setItem(16, empty);
        SpleefGUI.setItem(17, empty);
        SpleefGUI.setItem(18, empty);
        SpleefGUI.setItem(19, empty);
        SpleefGUI.setItem(20, empty);
        SpleefGUI.setItem(21, empty);
        SpleefGUI.setItem(22, empty);
        SpleefGUI.setItem(23, empty);
        SpleefGUI.setItem(24, empty);
        SpleefGUI.setItem(25, empty);
        SpleefGUI.setItem(26, empty);

        return SpleefGUI;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (frozenPlayers.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§3§lSpleef")) {
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


            Location spleefLocation = Minigames.getInstance().getSpleefLocation();

            if (spleefLocation == null) {
                player.sendMessage("§cNo location set");
                return;
            }


            if (clicked.getType() == Material.NETHERITE_SHOVEL) {
                player.getInventory().clear();
                player.getInventory().setItem(4, new ItemStack(Material.NETHERITE_SHOVEL));
                player.teleport(Minigames.getInstance().getSpleefLocation());
                player.sendMessage("§a§lYou have been teleported to Spleef!");
                frozenPlayers.add(player.getUniqueId());

                new BukkitRunnable() {


                    int seconds = 10;

                    @Override
                    public void run() {


                        if (seconds <= 0) {
                            player.sendActionBar("§aGO!");
                            frozenPlayers.remove(player.getUniqueId());
                            cancel();
                            return;
                        }


                        player.sendActionBar("§eStarting in " + seconds);
                        seconds--;
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);

                    }

                }.runTaskTimer(Minigames.getInstance(), 0L, 20L);

            }
        }
    }
}

