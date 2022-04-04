package net.larskrs.plugins.randomtweaks4.object;

import org.bukkit.entity.Player;

public abstract class GUI {

    public Player player;
    public String title;
    public int size;

    public abstract void openGUI();
    public GUI (Player p, String title, int size) {
        this.player = p;
        this.title = title;
        this.size = size;
    }
    public abstract void setUp();

}
