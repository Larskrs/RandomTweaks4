package net.larskrs.plugins.randomtweaks4.listener;

import net.larskrs.plugins.randomtweaks4.manager.DataFileManager;
import net.larskrs.plugins.randomtweaks4.manager.DroneManager;
import net.larskrs.plugins.randomtweaks4.manager.ModuleManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if (DataFileManager.getConfig().getString("player-data." + p.getUniqueId() + ".drone") != null || DroneManager.isDrone(p.getUniqueId())) {
            DroneManager.clearDrone(p);
        }
        if (ModuleManager.getModuleByName("DroneModule").getConfigFile().getBoolean("custom-item")) {
            e.getPlayer().setResourcePack("https://drive.google.com/uc?export=download&id=1t7dhOiKX1FBbtiGy4iT56W5_MBXTfOqa");
        }
    }
    @EventHandler
    public void PlayerJoin(PlayerQuitEvent e) {

        Player p = e.getPlayer();

        if (DataFileManager.getConfig().getString("player-data." + p.getUniqueId() + ".drone") != null || DroneManager.isDrone(p.getUniqueId())) {
            DroneManager.clearDrone(p);
        }
    }

}
