package cz.misakplak.minigames.spleef;

import cz.misakplak.minigames.Minigames;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SaveSpleefCommands implements CommandExecutor {

    //----------------------------------Save1------------------------------------------

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        switch (args[0]) {
            case "save1":
            if (!sender.hasPermission("misakplak.savearena")) {
                sender.sendMessage("§c§lYou don't have permission!");
                return true;
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command!");
                return true;
            }

            Minigames.getInstance().setPos1(player.getLocation());
            player.sendMessage("§a§lArena position 1 saved!");
            sender.sendMessage("§3§lMake sure position 2 is saved too, than run /spleef savearena");

            case "save2":

                if (!sender.hasPermission("misakplak.savearena")) {
                    sender.sendMessage("§c§lYou don't have permission!");
                    return true;
                }

                if (!(sender instanceof Player)) {
                    sender.sendMessage("Only players can use this command!");
                    return true;
                }

                Minigames.getInstance().setPos2(player.getLocation());
                player.sendMessage("§a§lArena position 2 saved!");
                sender.sendMessage("§3§lMake sure position 2 is saved too, than run /spleef savearena");


            case "savearena":

                Location pos1 = Minigames.getInstance().getPos1();
                Location pos2 = Minigames.getInstance().getPos2();

                if (pos1 == null || pos2 == null) {
                    sender.sendMessage("§cSet arena!");
                    return true;
                }

                int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
                int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());

                int minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
                int maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());

                int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
                int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());


                Minigames.getInstance().getArenaBlocks().clear();



                for (int x = minX; x <= maxX; x++) {
                    for (int y = minY; y <= maxY; y++) {
                        for (int z = minZ; z <= maxZ; z++) {

                            Block block = pos1.getWorld().getBlockAt(x, y, z);



                            Minigames.getInstance().getArenaBlocks().put(
                                    block.getLocation(),
                                    block.getState()
                            );


                        }
                    }
                }

                new BukkitRunnable() {


                    int seconds = 100;

                    @Override
                    public void run() {


                        if (seconds <= 0) {
                            for (BlockState state : Minigames.getInstance().getArenaBlocks().values()) {
                                state.update(true, false);
                            }
                            sender.sendMessage("§a§lArena has been reset!");
                        }

                    }

                }.runTaskTimer(Minigames.getInstance(), 0L, 20L);



                sender.sendMessage("§a§lArena has been saved!");
                return true;


            default:
                player.sendMessage("§cUnknown subcommand.");
                return true;



        }
    }
}