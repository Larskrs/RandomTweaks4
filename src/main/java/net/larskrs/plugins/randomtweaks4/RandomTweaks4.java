package net.larskrs.plugins.randomtweaks4;

import net.larskrs.plugins.randomtweaks4.command.HomeCommand;
import net.larskrs.plugins.randomtweaks4.command.ModuleCommand;
import net.larskrs.plugins.randomtweaks4.manager.ModuleManager;
import net.larskrs.plugins.randomtweaks4.modules.DataFileManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomTweaks4 extends JavaPlugin {


    private final RandomTweaks4 Instance = this;

    @Override
    public void onEnable() {
        // Plugin startup logic
        ModuleManager.setUp(this);
        DataFileManager.setUp(this);
        // Register commands.
            new ModuleCommand();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public RandomTweaks4 getInstance() {
        return Instance;
    }
}
