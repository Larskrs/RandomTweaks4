package net.larskrs.plugins.randomtweaks4.listener;

import net.larskrs.plugins.randomtweaks4.events.PlayerTeleportRequestAcceptedEvent;
import net.larskrs.plugins.randomtweaks4.manager.DroneManager;
import net.larskrs.plugins.randomtweaks4.manager.ModuleManager;
import net.larskrs.plugins.randomtweaks4.modules.DroneModule;
import net.larskrs.plugins.randomtweaks4.object.ModuleFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TpaListener implements Listener {

    @EventHandler
    public void onTeleport (PlayerTeleportRequestAcceptedEvent e) {
        if (!ModuleManager.getModuleByName("DroneModule").getModuleFile().isEnabled()) {
            return;
        }
        if (DroneManager.isDrone(e.getRecipient().getUniqueId())) {
            e.setTeleportLocation(DroneManager.getDrone(e.getRecipient().getUniqueId()).npc.getEntity().getLocation());
        }
    }

}
