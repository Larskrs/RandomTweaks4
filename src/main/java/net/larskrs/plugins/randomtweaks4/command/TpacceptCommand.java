package net.larskrs.plugins.randomtweaks4.command;

import net.larskrs.plugins.randomtweaks4.manager.TpaRequestManager;
import net.larskrs.plugins.randomtweaks4.object.Command;
import net.larskrs.plugins.randomtweaks4.object.TpaRequest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TpacceptCommand extends Command {

    public TpacceptCommand() {
        super("tpaccept",
                "randomTweaks.command.tpaccept",
                "accept request to teleport to other players",
                "tpaccept");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

                if (TpaRequestManager.hasRequest(p.getUniqueId())) {
                    TpaRequestManager.getRequest(p.getUniqueId()).respond(true);
                } else {
                    p.sendMessage("You do not have any pending requests.");
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
