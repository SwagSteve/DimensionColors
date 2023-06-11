package Listeners;

import com.swagsteve.dimensioncolors.Dimensioncolors;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class WorldChange implements Listener {

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        Dimensioncolors.colorFunctions.runCheck(e.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Dimensioncolors.colorFunctions.runCheck(e.getPlayer());
    }
}