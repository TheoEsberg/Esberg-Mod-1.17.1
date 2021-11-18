package dev.esberg.esbergmod.item.custom;

import dev.esberg.esbergmod.container.CoffeeBrewerContainer;
import dev.esberg.esbergmod.container.PatricThePouchContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

public class PatricThePouchItem extends Item {
    public PatricThePouchItem(Properties pProperties) { super(pProperties); }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()){
            MenuProvider containerProvider = createContainerProvider(pContext.getLevel());

            NetworkHooks.openGui(((ServerPlayer) pContext.getPlayer()), containerProvider);
        }
        return super.useOn(pContext);
    }

    private MenuProvider createContainerProvider(Level level) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return new TranslatableComponent("screen.esbergmod.patric_the_pouch");
            }

            @Override
            public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
                return new PatricThePouchContainer(pContainerId, level, pInventory, pPlayer);
            }
        };
    }
}
