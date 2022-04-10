package net.larskrs.plugins.randomtweaks4;

import net.larskrs.plugins.randomtweaks4.command.ModuleCommand;
import net.larskrs.plugins.randomtweaks4.manager.ModuleManager;
import net.larskrs.plugins.randomtweaks4.manager.DataFileManager;
import net.larskrs.plugins.randomtweaks4.manager.TpaRequestManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomTweaks4 extends JavaPlugin {


    private static RandomTweaks4 Instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;
        ModuleManager.setUp(this);
        TpaRequestManager.setUp();
        DataFileManager.setUp(this);
        // Register commands.
            new ModuleCommand();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RandomTweaks4 getInstance() {
        return Instance;
    }
}
