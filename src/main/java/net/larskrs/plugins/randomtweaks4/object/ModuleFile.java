package net.larskrs.plugins.randomtweaks4.object;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ModuleFile {

    private String name;
    private YamlConfiguration config;
    private File file;

    public ModuleFile (String name) {
        this.name = name;
    }

    public File getFile() {return this.file;}

    public void setupFile(RandomTweaks4 RT4) {
        this.file = new File(RT4.getDataFolder(), "modules/" + this.name + ".yml");
        if (!file.exists()) {
            RT4.saveResource("modules/" + this.name + ".yml", false);
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }
    public YamlConfiguration getConfig() {return this.config;}

    public void setEnabled(boolean activeState) {
        this.config.set("enabled", activeState);
        saveFile();
        reloadFile();
    }
    public void saveFile() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reloadFile() {
            try {
                this.config.load(this.file);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
        }
    }
    public boolean isEnabled() {
        return this.config.getBoolean("enabled");
    }
}
