package net.larskrs.plugins.randomtweaks4.manager;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.object.Drone;
import net.larskrs.plugins.randomtweaks4.tools.ConfigTools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DroneManager {

    private static List<Drone> drones;

    public static void setUp(RandomTweaks4 rt4) {
        drones = new ArrayList<>();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "Loading DroneManager"));
    }

    public static boolean isDrone(UUID uuid) {
        return drones.contains(getDrone(uuid));
    }
    public static void addDrone(UUID uuid) {
        if (!isDrone(uuid)) {
            drones.add(new Drone(uuid));
        }
    }
    public static void removeDrone(UUID uuid) {
        if (isDrone(uuid)) {
            if (RandomTweaks4.getInstance().hasCitizens()) {
            getDrone(uuid).clearNpc();
            }
            drones.remove(getDrone(uuid));

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

        if (!DataFileManager.getConfig().contains("player-data." + p.getUniqueId() + ".drone")) {
            return;
        }

        p.setGameMode(GameMode.getByValue(DataFileManager.getConfig().getInt("player-data." + p.getUniqueId() + ".drone.gamemode")));
        p.teleport(ConfigTools.getLocationFromConfSection(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".drone"));
        p.setVelocity(ConfigTools.getVector(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".velocity"));
        p.setAllowFlight(DataFileManager.getConfig().getBoolean("player-data." + p.getUniqueId() + ".drone.can-fly"));
        p.setHealth(DataFileManager.getConfig().getDouble("player-data." + p.getUniqueId() + ".drone.health"));
        p.setFlying(DataFileManager.getConfig().getBoolean("player-data." + p.getUniqueId() + ".drone.can-fly"));
        if (DataFileManager.getConfig().isConfigurationSection("player-data." + p.getUniqueId() + ".drone.inventory")) {
        ConfigTools.getPlayerInventory(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".drone.inventory", p);
        }
        DataFileManager.getConfig().set("player-data." + p.getUniqueId() + ".drone", null);
        DataFileManager.saveFile();
        DataFileManager.reloadFile();

        for (Player ppl : Bukkit.getOnlinePlayers()) {
            if (ppl != p) {
                ppl.showPlayer(p);
            }
        }

        p.removePotionEffect(PotionEffectType.INVISIBILITY);

        DroneManager.removeDrone(p.getUniqueId());
    }

    public static Drone getDrone(UUID uniqueId) {
        for (Drone d : drones) {
            if (d.owner == uniqueId) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + " found drone.");
                return d;
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + " failed to catch drone.");
        return null;
    }
}
