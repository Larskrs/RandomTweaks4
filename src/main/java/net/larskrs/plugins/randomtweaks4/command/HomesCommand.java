package net.larskrs.plugins.randomtweaks4.command;

import net.larskrs.plugins.randomtweaks4.gui.HomeListGui;
import net.larskrs.plugins.randomtweaks4.object.Command;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HomesCommand extends Command {

    public HomesCommand() {
        super("homes",
                "randomTweaks.command.homes",
                "main command for setting homes.",
                "homes");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            HomeListGui gui = new HomeListGui();
            gui.setUp(p);
            gui.openGUI(p);
        } else {
            sender.sendMessage(ChatColor.RED + " # This command can only be ran by players.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
