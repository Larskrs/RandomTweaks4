package net.larskrs.plugins.randomtweaks4.command;

import net.larskrs.plugins.randomtweaks4.manager.ModuleManager;
import net.larskrs.plugins.randomtweaks4.modules.DataFileManager;
import net.larskrs.plugins.randomtweaks4.object.Command;
import net.larskrs.plugins.randomtweaks4.tools.ConfigTools;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class HomeCommand extends Command {
        public HomeCommand() {
            super("home",
                    "randomTweaks.command.home",
                    "main command for home module.",
                    "home",
                    new String[]{"hme"});
        }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            String homeName = (args.length == 1) ? args[0] : "Home";
            if (!DataFileManager.getConfig().isConfigurationSection("player-data." + p.getUniqueId() + ".homes")) {
                p.sendMessage(ChatColor.RED + " # You do not have any homes.");
                return;
            }
            Boolean isHome = DataFileManager.getConfig().getConfigurationSection("player-data." + p.getUniqueId() + ".homes").getKeys(false).contains(homeName);

                if (isHome) {
                p.sendMessage(ChatColor.YELLOW + " > Teleported you to. " + ChatColor.RED + homeName);
                Location HomeLocation = ConfigTools.getLocationFromConfSection(DataFileManager.getConfig(), "player-data." + p.getUniqueId() + ".homes." + homeName);
                p.teleport(HomeLocation);
                } else {
                    p.sendMessage(ChatColor.RED + " # That is not a home.");
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
