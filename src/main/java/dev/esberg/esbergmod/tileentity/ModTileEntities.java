package dev.esberg.esbergmod.tileentity;

import dev.esberg.esbergmod.EsbergMod;
import dev.esberg.esbergmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {
    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, EsbergMod.MOD_ID);

    public static RegistryObject<BlockEntityType<CoffeeBrewerTile>> COFFEE_BREWER_TILE =
            BLOCK_ENTITES.register("coffee_brewer_tile", () -> BlockEntityType.Builder.of(
                    CoffeeBrewerTile::new, ModBlocks.COFFEE_BREWER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITES.register(eventBus);
    }
}
