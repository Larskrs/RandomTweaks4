package net.larskrs.plugins.randomtweaks4.object;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class GUI {

    public Inventory inv;

    public Player player;
    public String title;
    public int size;

    public abstract void openGUI(Player p);
    public GUI(String title, int size) {
        this.title = title;
        this.size = size;

        inv = Bukkit.createInventory(null, size * 9, title);
    }
    public abstract void setUp(Player p);

}
