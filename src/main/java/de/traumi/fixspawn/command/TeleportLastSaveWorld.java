package de.traumi.fixspawn.command;

import de.traumi.fixspawn.Fixspawn;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TeleportLastSaveWorld implements CommandExecutor, TabCompleter {

    private final Fixspawn plugin;

    public TeleportLastSaveWorld(Fixspawn plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Dieser Befehl kann nur von Spielern ausgeführt werden").color(NamedTextColor.RED));
            return true;
        }
        FileConfiguration config = this.plugin.getConfig();


        if(config.contains("players." + player.getUniqueId() + ".x")){
            double x = config.getDouble("players." + player.getUniqueId() + ".x");
            double y = config.getDouble("players." + player.getUniqueId() + ".y");
            double z = config.getDouble("players." + player.getUniqueId() + ".z");
            String worldName = config.getString("players." + player.getUniqueId() + ".world");


            Location targetlocation = new Location(Bukkit.getWorld(worldName), x, y, z);
            player.teleport(targetlocation);
        }else{
            if(config.contains("savecorworld")) {
                String worldName = config.getString("savecorworld");
                World world = Bukkit.getWorld(worldName);
                Location spawnLocation = world.getSpawnLocation();
                player.teleport(spawnLocation);
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
