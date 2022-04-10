package net.larskrs.plugins.randomtweaks4.command;

import net.larskrs.plugins.randomtweaks4.manager.TpaRequestManager;
import net.larskrs.plugins.randomtweaks4.object.Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TpaCommand extends Command {

    public TpaCommand() {
        super("tpa",
                "randomTweaks.command.tpa",
                "request to teleport to other players",
                "tpa");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length != 0) {
                Player t = Bukkit.getPlayer(args[0]);
                if (Bukkit.getOnlinePlayers().contains(t)) {
                    TpaRequestManager.sendRequest(p.getUniqueId(), t.getUniqueId());
                    p.sendMessage("Send a request to");
                } else {
                    p.sendMessage("Please input a player");
                }
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
