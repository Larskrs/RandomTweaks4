package net.larskrs.plugins.randomtweaks4;

import net.larskrs.plugins.randomtweaks4.command.ModuleCommand;
import net.larskrs.plugins.randomtweaks4.listener.DroneListener;
import net.larskrs.plugins.randomtweaks4.listener.JoinListener;
import net.larskrs.plugins.randomtweaks4.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomTweaks4 extends JavaPlugin {


    private static RandomTweaks4 Instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;
        ModuleManager.setUp(this);
        DataFileManager.setUp(this);
        LangManager.setupLangFile(this);
        // Register commands.
            new ModuleCommand();
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        if (ModuleManager.getModuleByName("DroneModule").getModuleFile().isEnabled()) {
        Bukkit.getPluginManager().registerEvents(new DroneListener(), this);
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
}
