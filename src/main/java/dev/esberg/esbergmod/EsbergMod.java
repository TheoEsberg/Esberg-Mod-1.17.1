package dev.esberg.esbergmod;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.platform.ScreenManager;
import dev.esberg.esbergmod.block.ModBlocks;
import dev.esberg.esbergmod.container.ModContainers;
import dev.esberg.esbergmod.item.ModItems;
import dev.esberg.esbergmod.screen.CoffeeBrewerScreen;
import dev.esberg.esbergmod.screen.PatricThePouchScreen;
import dev.esberg.esbergmod.tileentity.ModTileEntities;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.ImmutableDescriptor;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EsbergMod.MOD_ID)
public class EsbergMod
{
    public static final String MOD_ID = "esbergmod";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public EsbergMod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREENWOOD_SAPLING.get(), RenderType.cutout());
        MenuScreens.register(ModContainers.COFFEE_BREWER_CONTAINER.get(),
                CoffeeBrewerScreen::new);

        MenuScreens.register(ModContainers.PATRIC_THE_POUCH_CONTAINER.get(),
                PatricThePouchScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                    .put(ModBlocks.GREENWOOD_LOG.get(), ModBlocks.STRIPPED_GREENWOOD_LOG.get())
                    .put(ModBlocks.GREENWOOD_WOOD.get(), ModBlocks.STRIPPED_GREENWOOD_WOOD.get()).build();
        });
    }
}
