package net.larskrs.plugins.randomtweaks4.modules;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.command.*;
import net.larskrs.plugins.randomtweaks4.listener.TpaListener;
import net.larskrs.plugins.randomtweaks4.manager.TpaRequestManager;
import net.larskrs.plugins.randomtweaks4.object.RTModule;
import org.bukkit.Bukkit;

public class TeleportationModule extends RTModule {

    private RandomTweaks4 rt4;

    public TeleportationModule(RandomTweaks4 main, String name) {
        super(main, name);
        rt4 = main;
    }

    @Override
    public void Initialize() {
        if (this.getModuleFile().isEnabled()) {
            TpaRequestManager.setUp();
            new TpaCommand();
            new TpacceptCommand();
        }
    }
    @Override
    public void Reload() {

    }
}
