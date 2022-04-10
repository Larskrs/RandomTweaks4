package net.larskrs.plugins.randomtweaks4.listener;

import net.larskrs.plugins.randomtweaks4.manager.DataFileManager;
import net.larskrs.plugins.randomtweaks4.manager.DroneManager;
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
    }
    @EventHandler
    public void PlayerJoin(PlayerQuitEvent e) {

        Player p = e.getPlayer();

        if (DataFileManager.getConfig().getString("player-data." + p.getUniqueId() + ".drone") != null || DroneManager.isDrone(p.getUniqueId())) {
            DroneManager.clearDrone(p);
        }
    }

}
