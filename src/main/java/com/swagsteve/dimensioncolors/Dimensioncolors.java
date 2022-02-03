package com.swagsteve.dimensioncolors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dimensioncolors extends JavaPlugin implements Listener {

    Boolean joinmessage;


    @Override
    public void onEnable() {

        //Enable Message
        System.out.println("[DC] Enabling!");

        //Dimension Checker
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                        p.setPlayerListName(ChatColor.GREEN + p.getName());
                    } else if (p.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                        p.setPlayerListName(ChatColor.RED + p.getName());
                    } else if (p.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                        p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName());
                    }
                }
            }
        },10,10);
    }

    @Override
    public void onDisable() {

        //Disable Message
        System.out.println("[DC] Disabling!");

        //Reset Colors
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.getPlayer().setPlayerListName(p.getName());
        }
    }
}
