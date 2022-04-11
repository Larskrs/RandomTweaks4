package net.larskrs.plugins.randomtweaks4.manager;

import me.clip.placeholderapi.PlaceholderAPI;
import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.tools.MCTextUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LangManager {

    private static YamlConfiguration lang;

    public static void setupLangFile(RandomTweaks4 rt4) {
        File file = new File(rt4.getDataFolder(), "lang.yml");
        if (!file.exists()) {
            rt4.saveResource("lang.yml", false);
        }
        lang = YamlConfiguration.loadConfiguration(file);
    }

    public static List<String> getMessageFromLocation(String location) {
        return lang.getStringList(location);
    }
    public static void sendMessage(Player p, List<String> m) {
        for (String message : m) {
            String cen = "<Center>";

            boolean isFound = message.contains(cen);
            if (isFound) {
                String ne = StringUtils.remove(message, cen);
                MCTextUtil.sendCenteredMessage(p, ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(p, ne)));
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(p, message)));
            }
        }
    }
    public static void sendMessage(CommandSender s, List<String> m) {
        for (String message : m) {
                s.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
    public static void sendMessage(List<Player> pl, List<String> m) {
        for (int p = 0; p < pl.size(); p++) {
            sendMessage(pl.get(p), m);

        }
    }
    public static String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', getMessageFromLocation("prefix").get(0));
    }
    public static void sendMessage(Player p, String location) {
        sendMessage(p, getMessageFromLocation(location));
    }
    public static void sendMessage(CommandSender s, String location) {
        sendMessage(s, getMessageFromLocation(location));
    }

    public static List<String> replace(List<String> m, String placeholder, String result) {
        List<String> ret = new ArrayList<>();
        for (String r : m) {
            ret.add(r.replace(placeholder, result));
        }
        return ret;
    }
}
