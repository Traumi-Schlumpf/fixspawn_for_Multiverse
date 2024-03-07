package de.traumi.fixspawn.event;

import de.traumi.fixspawn.Fixspawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class getleftcord implements Listener {
    private final Fixspawn plugin;

    public getleftcord(Fixspawn plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Location location = event.getPlayer().getLocation();
        saveCoordinatesToConfig(event.getPlayer().getUniqueId(), event.getPlayer().getName(), location);
    }


    private boolean CheckWorldMustSave(Location location){
        FileConfiguration config = this.plugin.getConfig();

        if (config.contains("savecorworld")) {
            return  (config.getString("savecorworld").equals(location.getWorld().getName()));
        }

        if (config.contains("savecornether")) {
            return (config.getString("savecornether").equals(location.getWorld().getName()));

        }

        if (config.contains("savecorend")) {
            return config.getString("savecorend").equals(location.getWorld().getName());

        }

        return false;
    }
    private void saveCoordinatesToConfig(UUID uniqueId, String playerName,Location location) {
        if (CheckWorldMustSave(location)) {
            FileConfiguration config = this.plugin.getConfig();

            config.set("players." + uniqueId + ".x", location.getX());
            config.set("players." + uniqueId + ".y", location.getY());
            config.set("players." + uniqueId + ".z", location.getZ());
            config.set("players." + uniqueId + ".world", location.getWorld().getName());
            config.set("players." + uniqueId + ".name", playerName);
            Bukkit.getLogger().info(playerName + " hat das Spiel bei den Cordinaten: X:" + location.getX() + " Y: " + location.getY() + " verlassen.");
            this.plugin.saveConfig();
        }
    }
}
