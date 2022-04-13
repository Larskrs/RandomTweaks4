package net.larskrs.plugins.randomtweaks4.modules;

import com.cryptomorin.xseries.XItemStack;
import com.cryptomorin.xseries.XMaterial;
import net.larskrs.plugins.randomtweaks4.RandomTweaks4;
import net.larskrs.plugins.randomtweaks4.command.DroneCommand;
import net.larskrs.plugins.randomtweaks4.command.HatCommand;
import net.larskrs.plugins.randomtweaks4.listener.DroneListener;
import net.larskrs.plugins.randomtweaks4.manager.DroneManager;
import net.larskrs.plugins.randomtweaks4.object.RTModule;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class DroneModule extends RTModule {

    private final RandomTweaks4 rt4;

    public DroneModule(RandomTweaks4 main, String name) {
        super(main, name);
        rt4 = main;
    }

    @Override
    public void Initialize() {
        if (this.getModuleFile().isEnabled()) {
            DroneManager.setUp(rt4);
            new DroneCommand();
            new HatCommand();

        }
    }
    @Override
    public void Reload() {

    }

    public static void registerDroneRecipe(RandomTweaks4 rt4) {
        ItemStack droneItem = new ItemStack(XMaterial.CARROT_ON_A_STICK.parseMaterial());
        ItemMeta droneMeta = droneItem.getItemMeta();
        droneMeta.setDisplayName(ChatColor.AQUA + ChatColor.BOLD.toString() + "Drone");
        droneMeta.setCustomModelData(1001);
        droneItem.setItemMeta(droneMeta);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(rt4, "custom_drone_item"), droneItem);
        recipe.shape(
                "FIF",
                "IRI",
                "FIF"
        );
        recipe.setIngredient('F', XMaterial.FEATHER.parseMaterial());
        recipe.setIngredient('I', XMaterial.IRON_INGOT.parseMaterial());
        recipe.setIngredient('R', XMaterial.REDSTONE.parseMaterial());
        Bukkit.addRecipe(recipe);
    }
}
