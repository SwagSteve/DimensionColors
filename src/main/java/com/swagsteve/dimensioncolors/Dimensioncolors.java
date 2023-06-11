package com.swagsteve.dimensioncolors;

import Listeners.WorldChange;
import Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.HashMap;

public final class Dimensioncolors extends JavaPlugin implements Listener {

    //Instance
    private static Dimensioncolors instance;
    public static Dimensioncolors getInstance(){
        return instance;
    }

    //Config variables
    public static String confNetherprefix, confEndprefix, confOverworldprefix;
    public static Boolean confEnabled;

    //Reload cooldown
    public static HashMap<Player,Long> cooldown;

    @Override
    public void onEnable() {

        //Instance
        instance = this;

        //Cooldown
        cooldown = new HashMap<>();

        //Config
        File tempConfig = new File(getDataFolder(), "config.yml");
        if (!tempConfig.exists()) {
            getLogger().info("Generating config...");
            saveDefaultConfig();
            reloadConfig();
        }

        // Cache config
        confNetherprefix = getConfig().getString("Options.nether-prefix");
        confEndprefix = getConfig().getString("Options.end-prefix");
        confOverworldprefix = getConfig().getString("Options.overworld-prefix");
        confEnabled = getConfig().getBoolean("Options.enabled");

        //Commands
        this.getCommand("dcreload").setExecutor(new ReloadCommand());

        //Listeners
        this.getServer().getPluginManager().registerEvents(new WorldChange(), this);
    }

    public static void runCheck(Player p) {
        if (Dimensioncolors.confEnabled) {
            if (p.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                p.setPlayerListName(Utils.Color(Dimensioncolors.confNetherprefix + p.getName()));
            } else if (p.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                p.setPlayerListName(Utils.Color(Dimensioncolors.confEndprefix + p.getName()));
            } else {
                p.setPlayerListName(Utils.Color(Dimensioncolors.confOverworldprefix + p.getName()));
            }
        }
    }

    @Override
    public void onDisable() {

        //Disable Message
        getLogger().info("Disabling!");

        //Save Config
        saveConfig();
    }
}