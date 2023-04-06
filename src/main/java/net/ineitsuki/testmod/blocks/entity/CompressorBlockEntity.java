package net.ineitsuki.testmod.blocks.entity;

import net.ineitsuki.testmod.items.ModItems;
import net.ineitsuki.testmod.screen.CompressorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompressorBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(9){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public CompressorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COMPRESSOR.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CompressorBlockEntity.this.progress;
                    case 1 -> CompressorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CompressorBlockEntity.this.progress = value;
                    case 1 -> CompressorBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Compressor");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new CompressorMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static <E extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, CompressorBlockEntity Entity) {
        if (level.isClientSide()) {
            return;
        }

        if (hasRecipe(Entity)) {
            Entity.progress++;
            setChanged(level, pos, state);

            if (Entity.progress >= Entity.maxProgress) {
                craftItem(Entity);
            }
        } else {
            Entity.resetProgress();
            setChanged(level, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(CompressorBlockEntity Entity) {
        if (hasRecipe(Entity)) {
            Entity.itemHandler.extractItem(0,1,false);
            Entity.itemHandler.extractItem(1,1,false);
            Entity.itemHandler.extractItem(2,1,false);

            Entity.itemHandler.setStackInSlot(9, new ItemStack(ModItems.COMPRESSED_OSMIUM.get(),
                    Entity.itemHandler.getStackInSlot(9).getCount() + 1));

            Entity.resetProgress();
        }
    }

    private static boolean hasRecipe(CompressorBlockEntity Entity) {
        SimpleContainer inventory = new SimpleContainer(Entity.itemHandler.getSlots());
        for (int i = 0; i < Entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, Entity.itemHandler.getStackInSlot(i));
        }

        boolean hasOsmiumInFirstSlot = Entity.itemHandler.getStackInSlot(0).getItem() == ModItems.OSMIUM_INGOT.get();
        boolean hasOsmiumInSecondSlot = Entity.itemHandler.getStackInSlot(1).getItem() == ModItems.OSMIUM_INGOT.get();
        boolean hasOsmiumInThirdSlot = Entity.itemHandler.getStackInSlot(2).getItem() == ModItems.OSMIUM_INGOT.get();

        return hasOsmiumInFirstSlot && hasOsmiumInSecondSlot && hasOsmiumInThirdSlot && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, new ItemStack(ModItems.COMPRESSED_OSMIUM.get(), 1));

    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }
}
