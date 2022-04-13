package net.larskrs.plugins.randomtweaks4.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTeleportRequestAcceptedEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled;
    private final Player requester, recipient;
    private Location teleportLocation;

    public PlayerTeleportRequestAcceptedEvent (Player requester, Player recipient) {
        this.cancelled = false;
        this.requester = requester;
        this.recipient = recipient;
        teleportLocation = recipient.getLocation();
    }

    @Override
    public HandlerList getHandlers () {
        return HANDLERS;
    }
    public static HandlerList getHandlerList () {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
    public Player getRequester () {
        return requester;
    }
    public Player getRecipient () {
        return recipient;
    }
    public void setTeleportLocation(Location location) {
        this.teleportLocation = location;
    }
    public Location getTeleportLocation () {
        return this.teleportLocation;
    }

}
