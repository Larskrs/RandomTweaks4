package net.larskrs.plugins.randomtweaks4.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.Vector;

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

    public static void setVector(YamlConfiguration conf, String adr, Vector vel) {
        conf.set(adr + ".vector.x", vel.getX());
        conf.set(adr + ".vector.y", vel.getY());
        conf.set(adr + ".vector.z", vel.getZ());
    }
    public static Vector getVector(YamlConfiguration conf, String adr) {
        return new Vector(
          conf.getDouble(adr + ".vector.x"), conf.getDouble(adr + ".vector.y"), conf.getDouble(adr + ".vector.z")
                );
    }
}
