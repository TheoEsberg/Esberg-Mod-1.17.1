package dev.esberg.esbergmod.tileentity;

import dev.esberg.esbergmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class CoffeeBrewerTile extends BlockEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public CoffeeBrewerTile(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    public CoffeeBrewerTile(BlockPos pWorldPosition, BlockState pBlockState) {
        this(ModTileEntities.COFFEE_BREWER_TILE.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void load(CompoundTag pTag) {
        itemHandler.deserializeNBT(pTag.getCompound("inv"));
        super.load(pTag);
    }

    @Override
    public CompoundTag save(CompoundTag pTag) {
        pTag.put("inv", itemHandler.serializeNBT());
        return super.save(pTag);
    }

    private  ItemStackHandler createHandler () {
        return new ItemStackHandler(3){
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0: return stack.getItem() == ModItems.ROASTED_COFFEE_BEANS.get();
                    case 1: return stack.getItem() == ModItems.WATER_CUP.get();
                    case 2: return stack.getItem() == ModItems.COFFEE_CUP.get();
                    default: return false;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap);
    }

    public void brewCoffee() {
        boolean hasBeansInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0 &&
                this.itemHandler.getStackInSlot(0).getItem() == ModItems.ROASTED_COFFEE_BEANS.get();
        boolean hasCupInSecondSlot = this.itemHandler.getStackInSlot(1).getCount() > 0 &&
                this.itemHandler.getStackInSlot(1).getItem() == ModItems.WATER_CUP.get();

        if (hasBeansInFirstSlot && hasCupInSecondSlot) {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(2, new ItemStack(ModItems.COFFEE_CUP.get()), false);
            level.playSound(null, this.getBlockPos(), SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1, 10);
        }
    }
}
