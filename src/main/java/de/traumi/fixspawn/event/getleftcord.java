package event;

import de.traumi.fixspawn.Fixspawn;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class getleftcord implements Listener {
    private final Fixspawn plugin;

    public getleftcord(Fixspawn plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
