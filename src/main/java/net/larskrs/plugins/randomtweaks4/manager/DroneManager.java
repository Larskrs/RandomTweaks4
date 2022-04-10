package net.larskrs.plugins.randomtweaks4.manager;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.tools.ConfigTools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DroneManager {

    private static List<UUID> drones;

    public static void setUp(RandomTweaks4 rt4) {
        drones = new ArrayList<>();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "Loading DroneManager"));
    }

    public static boolean isDrone(UUID uuid) {
        return drones.contains(uuid);
    }
    public static void addDrone(UUID uuid) {
        if (!isDrone(uuid)) {
            drones.add(uuid);
        }
    }
    public static void removeDrone(UUID uuid) {
        if (isDrone(uuid)) {
            drones.remove(uuid);

        }
    }
    public static void toggle(UUID uuid, Boolean toggle) {
        if (toggle) {
            addDrone(uuid);
        } else {
            removeDrone(uuid);
        }
    }
    public static void clearDrones() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            clearDrone(p);
        }

    }
    public static void clearDrone(Player p) {
        DroneManager.removeDrone(p.getUniqueId());
        p.setGameMode(GameMode.getByValue(DataFileManager.getConfig().getInt("player-data." + p.getUniqueId() + ".drone.gamemode")));
        p.teleport(ConfigTools.getLocationFromConfSection(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".drone"));
        p.setVelocity(ConfigTools.getVector(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".velocity"));
        p.setAllowFlight(DataFileManager.getConfig().getBoolean("player-data." + p.getUniqueId() + ".drone.can-fly"));
        p.setHealth(DataFileManager.getConfig().getDouble("player-data." + p.getUniqueId() + ".drone.health"));
        p.setFlying(DataFileManager.getConfig().getBoolean("player-data." + p.getUniqueId() + ".drone.can-fly"));
        DataFileManager.getConfig().set("player-data." + p.getUniqueId() + ".drone", null);
        DataFileManager.saveFile();
        DataFileManager.reloadFile();

        for (Player ppl : Bukkit.getOnlinePlayers()) {
            if (ppl != p) {
                ppl.showPlayer(p);
            }
        }

        p.removePotionEffect(PotionEffectType.INVISIBILITY);
    }

}
