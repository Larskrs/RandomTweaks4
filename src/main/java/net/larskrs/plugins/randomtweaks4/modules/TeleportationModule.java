package net.larskrs.plugins.randomtweaks4.modules;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.command.*;
import net.larskrs.plugins.randomtweaks4.object.RTModule;

public class TeleportationModule extends RTModule {
    public TeleportationModule(RandomTweaks4 main, String name) {
        super(main, name);
    }

    @Override
    public void Initialize() {
        if (this.getModuleFile().isEnabled()) {
            new TpaCommand();
            new TpacceptCommand();
        }
    }
}
