package de.traumi.fixspawn.command;

import de.traumi.fixspawn.Fixspawn;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SetSaveCorWorldCommand implements CommandExecutor, TabCompleter {
    private final Fixspawn plugin;

    public SetSaveCorWorldCommand(Fixspawn plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Dieser Befehl kann nur von Spielern ausgeführt werden").color(NamedTextColor.RED));
            return true;
        }
        FileConfiguration config = this.plugin.getConfig();

        config.set("savecorworld", player.getWorld().getName());
        this.plugin.saveConfig();
        sender.sendMessage(Component.text("Die Welt zum berrichtigen des Spawnpoints wurde auf " + player.getWorld().getName() + " gesetzt.").color(NamedTextColor.GREEN));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
