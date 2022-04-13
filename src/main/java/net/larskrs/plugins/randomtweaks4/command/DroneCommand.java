package net.larskrs.plugins.randomtweaks4.command;

import com.cryptomorin.xseries.XMaterial;
import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.manager.DataFileManager;
import net.larskrs.plugins.randomtweaks4.manager.DroneManager;
import net.larskrs.plugins.randomtweaks4.manager.LangManager;
import net.larskrs.plugins.randomtweaks4.manager.TpaRequestManager;
import net.larskrs.plugins.randomtweaks4.object.Command;
import net.larskrs.plugins.randomtweaks4.tools.ConfigTools;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import javax.xml.crypto.Data;
import java.util.List;

public class DroneCommand extends Command {
    public DroneCommand() {
        super("drone",
                "randomTweaks.command.drone",
                "toggle drone mode.",
                "drone");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

                if (args.length >= 2) {
                    for (int i = 0; i < p.getInventory().getSize(); i++) {
                        p.getInventory().setItem(i, new ItemStack(XMaterial.WHITE_STAINED_GLASS.parseMaterial(), i));
                    }
                  return;
                }

                boolean toggle = !DroneManager.isDrone(p.getUniqueId());
                if (toggle) {
                    DroneManager.addDrone(p.getUniqueId());

                } else {
                    DroneManager.removeDrone(p.getUniqueId());
                    LangManager.sendMessage(p, "drone-module.drone-deactivated");
                    DroneManager.clearDrone(p);
                }
        } else {
            LangManager.sendMessage(sender, "general.only-ran-by-player");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
