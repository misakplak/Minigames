package cz.misakplak.minigames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RebirthGuiCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Players only!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUsage: /rebirth | /rebirth rebirthreset");
            return true;
        }

        RebirthGui gui = new RebirthGui();

        switch (args[0].toLowerCase()) {

            case "rebirthreset":

                int currentLevel = Minigames.getInstance().getRebirthLevel(player);

                if (currentLevel == 0) {
                    player.sendMessage("§c§lYour rebirth level is already 0");
                    return true;
                }

                Minigames.getInstance().setRebirthLevel(player, 0);

                player.sendMessage("§a§lYour rebirth level has been reset!");
                return true;

            case "rebirth":

                if (!player.hasPermission("misakplak.rebirth")) {
                    player.sendMessage("§c§lYou don't have permission!");
                    return true;
                }

                player.openInventory(gui.getInventory());
                return true;

            default:
                player.sendMessage("§cUnknown subcommand.");
                return true;
        }
    }
}