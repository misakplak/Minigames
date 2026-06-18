package cz.misakplak.minigames;

import cz.misakplak.minigames.rebirth.*;
import cz.misakplak.minigames.rebirth.TabComplete;
import cz.misakplak.minigames.spleef.SaveSpleefCommands;
import cz.misakplak.minigames.spleef.SpleefCommand;
import cz.misakplak.minigames.spleef.SpleefGui;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class Minigames extends JavaPlugin {

    private static Minigames instance;

    private File rebirthsFile;
    private FileConfiguration rebirthsConfig;

    private Location SpleefLocation;

    private Location pos1;
    private Location pos2;

    private HashMap<Location, BlockState> arenaBlocks = new HashMap<>();

    public HashMap<Location, BlockState> getArenaBlocks() {
        return arenaBlocks;
    }

    public void setSpleefLocation(Location SpleefLocation) {
        this.SpleefLocation = SpleefLocation;
    }

    public Location getSpleefLocation() {
        return SpleefLocation;
    }

    public static Minigames getInstance() {
        return instance;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public Location getPos1() {
        return pos1;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public Location getPos2() {
        return pos2;
    }


    @Override
    public void onEnable() {

        instance = this;
        saveDefaultConfig();
        loadRebirthsFile();

        getServer().getPluginManager().registerEvents(new NoCrafting(), this);
        getServer().getPluginManager().registerEvents(new RebirthGui(), this);
        getServer().getPluginManager().registerEvents(new RebirthConfirmGui(), this);
        getServer().getPluginManager().registerEvents(new RequirementsGui(), this);
        getServer().getPluginManager().registerEvents(new LevelGui(), this);
        getServer().getPluginManager().registerEvents(new SpleefGui(), this);


        RebirthGuiCommand commands = new RebirthGuiCommand();
        {
            getCommand("rebirth").setExecutor(commands);
            getCommand("reset").setExecutor(commands);
            getCommand("rebirth").setTabCompleter(new TabComplete());
        }
        SpleefCommand spleefCommands = new SpleefCommand();
        {
            getCommand("spleefarena").setExecutor(spleefCommands);

            SaveSpleefCommands spleefcommands1 = new SaveSpleefCommands();
            getCommand("spleef").setExecutor(spleefcommands1);

        }
        getLogger().info("Plugin enabled!");
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