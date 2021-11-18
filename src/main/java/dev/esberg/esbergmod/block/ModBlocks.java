package dev.esberg.esbergmod.block;

import dev.esberg.esbergmod.EsbergMod;
import dev.esberg.esbergmod.block.custom.CoffeeBrewerBlock;
import dev.esberg.esbergmod.block.custom.trees.GreenwoodTree;
import dev.esberg.esbergmod.item.ModCreativeModeTab;
import dev.esberg.esbergmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.predicate.BlockMaterialPredicate;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EsbergMod.MOD_ID);

    public static final RegistryObject<Block> GREENWOOD_LOG = registerBlock("greenwood_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> GREENWOOD_WOOD = registerBlock("greenwood_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(1f)));

    public static final RegistryObject<Block> STRIPPED_GREENWOOD_LOG = registerBlock("stripped_greenwood_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(1f)));

    public static final RegistryObject<Block> STRIPPED_GREENWOOD_WOOD = registerBlock("stripped_greenwood_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(1f)));

    public static final RegistryObject<Block> GREENWOOD_PLANKS = registerBlock("greenwood_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> GREENWOOD_LEAVES = registerBlock("greenwood_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> GREENWOOD_SAPLING = registerBlock("greenwood_sapling",
            () -> new SaplingBlock(new GreenwoodTree(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> COFFEE_BREWER = registerBlock("coffee_brewer",
            () -> new CoffeeBrewerBlock(BlockBehaviour.Properties.of(Material.METAL).strength(0.5f)));



    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModCreativeModeTab.ESBERG_MOD_TAB)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
