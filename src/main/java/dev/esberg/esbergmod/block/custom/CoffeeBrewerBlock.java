package dev.esberg.esbergmod.block.custom;

import dev.esberg.esbergmod.block.ModBlocks;
import dev.esberg.esbergmod.container.CoffeeBrewerContainer;
import dev.esberg.esbergmod.tileentity.CoffeeBrewerTile;
import dev.esberg.esbergmod.tileentity.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import javax.annotation.Nullable;

public class CoffeeBrewerBlock extends Block implements EntityBlock {
    public CoffeeBrewerBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModTileEntities.COFFEE_BREWER_TILE.get().create(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if(!player.isCrouching()) {
                if(blockEntity instanceof CoffeeBrewerTile) {
                    MenuProvider containerProvider = createContainerProvider(level, pos);

                    NetworkHooks.openGui(((ServerPlayer) player), containerProvider, blockEntity.getBlockPos());
                } else {
                    throw new IllegalStateException("Our container provider is missing!");
                }
            } else {
                if(blockEntity instanceof CoffeeBrewerTile) {
                    ((CoffeeBrewerTile) blockEntity).brewCoffee();
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    private MenuProvider createContainerProvider(Level level, BlockPos pos) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return new TranslatableComponent("screen.esbergmod.coffee_brewer");
            }

            @Override
            public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
                return new CoffeeBrewerContainer(pContainerId, level, pos, pInventory, pPlayer);
            }
        };
    }
}
