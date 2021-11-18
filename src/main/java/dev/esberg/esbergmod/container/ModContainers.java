package dev.esberg.esbergmod.container;

import dev.esberg.esbergmod.EsbergMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static DeferredRegister<MenuType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, EsbergMod.MOD_ID);

    public static final RegistryObject<MenuType<CoffeeBrewerContainer>> COFFEE_BREWER_CONTAINER
            = CONTAINERS.register("coffee_brewer_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level level = inv.player.getCommandSenderWorld();
                return new CoffeeBrewerContainer(windowId, level, pos, inv, inv.player);
            })));

    public static final RegistryObject<MenuType<PatricThePouchContainer>> PATRIC_THE_POUCH_CONTAINER
            = CONTAINERS.register("patric_the_pouch_container",
            () -> IForgeContainerType.create((((windowId, inv, data) -> {
                Level level = inv.player.getCommandSenderWorld();
                return new PatricThePouchContainer(windowId, level, inv, inv.player);
            }))));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }

}
