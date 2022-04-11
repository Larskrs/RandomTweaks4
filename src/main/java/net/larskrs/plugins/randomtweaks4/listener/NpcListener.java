package net.larskrs.plugins.randomtweaks4.listener;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCDespawnEvent;
import net.citizensnpcs.api.event.NPCEvent;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import net.larskrs.plugins.randomtweaks4.manager.DataFileManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class NpcListener implements Listener {

    @EventHandler
    public void onSpawn(NPCSpawnEvent e) {

        int id = e.getNPC().getId();
        for (Integer ids : DataFileManager.getConfig().getIntegerList("garbage-npc")) {
                Bukkit.getConsoleSender().sendMessage("found npc.");
        }
        if (DataFileManager.getConfig().getIntegerList("garbage-npc").contains(id)) {
            Bukkit.getConsoleSender().sendMessage("killed npc.");
        } else {
            List<Integer> list = DataFileManager.getConfig().getIntegerList("garbage-npc");
            list.add(id);
            DataFileManager.getConfig().set("garbage.npc", list);
            DataFileManager.saveFile();
            DataFileManager.reloadFile();
        }


    }

}
