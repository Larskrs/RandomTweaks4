package net.larskrs.plugins.randomtweaks4.modules;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.command.DroneCommand;
import net.larskrs.plugins.randomtweaks4.listener.DroneListener;
import net.larskrs.plugins.randomtweaks4.manager.DroneManager;
import net.larskrs.plugins.randomtweaks4.object.RTModule;
import org.bukkit.Bukkit;

public class DroneModule extends RTModule {

    private RandomTweaks4 rt4;

    public DroneModule(RandomTweaks4 main, String name) {
        super(main, name);
        rt4 = main;
    }

    @Override
    public void Initialize() {
        if (this.getModuleFile().isEnabled()) {
            DroneManager.setUp(rt4);
            new DroneCommand();
        }
    }
}
