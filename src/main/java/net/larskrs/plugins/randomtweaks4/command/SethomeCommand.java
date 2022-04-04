package net.larskrs.plugins.randomtweaks4.command;

import net.larskrs.plugins.randomtweaks4.manager.ModuleManager;
import net.larskrs.plugins.randomtweaks4.manager.DataFileManager;
import net.larskrs.plugins.randomtweaks4.object.Command;
import net.larskrs.plugins.randomtweaks4.tools.ConfigTools;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SethomeCommand extends Command {

        public SethomeCommand() {
            super("sethome",
                    "randomTweaks.command.sethome",
                    "main command for setting homes.",
                    "home");
        }


        @Override
        public void execute(CommandSender sender, String[] args) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                String homeName = (args.length == 1) ? args[0] : "Home";
                Boolean hasHome = DataFileManager.getConfig().isConfigurationSection("player-data." + p.getUniqueId() + ".homes");


                    if (hasHome) {
                        int playerHomes = DataFileManager.getConfig().getConfigurationSection("player-data." + p.getUniqueId() + ".homes.").getKeys(false).size();

                        if (ModuleManager.getModuleByName("HomeModule").getModuleFile().getConfig().getInt("max-homes") > playerHomes) {
                            ConfigTools.setLocationToConfSection(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".homes." + homeName, p.getLocation());
                            DataFileManager.saveFile();
                            DataFileManager.reloadFile();
                            p.sendMessage(ChatColor.YELLOW + " > Saved your home location. You can use " + ChatColor.RED + "/home " + ChatColor.YELLOW + "to come back to this place.");

                        } else {
                            p.sendMessage(ChatColor.RED + " # You can't make more than " + playerHomes + " homes.");
                        }
                    } else {
                            ConfigTools.setLocationToConfSection(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".homes." + homeName, p.getLocation());
                            DataFileManager.saveFile();
                            DataFileManager.reloadFile();
                            p.sendMessage(ChatColor.YELLOW + " > Saved your home location. You can use " + ChatColor.RED + "/home " + ChatColor.YELLOW + "to come back to this place.");
                    }





            } else {
                sender.sendMessage(ChatColor.RED + " # This command can only be ran by players.");
            }
        }

        @Override
        public List<String> onTabComplete(CommandSender sender, String[] args) {
            return null;
        }
}
