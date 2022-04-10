package net.larskrs.plugins.randomtweaks4.gui;

import com.cryptomorin.xseries.XMaterial;
import net.larskrs.plugins.randomtweaks4.manager.DataFileManager;
import net.larskrs.plugins.randomtweaks4.object.GUI;
import net.larskrs.plugins.randomtweaks4.tools.ArrayHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class HomeListGui extends GUI {

private Player p;

    public HomeListGui() {
        super("..loading..", 6);
    }

    @Override
    public void openGUI(Player p) {
        player.openInventory(inv);
    }

    @Override
    public void setUp(Player p) {
        inv = Bukkit.createInventory(null, size * 9, p.getName() + " > Homes > 1");
        this.player = p;

        List homes = ArrayHelper.convertArrayToList(DataFileManager.getConfig().getConfigurationSection("player-data." + p.getUniqueId() + ".homes.").getKeys(false).toArray());
        for (int i = 0; i < homes.size(); i++) {


            int interval = 4;
            int placement = 11+i;

            ItemStack homeItem;
            Integer[] illegal = new Integer[]{16,17,18,19};
            if (ArrayHelper.convertArrayToList(illegal).contains(i)) {
                placement += 4;
                homeItem = new ItemStack(XMaterial.BLUE_BED.parseMaterial());
            } else {
                homeItem = new ItemStack(XMaterial.RED_BED.parseMaterial());
            }
            homeItem.setAmount(1);
            ItemMeta meta = homeItem.getItemMeta();
            meta.setDisplayName(homes.get(i).toString());
            homeItem.setItemMeta(meta);

                inv.setItem(placement, homeItem);
        }
    }
}
