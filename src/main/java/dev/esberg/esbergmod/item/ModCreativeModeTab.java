package dev.esberg.esbergmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab ESBERG_MOD_TAB = new CreativeModeTab("esbergModTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.COFFEE_CUP.get());
        }
    };
}
