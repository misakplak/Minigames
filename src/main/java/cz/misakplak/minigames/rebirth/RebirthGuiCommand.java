package cz.misakplak.minigames.rebirth;

import cz.misakplak.minigames.Minigames;
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
            sender.sendMessage("§c§lUsage: /rebirth gui | /rebirth reset");
            return true;
        }

        RebirthGui gui = new RebirthGui();

        switch (args[0].toLowerCase()) {

            case "reset":

                int currentLevel = Minigames.getInstance().getRebirthLevel(player);

                if (currentLevel == 0) {
                    player.sendMessage("§c§lYour rebirth level is already 0");
                    return true;
                }

                if (player.hasPermission("minigames.rebirth.reset")) {
                    sender.sendMessage("§c§uYou Dont have permission to reset your rebirth level!");
                    return true;
                }

                Minigames.getInstance().setRebirthLevel(player, 0);

                player.sendMessage("§a§lYour rebirth level has been reset!");
                return true;

            case "gui":

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