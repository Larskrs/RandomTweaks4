package net.larskrs.plugins.randomtweaks4.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigTools {

    public static void setLocationToConfSection(YamlConfiguration conf, String adr, Location loc) {
        conf.set(adr + ".location.world", loc.getWorld().getName());
        conf.set(adr + ".location.x", loc.getX());
        conf.set(adr + ".location.y", loc.getY());
        conf.set(adr + ".location.z", loc.getZ());
        conf.set(adr + ".location.yaw", loc.getYaw());
        conf.set(adr + ".location.pitch", loc.getPitch());
    }
    public static Location getLocationFromConfSection(YamlConfiguration conf, String adr) {
        return new Location(
                Bukkit.getWorld(conf.getString(adr + ".location.world")),
        conf.getDouble(adr + ".location.x"),
        conf.getDouble(adr + ".location.y"),
        conf.getDouble(adr + ".location.z"),
         (float) conf.getDouble(adr + ".location.yaw"),
         (float) conf.getDouble(adr + ".location.pitch")
        );
    }

}
