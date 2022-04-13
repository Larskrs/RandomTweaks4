package net.larskrs.plugins.randomtweaks4.manager;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.object.Drone;
import net.larskrs.plugins.randomtweaks4.tools.ConfigTools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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

            Player p = Bukkit.getPlayer(uuid);

            DroneManager.addDrone(p.getUniqueId());
            LangManager.sendMessage(p, "drone-module.drone-activated");
            DataFileManager.getConfig().set("player-data." + p.getUniqueId() + ".drone.gamemode", p.getGameMode().getValue());
            DataFileManager.getConfig().set("player-data." + p.getUniqueId() + ".drone.can-fly", p.getAllowFlight());
            DataFileManager.getConfig().set("player-data." + p.getUniqueId() + ".drone.health", p.getHealth());
            ConfigTools.setLocationToConfSection(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".drone", p.getLocation());
            ConfigTools.setVector(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".drone.velocity", p.getVelocity());
            ConfigTools.savePlayerInventory(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".drone.inventory", p.getInventory());
            DataFileManager.saveFile();
            DataFileManager.reloadFile();
            // Actual Changes
            p.setGameMode(GameMode.ADVENTURE);
            p.setAllowFlight(true);
            p.setFlying(true);
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999 * 20, 1));
            p.setHealth(p.getMaxHealth());
            p.getInventory().clear();
            if (RandomTweaks4.getInstance().hasCitizens()) {
                DroneManager.getDrone(p.getUniqueId()).spawnNpc();
            }
            p.teleport(p.getLocation().add(new Vector(0,0.2,0)));

            for (Player ppl : Bukkit.getOnlinePlayers()) {
                if (ppl != p) {
                    ppl.hidePlayer(p);
                }
            }
        }
    }
    public static void removeDrone(UUID uuid) {
        if (isDrone(uuid)) {
            getDrone(uuid).clear();
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
                return d;
            }
        }
        return null;
    }
}
