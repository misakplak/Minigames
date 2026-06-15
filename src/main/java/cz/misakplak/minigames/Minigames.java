package cz.misakplak.minigames;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
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

        getCommand("rebirth").setExecutor(new RebirthGuiCommand());
        getCommand("rebirthreset").setExecutor(new RebirthReset());

        getLogger().info(ChatColor.GOLD + "[Minigames] "
                + ChatColor.GREEN + "Plugin enabled!");
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
}