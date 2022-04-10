package net.larskrs.plugins.randomtweaks4.command;

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
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
                boolean toggle = !DroneManager.isDrone(p.getUniqueId());
                if (toggle) {
                    DroneManager.addDrone(p.getUniqueId());
                    LangManager.sendMessage(p, "drone-module.drone-activated");
                    DataFileManager.getConfig().set("player-data." + p.getUniqueId() + ".drone.gamemode", p.getGameMode().getValue());
                    DataFileManager.getConfig().set("player-data." + p.getUniqueId() + ".drone.can-fly", p.getAllowFlight());
                    DataFileManager.getConfig().set("player-data." + p.getUniqueId() + ".drone.health", p.getHealth());
                    ConfigTools.setLocationToConfSection(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".drone", p.getLocation());
                    ConfigTools.setVector(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".drone.velocity", p.getVelocity());
                    DataFileManager.saveFile();
                    DataFileManager.reloadFile();
                    // Actual Changes
                    p.setGameMode(GameMode.ADVENTURE);
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999 * 20, 1));
                    p.setHealth(p.getMaxHealth());

                    for (Player ppl : Bukkit.getOnlinePlayers()) {
                        if (ppl != p) {
                            ppl.hidePlayer(p);
                        }
                    }

                } else {
                    DroneManager.removeDrone(p.getUniqueId());
                    LangManager.sendMessage(p, "drone-module.drone-deactivated");
                    p.setGameMode(GameMode.getByValue(DataFileManager.getConfig().getInt("player-data." + p.getUniqueId() + ".drone.gamemode")));
                    p.teleport(ConfigTools.getLocationFromConfSection(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".drone"));
                    p.setVelocity(ConfigTools.getVector(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".velocity"));
                    p.setAllowFlight(DataFileManager.getConfig().getBoolean("player-data." + p.getUniqueId() + ".drone.can-fly"));
                    p.setHealth(DataFileManager.getConfig().getDouble("player-data." + p.getUniqueId() + ".drone.health"));
                    p.setFlying(DataFileManager.getConfig().getBoolean("player-data." + p.getUniqueId() + ".drone.can-fly"));
                    DataFileManager.getConfig().set("player-data." + p.getUniqueId() + ".drone", null);
                    DataFileManager.saveFile();
                    DataFileManager.reloadFile();

                    for (Player ppl : Bukkit.getOnlinePlayers()) {
                        if (ppl != p) {
                            ppl.showPlayer(p);
                        }
                    }

                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
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
