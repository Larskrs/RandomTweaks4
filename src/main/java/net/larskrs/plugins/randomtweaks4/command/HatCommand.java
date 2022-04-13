package net.larskrs.plugins.randomtweaks4.command;

import net.larskrs.plugins.randomtweaks4.object.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HatCommand extends Command {

    public HatCommand() {
        super("hat",
                "randomTweaks.command.hat",
                "wear anything.",
                "hat");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        p.getInventory().setHelmet(p.getItemInHand());
        p.sendMessage("put on the hat.");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
