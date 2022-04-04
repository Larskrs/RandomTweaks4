package net.larskrs.plugins.randomtweaks4.command;

import net.larskrs.plugins.randomtweaks4.manager.DataFileManager;
import net.larskrs.plugins.randomtweaks4.object.Command;
import net.larskrs.plugins.randomtweaks4.tools.ConfigTools;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DelhomeCommand extends Command {
    public DelhomeCommand() {
        super("delhome",
                "randomTweaks.command.delhome",
                "This exist to delete your homes.",
                "delhome",
                new String[]{"dhome"});
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            String homeName = args.length >= 1 ? args[0] : "Home";
            boolean confirm = args.length == 2 && args[1].equalsIgnoreCase("confirm");

            if (!DataFileManager.getConfig().isConfigurationSection("player-data." + p.getUniqueId() + ".homes")) {
                p.sendMessage(ChatColor.RED + " # You do not have any homes to delete.");
                return;
            }
            boolean isHome = DataFileManager.getConfig().getConfigurationSection("player-data." + p.getUniqueId() + ".homes").getKeys(false).contains(homeName);

            if (isHome) {
                if (confirm) {
                    DataFileManager.getConfig().set("player-data." + p.getUniqueId() + ".homes." + homeName, null); DataFileManager.saveFile(); DataFileManager.reloadFile();
                p.sendMessage(ChatColor.YELLOW + " > You home was successfully deleted. " + ChatColor.RED + homeName);
                } else {
                    // send a clickable message that when clicked will execute
                    //   /delhome homeName confirm

                    p.sendMessage("You need to confirm the deletion.");
                    TextComponent txtC = new TextComponent();
                }
            } else {
                p.sendMessage(ChatColor.RED + " # That is not a home.");
            }



        } else {
            sender.sendMessage(ChatColor.RED + " # This command can only be ran by players.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (!DataFileManager.getConfig().isConfigurationSection("player-data." + p.getUniqueId() + ".homes")) {
                return null;
            }
            return new ArrayList<>(DataFileManager.getConfig().getConfigurationSection("player-data." + p.getUniqueId() + ".homes").getKeys(false));
        } else {
            return null;
        }


    }
    private static <C, T> List<T> valueGrabber(List<C> items, Function<C, T> func) {
        return items.stream().map(func).collect(Collectors.toList());
    }
}
