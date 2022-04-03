package net.larskrs.plugins.randomtweaks4.tools;

import org.bukkit.ChatColor;

public class ChatColorTool {

    public static ChatColor getBooleanColor(Boolean bool) {
        return (bool) ? ChatColor.GREEN : ChatColor.RED;
    }

}
