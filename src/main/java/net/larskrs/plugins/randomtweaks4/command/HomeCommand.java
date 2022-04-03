package net.larskrs.plugins.randomtweaks4.command;

import net.larskrs.plugins.randomtweaks4.object.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

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

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
