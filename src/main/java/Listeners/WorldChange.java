package Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.swagsteve.dimensioncolors.Dimensioncolors.runCheck;

public class WorldChange implements Listener {

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        runCheck(e.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        runCheck(e.getPlayer());
    }
}