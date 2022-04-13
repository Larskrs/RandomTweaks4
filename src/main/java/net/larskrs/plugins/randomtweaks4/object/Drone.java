package net.larskrs.plugins.randomtweaks4.object;

import com.cryptomorin.xseries.XSound;
import com.cryptomorin.xseries.messages.ActionBar;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.MemoryNPCDataStore;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.TraitInfo;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.Gravity;
import net.citizensnpcs.trait.HologramTrait;
import net.citizensnpcs.trait.text.Text;
import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.manager.DataFileManager;
import net.larskrs.plugins.randomtweaks4.manager.DroneManager;
import net.larskrs.plugins.randomtweaks4.manager.LangManager;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class Drone extends BukkitRunnable {
    public UUID owner;
    public NPC npc;

    public Drone(UUID uuid) {
        this.owner = uuid;
        Bukkit.getPlayer(owner).playSound(Bukkit.getPlayer(owner).getLocation(), XSound.ENTITY_BAT_AMBIENT.parseSound(), 1, 1);
        this.runTaskTimer(RandomTweaks4.getInstance(), 0, 20*2);

    }

    public void clearNpc() {
        this.npc.destroy();
    }

    public void spawnNpc() {

        final NPCRegistry registery = CitizensAPI.createAnonymousNPCRegistry(new MemoryNPCDataStore());
        Player p = Bukkit.getPlayer(this.owner);
        this.npc = registery.createNPC(EntityType.PLAYER, p.getName());
        this.npc.getOrAddTrait(Gravity.class).gravitate(true);
        this.npc.getOrAddTrait(HologramTrait.class).onDespawn();

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, DataFileManager.getConfig().getItemStack("player-data."+this.owner+".drone.inventory."+36));
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, DataFileManager.getConfig().getItemStack("player-data."+this.owner+".drone.inventory."+37));
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, DataFileManager.getConfig().getItemStack("player-data."+this.owner+".drone.inventory."+38));
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, DataFileManager.getConfig().getItemStack("player-data."+this.owner+".drone.inventory."+39));
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.OFF_HAND, DataFileManager.getConfig().getItemStack("player-data."+this.owner+".drone.inventory."+40));

        this.npc.spawn(Bukkit.getPlayer(this.owner).getLocation());
        this.npc.getEntity().setCustomName(p.getName());
        this.npc.getEntity().setCustomNameVisible(false);

        List<Integer> idList = DataFileManager.getConfig().getIntegerList("garbage.npc");
        idList.add(this.npc.getId());
        DataFileManager.getConfig().set("garbage.npc", idList);
        DataFileManager.saveFile();
        DataFileManager.reloadFile();

    }
    public void clear(){
        if (RandomTweaks4.getInstance().hasCitizens()) {
            clearNpc();
        }
        cancel();
        Bukkit.getPlayer(owner).stopSound(XSound.ENTITY_BEE_LOOP.parseSound());
        Bukkit.getPlayer(owner).playSound(Bukkit.getPlayer(owner).getLocation(), XSound.ENTITY_BAT_AMBIENT.parseSound(), 1, 1);
    }


    @Override
    public void run() {
        Player p = Bukkit.getPlayer(this.owner);
        p.playSound(p.getLocation(), XSound.ENTITY_BEE_LOOP.parseSound(), 500, 1);
        ActionBar.sendActionBar(p, LangManager.getSingleMessageFromLocation("drone-module.drone-activated-lower"));
    }
}
