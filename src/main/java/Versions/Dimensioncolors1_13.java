package Versions;

import Utils.Utils;
import com.swagsteve.dimensioncolors.ColorFunctions;
import com.swagsteve.dimensioncolors.Dimensioncolors;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Dimensioncolors1_13 implements ColorFunctions {

    @Override
    public void runCheck(Player p) {
        if (Dimensioncolors.confEnabled) {
            if (p.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                p.setPlayerListName(Utils.Color(Dimensioncolors.confNetherprefix + p.getName() + Dimensioncolors.confNethersuffix));
            } else if (p.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                p.setPlayerListName(Utils.Color(Dimensioncolors.confEndprefix + p.getName() + Dimensioncolors.confEndsuffix));
            } else {
                p.setPlayerListName(Utils.Color(Dimensioncolors.confOverworldprefix + p.getName() + Dimensioncolors.confOverworldsuffix));
            }
        }
    }
}