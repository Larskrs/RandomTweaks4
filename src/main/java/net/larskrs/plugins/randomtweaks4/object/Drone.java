package net.larskrs.plugins.randomtweaks4.object;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.TraitInfo;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.Gravity;
import net.larskrs.plugins.randomtweaks4.manager.DataFileManager;
import net.larskrs.plugins.randomtweaks4.manager.DroneManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.UUID;

public class Drone {
    public UUID owner;
    public NPC npc;

    public Drone(UUID uuid) {
        this.owner = uuid;
    }

    public void clearNpc() {
        this.npc.destroy();
    }

    public void spawnNpc() {
        Player p = Bukkit.getPlayer(this.owner);
        this.npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, p.getName());
        this.npc.getOrAddTrait(Gravity.class).gravitate(true);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, DataFileManager.getConfig().getItemStack("player-data."+this.owner+".drone.inventory."+36));
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, DataFileManager.getConfig().getItemStack("player-data."+this.owner+".drone.inventory."+37));
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, DataFileManager.getConfig().getItemStack("player-data."+this.owner+".drone.inventory."+38));
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, DataFileManager.getConfig().getItemStack("player-data."+this.owner+".drone.inventory."+39));
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.OFF_HAND, DataFileManager.getConfig().getItemStack("player-data."+this.owner+".drone.inventory."+40));

        this.npc.spawn(Bukkit.getPlayer(this.owner).getLocation());

        List<Integer> idList = DataFileManager.getConfig().getIntegerList("garbage.npc");
        idList.add(this.npc.getId());
        DataFileManager.getConfig().set("garbage.npc", idList);
        DataFileManager.saveFile();
        DataFileManager.reloadFile();

    }

}
