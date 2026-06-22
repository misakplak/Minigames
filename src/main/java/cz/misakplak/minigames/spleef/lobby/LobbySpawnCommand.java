package cz.misakplak.minigames.spleef.lobby;

import cz.misakplak.minigames.Minigames;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbySpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        Minigames.getInstance().setSpawnLocation(player.getLocation());
        {
            player.sendMessage("location set!");
        }
        return true;
    }
}
