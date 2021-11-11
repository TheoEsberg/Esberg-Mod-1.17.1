package dev.esberg.esbergmod.item;

import dev.esberg.esbergmod.EsbergMod;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EsbergMod.MOD_ID);

    public static final RegistryObject<Item> EMPTY_CUP = ITEMS.register("empty_cup",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));

    public static final RegistryObject<Item> WATER_CUP = ITEMS.register("water_cup",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)
                    .food(new FoodProperties.Builder().alwaysEat().nutrition(1).build())));

    public static final RegistryObject<Item> COFFEE_CUP = ITEMS.register("coffee_cup",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)
                    .food(new FoodProperties.Builder().nutrition(4).saturationMod(2).alwaysEat().build())));

    public static final RegistryObject<Item> ROASTED_COFFEE_BEANS = ITEMS.register("roasted_coffee_beans",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)
                    .food(new FoodProperties.Builder().alwaysEat().nutrition(1).build())));

    //Copper Tools
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModItemTier.COPPER, 2, 5, new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));

    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
            () -> new SwordItem(ModItemTier.COPPER, 2, 5, new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));

    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModItemTier.COPPER, 2, 5, new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));

    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModItemTier.COPPER, 2, 5, new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));

    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModItemTier.COPPER, 2, 5, new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));


    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));

    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));

    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));

    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}