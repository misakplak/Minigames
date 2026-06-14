package cz.misakplak.minigames;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minigames extends JavaPlugin {

    @Override
    public void onEnable() {


        getServer().getPluginManager().registerEvents(new NoCrafting(), this);
        getServer().getPluginManager().registerEvents(new RebirthGui(), this);
        getServer().getPluginManager().registerEvents(new RebirthConfirmGui(), this);
        getServer().getPluginManager().registerEvents(new RequirementsGui(), this);
        getServer().getPluginManager().registerEvents(new LevelGui(), this);

        getLogger().info(ChatColor.GOLD + "[Minigames] " + ChatColor.GREEN + "Plugin enabled!");

        getCommand("rebirth").setExecutor(new RebirthGuiCommand());



    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GOLD + "[Minigames] " + ChatColor.RED + "Plugin disabled!");

    }
}
