package de.traumi.fixspawn.command;

import de.traumi.fixspawn.Fixspawn;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class SaveCord implements CommandExecutor, TabCompleter {
    private final Fixspawn plugin;

    public SaveCord(Fixspawn plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Dieser Befehl kann nur von Spielern ausgeführt werden").color(NamedTextColor.RED));
            return true;
        }

        if(saveCoordinatesToConfig(player.getUniqueId(), player.getName(), player.getLocation())){
            sender.sendMessage(Component.text("Deine Position wurde akltualisiert.").color(NamedTextColor.GREEN));
        } else {
            sender.sendMessage(Component.text("Deine Position wurde nicht akltualisiert.").color(NamedTextColor.RED));
        }
        return true;
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
    private boolean saveCoordinatesToConfig(UUID uniqueId, String playerName,Location location) {
        if (CheckWorldMustSave(location)) {
            FileConfiguration config = this.plugin.getConfig();

            config.set("players." + uniqueId + ".x", location.getX());
            config.set("players." + uniqueId + ".y", location.getY());
            config.set("players." + uniqueId + ".z", location.getZ());
            config.set("players." + uniqueId + ".world", location.getWorld().getName());
            config.set("players." + uniqueId + ".name", playerName);
            Bukkit.getLogger().info(playerName + " hat das Spiel bei den Cordinaten: X:" + location.getX() + " Y: " + location.getY() + " verlassen.");
            this.plugin.saveConfig();
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
