package net.larskrs.plugins.randomtweaks4.listener;

import com.cryptomorin.xseries.XMaterial;
import net.larskrs.plugins.randomtweaks4.manager.DroneManager;
import net.larskrs.plugins.randomtweaks4.manager.ModuleManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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

        if (e.getItem() != null) {

            if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) && e.getItem().getType().equals(XMaterial.CARROT_ON_A_STICK.parseMaterial()) && e.getItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + ChatColor.BOLD.toString() + "Drone")) {
                e.setCancelled(true);

                if (ModuleManager.getModuleByName("DroneModule").getConfigFile().getBoolean("custom-item")) {
                    if (e.getItem().getDurability() < 25) {
                        e.getItem().setDurability((short) (e.getItem().getDurability() + (10)));
                        DroneManager.addDrone(p.getUniqueId());
                    } else {
                        e.getPlayer().getInventory().remove(e.getItem());
                    }
                }
            }
        }

    }
    @EventHandler
    public void PlayerDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (DroneManager.isDrone(p.getUniqueId())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void PlayerDropItem(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (DroneManager.isDrone(p.getUniqueId())) {
                e.setCancelled(true);
            }
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
