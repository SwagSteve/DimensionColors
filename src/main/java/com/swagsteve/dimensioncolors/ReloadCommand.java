package com.swagsteve.dimensioncolors;

import Utils.Utils;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (p.isOp()) {

                Dimensioncolors.getInstance().reloadConfig();
                p.sendMessage(Utils.Color("&a&lConfig Successfully Reloaded!"));
                p.sendMessage(Utils.Color("&c&lIMPORTANT: &r&cIf You Changed The 'Enabled' Or 'Check-Delay' Option You Must Restart The Server To Apply This Change"));
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, 1, 1);

            } else {
                p.sendMessage(Utils.Color("&c&lYou Don't Have Permission To Use This Command!"));
            }
        }
        return false;
    }
}
