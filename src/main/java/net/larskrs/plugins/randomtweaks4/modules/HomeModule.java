package net.larskrs.plugins.randomtweaks4.modules;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.command.DelhomeCommand;
import net.larskrs.plugins.randomtweaks4.command.HomeCommand;
import net.larskrs.plugins.randomtweaks4.command.HomesCommand;
import net.larskrs.plugins.randomtweaks4.command.SethomeCommand;
import net.larskrs.plugins.randomtweaks4.object.RTModule;

public class HomeModule extends RTModule {
    public HomeModule(RandomTweaks4 main, String name) {
        super(main, name);
    }

    @Override
    public void Initialize() {
        if (this.getModuleFile().isEnabled()) {
            new HomeCommand();
            new SethomeCommand();
            new DelhomeCommand();
            new HomesCommand();
        }
    }
}
