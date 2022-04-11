package net.larskrs.plugins.randomtweaks4.command;

import com.sun.org.apache.xpath.internal.operations.Mod;
import net.larskrs.plugins.randomtweaks4.manager.ModuleManager;
import net.larskrs.plugins.randomtweaks4.object.ModuleFile;
import net.larskrs.plugins.randomtweaks4.object.RTModule;
import net.larskrs.plugins.randomtweaks4.tools.ChatColorTool;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleCommand extends net.larskrs.plugins.randomtweaks4.object.Command {


    public ModuleCommand() {
        super("module",
                "randomTweaks.command.module",
                "This is the main command to control modules.",
                "module",
                new String[]{"modul", "mod"});
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
            if (args.length > 1 && args[0].equalsIgnoreCase("toggle")) {
                if (ModuleManager.isModule(args[1])) {
                    RTModule module = ModuleManager.getModuleByName(args[1]);
                    ModuleFile moduleFile = module.getModuleFile();
                    module.getModuleFile().setEnabled(!moduleFile.isEnabled());

                    sender.sendMessage(ChatColor.GRAY + " # You set the module to " + ((moduleFile.isEnabled()) ? ChatColor.GREEN + " > Enabled" : ChatColor.RED + " > Disabled"));
                } else {
                    sender.sendMessage(ChatColor.RED + " # That is not the name of a module!");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
                for (int i = 0; i < ModuleManager.getModules().size(); i++) {
                    RTModule m = ModuleManager.getModules().get(i);
                    sender.sendMessage(ChatColorTool.getBooleanColor(m.getModuleFile().isEnabled()) + " ● " + ChatColor.GRAY + m.getDisplayName() + ((m.getModuleFile().isEnabled()) ? ChatColor.GREEN + " > Enabled" : ChatColor.RED + " > Disabled"));
                }
            } if (args.length > 1 && args[0].equalsIgnoreCase("reload")) {
                if (ModuleManager.isModule(args[1])) {
                    RTModule module = ModuleManager.getModuleByName(args[1]);
                    ModuleFile moduleFile = module.getModuleFile();
                    module.getModuleFile().reloadFile();
                    module.Reload();

                    sender.sendMessage(ChatColor.GRAY + " # You set the module to " + ((moduleFile.isEnabled()) ? ChatColor.GREEN + " > Enabled" : ChatColor.RED + " > Disabled"));
                } else {
                    sender.sendMessage(ChatColor.RED + " # That is not the name of a module!");
                }
            }
        }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {

        List<String> options = new ArrayList<>();
        if (args.length == 1) {
            options.add("toggle");
            options.add("list");
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("toggle")) {
        for (int i = 0; i < ModuleManager.getModules().size(); i++) {
            options.add(ModuleManager.getModules().get(i).getDisplayName());
            }
        }


        if (options.isEmpty()) {
        return null;
        } else {
            return StringUtil.copyPartialMatches(args[args.length - 1], options, new ArrayList<>());
        }
    }
}
