package net.larskrs.plugins.randomtweaks4.object;

import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.events.PlayerTeleportRequestAcceptedEvent;
import net.larskrs.plugins.randomtweaks4.manager.LangManager;
import net.larskrs.plugins.randomtweaks4.manager.ModuleManager;
import net.larskrs.plugins.randomtweaks4.manager.TpaRequestManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.StringUtil;
import sun.management.GarbageCollectorImpl;

import java.awt.*;
import java.lang.management.GarbageCollectorMXBean;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TpaRequest extends BukkitRunnable {

    public UUID recipient, requester;
    private int requestTime = Objects.requireNonNull(ModuleManager.getModuleByName("TeleportationModule")).getConfigFile().getInt("tp-request-timer");
    private boolean isExpired;
    private  PlayerTeleportRequestAcceptedEvent event;

    public TpaRequest(UUID requester, UUID recipient) {
        this.recipient = recipient;
        this.requester = requester;
    }

    public void start() {

        Player req = Bukkit.getPlayer(requester);
        Player rec = Bukkit.getPlayer(recipient);

        TextComponent button = new TextComponent(ChatColor.GREEN + "[✓]");
        button.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));

        LangManager.sendMessage(rec, LangManager.replace(LangManager.getMessageFromLocation("tpa-module.request-recieved"), "%requester%",req.getName()));
        rec.spigot().sendMessage(button);
        runTaskTimer(RandomTweaks4.getInstance(), 0, 20);

        event = new PlayerTeleportRequestAcceptedEvent(req, rec);
        Bukkit.getPluginManager().callEvent(event);
    }
    public void expire() {

        Player req = Bukkit.getPlayer(requester);
        Player rec = Bukkit.getPlayer(recipient);

        LangManager.sendMessage(req, LangManager.replace(LangManager.getMessageFromLocation("tpa-module.request-expired"), "%player%", rec.getName()));

        isExpired = true;
        TpaRequestManager.closeRequest(this);
        System.gc();
    }
    public void respond(boolean teleport) {
        Player req = Bukkit.getPlayer(requester);
        Player rec = Bukkit.getPlayer(recipient);

        req.teleport(event.getTeleportLocation());
        LangManager.sendMessage(req, "tpa-module.player-teleported");
        LangManager.sendMessage(rec, "tpa-module.request-accepted");




        isExpired = true;
        TpaRequestManager.closeRequest(this);
        System.gc();
    }

    @Override
    public void run() {
        if (requestTime == 0) {
            if (!isExpired) {
            expire();
            cancel();
            }
        }
            requestTime--;
    }
}
