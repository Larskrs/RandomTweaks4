package net.larskrs.plugins.randomtweaks4;

import net.larskrs.plugins.randomtweaks4.command.ModuleCommand;
import net.larskrs.plugins.randomtweaks4.listener.DroneListener;
import net.larskrs.plugins.randomtweaks4.listener.JoinListener;
import net.larskrs.plugins.randomtweaks4.listener.TpaListener;
import net.larskrs.plugins.randomtweaks4.manager.*;
import net.larskrs.plugins.randomtweaks4.modules.DroneModule;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomTweaks4 extends JavaPlugin {


    private static RandomTweaks4 Instance;
    private boolean hasCitizens;
    private boolean hasPlaceholderAPI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;
        ModuleManager.setUp(this);
        DataFileManager.setUp(this);
        LangManager.setupLangFile(this);
        // Register commands.
            new ModuleCommand();
        hasCitizens = Bukkit.getPluginManager().getPlugin("Citizens") != null && Bukkit.getPluginManager().getPlugin("Citizens").isEnabled();
        hasPlaceholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null && Bukkit.getPluginManager().getPlugin("PlaceholderAPI").isEnabled();
        if (hasCitizens) {
            Bukkit.getConsoleSender().sendMessage(LangManager.getPrefix() + ChatColor.GREEN + " Hooked into Citizens!");
        }
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        if (ModuleManager.getModuleByName("DroneModule").getModuleFile().isEnabled()) {
            Bukkit.getPluginManager().registerEvents(new DroneListener(), this);
            if (ModuleManager.getModuleByName("DroneModule").getConfigFile().getBoolean("custom-item")) {
            DroneModule.registerDroneRecipe(this);
            }
            DroneManager.clearDrones();
            if (hasCitizens) {
                //Bukkit.getPluginManager().registerEvents(new NpcListener(), this);
            }
        }
        if (ModuleManager.getModuleByName("TeleportationModule").getModuleFile().isEnabled()) {
            Bukkit.getPluginManager().registerEvents(new TpaListener(), this);
        }





    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (ModuleManager.getModuleByName("DroneModule").getModuleFile().isEnabled()) {
            DroneManager.clearDrones();
        }
    }

    public static RandomTweaks4 getInstance() {
        return Instance;
    }

    public boolean hasCitizens() {
        return hasCitizens;
    }

    public boolean hasPlaceholderAPI() {
        return hasPlaceholderAPI;
    }
}
