package cz.misakplak.minigames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RebirthGuiCommand implements CommandExecutor {

    //----------------------------------guicommand------------------------------------------

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!sender.hasPermission("misakplak.rebirth")) {
            sender.sendMessage(ChatColor.RED + "§c§lYou don't have permission!");
            return true;
        }

        RebirthGui gui = new RebirthGui();

        Player player = (Player) sender;
        player.openInventory(
                gui.getInventory()
        );


       return true;
    }
}