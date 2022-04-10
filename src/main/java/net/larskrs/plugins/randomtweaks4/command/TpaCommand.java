package net.larskrs.plugins.randomtweaks4.command;

import net.larskrs.plugins.randomtweaks4.manager.LangManager;
import net.larskrs.plugins.randomtweaks4.manager.TpaRequestManager;
import net.larskrs.plugins.randomtweaks4.object.Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
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
                    if (!t.getName().equalsIgnoreCase(p.getName())) {
                        TpaRequestManager.sendRequest(p.getUniqueId(), t.getUniqueId());
                        LangManager.sendMessage(p, "tpa-module.request-sent");
                    } else {
                        LangManager.sendMessage(p, "tpa-module.request-cant-self");
                    }
                } else {
                    LangManager.sendMessage(p, "tpa-module.request-not-player");
                }
            }
        } else {
            LangManager.sendMessage(sender, "general.only-ran-by-player");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {

        List<String> options = new ArrayList<>();

        List<String> plNames = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            plNames.add(p.getName());
        }

        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], plNames, options);
        }

        return options;
    }

}
