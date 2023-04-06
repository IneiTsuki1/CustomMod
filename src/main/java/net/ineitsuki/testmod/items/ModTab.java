package net.ineitsuki.testmod.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModTab {

    public static final CreativeModeTab TAB_TEST_MOD = new CreativeModeTab("testTab") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(Blocks.MANGROVE_PROPAGULE);
            }
            @Override
            public boolean hasSearchBar() {
                return true;
            }
    }.setBackgroundSuffix("item_search.png");
}
