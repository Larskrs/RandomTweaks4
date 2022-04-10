package net.larskrs.plugins.randomtweaks4.listener;

import net.larskrs.plugins.randomtweaks4.manager.DroneManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DroneListener implements Listener {

    @EventHandler
    public void healthChange(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (DroneManager.isDrone(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void foodChange(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (DroneManager.isDrone(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
            Player p = e.getPlayer();
            if (DroneManager.isDrone(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (DroneManager.isDrone(p.getUniqueId())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void PlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (DroneManager.isDrone(p.getUniqueId())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void PlayerAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (DroneManager.isDrone(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

}
