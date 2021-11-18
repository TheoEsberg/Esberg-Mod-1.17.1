package dev.esberg.esbergmod.item.custom;

import com.google.common.collect.ImmutableMap;
import dev.esberg.esbergmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Map;

public class EmptyCupItem extends Item {

    private static final Map<Fluid, Item> EMPTY_TO_FILLED_CUP_ITEM =
            new ImmutableMap.Builder<Fluid, Item>()
                    .put(Fluids.WATER, ModItems.WATER_CUP.get()).build();

    public EmptyCupItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()){
            Level level = pContext.getLevel();
            HitResult hitresult = getPlayerPOVHitResult(level, pContext.getPlayer(), ClipContext.Fluid.SOURCE_ONLY);

            if (hitresult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockpos = ((BlockHitResult)hitresult).getBlockPos();
                if (level.getFluidState(blockpos).is(FluidTags.WATER)) {
                    level.playSound(pContext.getPlayer(), pContext.getPlayer().getX(), pContext.getPlayer().getY(), pContext.getPlayer().getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                    ItemEntity itemEntity = new ItemEntity(level, pContext.getPlayer().getX(), pContext.getPlayer().getY(), pContext.getPlayer().getZ(),
                            new ItemStack(ModItems.WATER_CUP.get(), 1));
                    level.addFreshEntity(itemEntity);
                    ItemStack currentItem = pContext.getItemInHand();
                    currentItem.shrink(1);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    private boolean canFill(Fluid fluid){
        return EMPTY_TO_FILLED_CUP_ITEM.containsKey(fluid);
    }
}
