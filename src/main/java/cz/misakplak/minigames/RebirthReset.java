package cz.misakplak.minigames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RebirthReset implements CommandExecutor {

    //----------------------------------guicommand------------------------------------------

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!sender.hasPermission("misakplak.rebirth")) {
            sender.sendMessage(ChatColor.RED + "§c§lYou don't have permission!");
            return true;
        }

        RebirthGui gui = new RebirthGui();

        Player player = (Player) sender;
        int currentLevel =
                Minigames.getInstance().getRebirthLevel(player);

        if (currentLevel == 0) {
            player.sendMessage("§c§lYour rebirth level is already 0");
            return true;
        }

        Minigames.getInstance().setRebirthLevel(player, 0);

        player.sendMessage("§a§lYour rebirth level has been reset!");
        return true;
    }
}