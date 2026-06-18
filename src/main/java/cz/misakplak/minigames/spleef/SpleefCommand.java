package cz.misakplak.minigames.spleef;

import cz.misakplak.minigames.Minigames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpleefCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        SpleefGui gui = new  SpleefGui();


        if (!(sender instanceof Player player)) {
            sender.sendMessage("Players only!");
            return true;
        }

        if (!player.hasPermission("minigames.spleef")) {
            sender.sendMessage("You do not have permission!");
            return true;
        }

        if (args.length == 0) {
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "settp":
                Minigames.getInstance().setSpleefLocation(player.getLocation());
                player.sendMessage( "§a§l§oSpleef location has been set to your location!");
                return true;

            case "gui":
                player.openInventory(new SpleefGui().getInventory(player));
            return true;

            default:
                player.sendMessage("§cUnknown subcommand.");
                return true;


        }
    }
}


