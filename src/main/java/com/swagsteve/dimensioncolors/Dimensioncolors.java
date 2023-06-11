package com.swagsteve.dimensioncolors;

import Listeners.WorldChange;
import Utils.Utils;
import Versions.Dimensioncolors1_13;
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

    //Version code
    public static ColorFunctions colorFunctions;

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

        //Version management
        String version = Bukkit.getVersion();
        if (version.contains("1.13") || version.contains("1.14") || version.contains("1.15") || version.contains("1.16") || version.contains("1.17") || version.contains("1.18") || version.contains("1.19") || version.contains("1.20")) {
            colorFunctions = new Dimensioncolors1_13();
        } else {
            getServer().getPluginManager().disablePlugin(this);
            getLogger().info("Unsupported version detected! Disabling plugin...");
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