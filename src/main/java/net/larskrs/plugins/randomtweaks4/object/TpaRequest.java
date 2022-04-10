package net.larskrs.plugins.randomtweaks4.object;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.manager.ModuleManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import sun.management.GarbageCollectorImpl;

import java.lang.management.GarbageCollectorMXBean;
import java.util.Objects;
import java.util.UUID;

public class TpaRequest extends BukkitRunnable {

    private UUID recipient, requester;
    private int requestTime = Objects.requireNonNull(ModuleManager.getModuleByName("TeleportationModule")).getConfigFile().getInt("tp-request-timer");
    private boolean isExpired;

    public TpaRequest(UUID requester, UUID recipient) {
        this.recipient = recipient;
        this.requester = requester;
    }

    public void start() {
        runTaskTimer(RandomTweaks4.getInstance(), 0, 20);
    }
    public void expire() {

        Player req = Bukkit.getPlayer(requester);
        Player rec = Bukkit.getPlayer(recipient);

        req.sendMessage(ChatColor.RED + " # " + ChatColor.GRAY + rec.getName() + " did not respond to the teleportation request in time.");

        isExpired = true;
    }
    public void respond(boolean teleport) {
        Player req = Bukkit.getPlayer(requester);
        Player rec = Bukkit.getPlayer(recipient);

        req.teleport(rec);
        req.sendMessage("You have been teleported.");
        rec.sendMessage("You accepted the request.");
    }

    @Override
    public void run() {
        if (requestTime == 0 && !isExpired) {
            expire();
        }
            requestTime--;
    }
}
