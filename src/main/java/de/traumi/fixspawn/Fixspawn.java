package de.traumi.fixspawn;

import de.traumi.fixspawn.command.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import de.traumi.fixspawn.event.getleftcord;

public final class Fixspawn extends JavaPlugin {

    private final List<String> registeredCommands = new ArrayList<>();

    private getleftcord leftcord;

    @Override
    public void onEnable() {


        this.registerCommand("teleportlastsaveworld", new TeleportLastSaveWorld(this));

        this.registerCommand("setfixworldspawnpoint", new SetSaveCorWorldCommand(this));
        this.registerCommand("fixworldspawn", new SetSaveCorWorldCommand(this));

        this.registerCommand("fixendspawn", new SetSaveCorEndCommand(this));
        this.registerCommand("fixnetherspawn", new SetSaveCorNetherCommand(this));


        this.registerCommand("tplogoutworld", new TeleportLastSaveWorld(this));
        this.registerCommand("savecords", new SaveCord(this));

        //Bukkit.getPluginManager().registerEvents(new OnLeaveListener(this), this);
        leftcord = new getleftcord(this);
    }
    private <T extends CommandExecutor & TabCompleter> void registerCommand(String command, T obj) {
        registeredCommands.add(command);
        PluginCommand pc = Bukkit.getPluginCommand(command);
        if (pc == null) return;
        pc.setExecutor(obj);
        pc.setTabCompleter(obj);
    }
    // Plugin startup logic
    private void unregisterCommands() {
        for (String command : registeredCommands) {
            PluginCommand pc = Bukkit.getPluginCommand(command);
            if (pc == null) continue;
            pc.setExecutor(null);
            pc.setTabCompleter(null);
        }
    }


    @Override
    public void onDisable() {
        unregisterCommands();
        leftcord = null;
    }


}
