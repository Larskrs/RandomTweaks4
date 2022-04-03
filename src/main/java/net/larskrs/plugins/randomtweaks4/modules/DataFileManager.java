package net.larskrs.plugins.randomtweaks4.modules;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.object.ModuleFile;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataFileManager {

    private static YamlConfiguration config;
    private static File file;

    public static void setUp(RandomTweaks4 RT4) {
        file = new File(RT4.getDataFolder(), "data.yml");
        if (!file.exists()) {
            RT4.saveResource("data.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public static YamlConfiguration getConfig() {return config;}

    public static void saveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void reloadFile() {
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
