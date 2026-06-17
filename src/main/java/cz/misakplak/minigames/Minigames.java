package cz.misakplak.minigames;

import cz.misakplak.plugin.TabComplete;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Minigames extends JavaPlugin {

    private static Minigames instance;

    private File rebirthsFile;
    private FileConfiguration rebirthsConfig;

    public static Minigames getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {

        instance = this;

        loadRebirthsFile();

        getServer().getPluginManager().registerEvents(new NoCrafting(), this);
        getServer().getPluginManager().registerEvents(new RebirthGui(), this);
        getServer().getPluginManager().registerEvents(new RebirthConfirmGui(), this);
        getServer().getPluginManager().registerEvents(new RequirementsGui(), this);
        getServer().getPluginManager().registerEvents(new LevelGui(), this);



        RebirthGuiCommand commands = new RebirthGuiCommand();
        {
            getCommand("rebirth").setExecutor(commands);
            getCommand("reset").setExecutor(commands);
            getCommand("rebirth").setTabCompleter(new TabComplete());
        }
        getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {

        saveRebirths();

        getLogger().info(ChatColor.GOLD + "[Minigames] "
                + ChatColor.RED + "Plugin disabled!");
    }

    // ---------------- REBIRTH FILE ----------------

    private void loadRebirthsFile() {

        rebirthsFile = new File(getDataFolder(), "rebirths.yml");

        if (!rebirthsFile.exists()) {

            rebirthsFile.getParentFile().mkdirs();

            try {
                rebirthsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        rebirthsConfig = YamlConfiguration.loadConfiguration(rebirthsFile);
    }

    public FileConfiguration getRebirthsConfig() {
        return rebirthsConfig;
    }

    public void saveRebirths() {

        try {
            rebirthsConfig.save(rebirthsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---------------- REBIRTH LEVELS ----------------

    public int getRebirthLevel(Player player) {

        return rebirthsConfig.getInt(
                "players." + player.getUniqueId(),
                0
        );
    }

    public void setRebirthLevel(Player player, int level) {

        rebirthsConfig.set(
                "players." + player.getUniqueId(),
                level
        );

        saveRebirths();
    }

    public void addRebirthLevel(Player player) {

        int currentLevel = getRebirthLevel(player);

        setRebirthLevel(
                player,
                currentLevel + 1
        );
    }

    public boolean checkRequirementsRebirthLevel1(Player player, int rebirthLevel) {

        switch (rebirthLevel) {

            case 0:
                   return player.getInventory().contains(Material.OAK_LOG, 11);

                case 1:
                    return player.getInventory().contains(Material.COBBLESTONE, 38);

                    case 2:
                        return player.getInventory().contains(Material.IRON_INGOT, 6);

                        default:
                            return false;
        }
    }


    public boolean checkRequirementsRebirthLevel2(Player player, int rebirthLevel) {

        switch (rebirthLevel) {

            case 0:
                return player.getInventory().contains(Material.ENDER_PEARL, 1);

            case 1:
                return player.getInventory().contains(Material.NETHERRACK, 5);

            case 2:
                return player.getInventory().contains(Material.COAL_BLOCK, 1);

            default:
                return false;
        }
    }
}