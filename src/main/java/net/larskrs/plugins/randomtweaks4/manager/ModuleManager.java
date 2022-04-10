package net.larskrs.plugins.randomtweaks4.manager;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.modules.HomeModule;
import net.larskrs.plugins.randomtweaks4.modules.TeleportationModule;
import net.larskrs.plugins.randomtweaks4.object.RTModule;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModuleManager {

    private static RandomTweaks4 main;
    private static List<RTModule> moduleList;

    public static void setUp(RandomTweaks4 rt4) {
        main = rt4;
        moduleList = new ArrayList<>();

        moduleList.add(new HomeModule(main, "HomeModule"));
        moduleList.add(new TeleportationModule(main, "TeleportationModule"));
    }
    public static RTModule getModuleByName(String name) {
        for (RTModule m : moduleList
             ) {
            if (Objects.equals(m.getDisplayName(), name)) {
                return m;
            }
        }
        return null;
    }
    public static List<RTModule> getModules() {
        return moduleList;
    }
    public static RTModule getModuleByConfig(YamlConfiguration yamlConfiguration) {
        for (RTModule m: moduleList
             ) {
            if (yamlConfiguration == m.getConfigFile()) {
                return m;
            }
        }
        return null;
    }

    public static boolean isModule(String arg) {
        return getModuleByName(arg) != null;
    }
}
