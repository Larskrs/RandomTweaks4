package net.larskrs.plugins.randomtweaks4.object;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class RTModule {

    private final String displayName;
    private final YamlConfiguration config;
    private final ModuleFile moduleFile;

    public RTModule(RandomTweaks4 main, String name) {
        this.displayName = name;
        this.moduleFile = new ModuleFile(name);
        this.moduleFile.setupFile(main);
        this.config = this.moduleFile.getConfig();
        Initialize();
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public YamlConfiguration getConfigFile() {return this.config;}

    public ModuleFile getModuleFile() {
        return this.moduleFile;
    }

    public abstract void Initialize();
}
