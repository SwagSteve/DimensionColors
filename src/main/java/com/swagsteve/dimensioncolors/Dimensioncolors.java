package com.swagsteve.dimensioncolors;

import Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dimensioncolors extends JavaPlugin implements Listener {

    //Instance
    private static Dimensioncolors instance;
    public static Dimensioncolors getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {

        //Instance
        instance = this;

        //Enable Message
        getLogger().info("Enabling!");

        //Config
        this.getConfig().options().copyDefaults();
        this.getConfig().addDefault("Options.Enabled", true);
        this.getConfig().addDefault("Options.Overworld-Prefix", "&a");
        this.getConfig().addDefault("Options.Nether-Prefix", "&c");
        this.getConfig().addDefault("Options.End-Prefix", "&d");
        this.getConfig().addDefault("Setup.Check-Delay", 10);
        saveDefaultConfig();

        //Commands
        this.getCommand("dcreload").setExecutor(new ReloadCommand());

        //Dimension Checker
        if (getConfig().getBoolean("Options.Enabled") == true) {
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                @Override
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                            p.setPlayerListName(Utils.Color(getConfig().getString("Options.Overworld-Prefix") + p.getName()));
                        } else if (p.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                            p.setPlayerListName(Utils.Color(getConfig().getString("Options.Nether-Prefix") + p.getName()));
                        } else if (p.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                            p.setPlayerListName(Utils.Color(getConfig().getString("Options.End-Prefix") + p.getName()));
                        }
                    }
                }
            },20,getConfig().getInt("Setup.Check-Delay"));
        }
    }

    @Override
    public void onDisable() {

        //Disable Message
        getLogger().info("Disabling!");

        //Reset Colors
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.getPlayer().setPlayerListName(p.getName());
        }

        //Save Config
        saveConfig();
    }
}
