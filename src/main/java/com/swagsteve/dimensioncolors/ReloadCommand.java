package com.swagsteve.dimensioncolors;

import Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static com.swagsteve.dimensioncolors.Dimensioncolors.runCheck;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (p.isOp() || p.hasPermission("dc.command.reload")) {

                if (Dimensioncolors.cooldown.containsKey(p) && Dimensioncolors.cooldown.get(p) > System.currentTimeMillis()) {

                    p.sendMessage(Utils.Color("[&dDC&r] &a&lPlease wait before reloading again..."));

                } else {
                    //Put player in cooldown
                    Dimensioncolors.cooldown.put(p, System.currentTimeMillis() + (5 * 1000));

                    Dimensioncolors.getInstance().reloadConfig();
                    p.sendMessage(Utils.Color("[&dDC&r] &a&lConfig successfully reloaded!"));

                    //Re-cache config
                    FileConfiguration config = Dimensioncolors.getInstance().getConfig();
                    Dimensioncolors.confNetherprefix = config.getString("Options.nether-prefix");
                    Dimensioncolors.confEndprefix = config.getString("Options.end-prefix");
                    Dimensioncolors.confOverworldprefix = config.getString("Options.overworld-prefix");
                    Dimensioncolors.confEnabled = config.getBoolean("Options.enabled");

                    //Reset tablist
                    if (!Dimensioncolors.getInstance().getConfig().getBoolean("Options.enabled")) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.setPlayerListName(p.getName());
                        }
                    } else {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            runCheck(all);
                        }
                    }

                }
            } else {
                p.sendMessage(Utils.Color("&c&lYou don't have permission to use this command!"));
            }
        }
        return false;
    }
}