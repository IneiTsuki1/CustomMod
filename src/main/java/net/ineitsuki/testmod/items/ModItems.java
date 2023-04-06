package net.ineitsuki.testmod.items;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.ineitsuki.testmod.TestMod.MODID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    //Ingots//
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",
            () -> new Item(new Item.Properties().tab(ModTab.TAB_TEST_MOD)));

    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties().tab(ModTab.TAB_TEST_MOD)));

    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
            () -> new Item(new Item.Properties().tab(ModTab.TAB_TEST_MOD)));

    public static final RegistryObject<Item> OSMIUM_INGOT = ITEMS.register("osmium_ingot",
            () -> new Item(new Item.Properties().tab(ModTab.TAB_TEST_MOD)));

    //compressed//
    public static final RegistryObject<Item> COMPRESSED_OSMIUM = ITEMS.register("compressed_osmium",
            () -> new Item(new Item.Properties().tab(ModTab.TAB_TEST_MOD)));

    public static final RegistryObject<Item> COMPRESSED_TITANIUM = ITEMS.register("compressed_titanium",
            () -> new Item(new Item.Properties().tab(ModTab.TAB_TEST_MOD)));
    //Tools//
    //titanium//
    public static final RegistryObject<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new SwordItem(Tiers.DIAMOND, 4, -3f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    public static final RegistryObject<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new PickaxeItem(Tiers.DIAMOND, -1, -2.9f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    public static final RegistryObject<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(Tiers.DIAMOND, 4, -2.9f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    public static final RegistryObject<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(Tiers.DIAMOND, -2, -2.9f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    public static final RegistryObject<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(Tiers.DIAMOND, -3, -2.9f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    //Cobalt//

    public static final RegistryObject<Item> COBALT_SWORD = ITEMS.register("cobalt_sword",
            () -> new SwordItem(Tiers.DIAMOND, 7, 6f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    public static final RegistryObject<Item> COBALT_PICKAXE = ITEMS.register("cobalt_pickaxe",
            () -> new PickaxeItem(Tiers.DIAMOND, 1, 3f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    public static final RegistryObject<Item> COBALT_SHOVEL = ITEMS.register("cobalt_shovel",
            () -> new PickaxeItem(Tiers.DIAMOND, -2, -2.7f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    //Osmium

    public static final RegistryObject<Item> OSMIUM_SWORD = ITEMS.register("osmium_sword",
            () -> new SwordItem(Tiers.DIAMOND, 7, -2.5f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));


    public static final RegistryObject<Item> OSMIUM_PICKAXE = ITEMS.register("osmium_pickaxe",
            () -> new PickaxeItem(Tiers.DIAMOND, -1, -2.5f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    public static final RegistryObject<Item> OSMIUM_AXE = ITEMS.register("osmium_axe",
            () -> new SwordItem(Tiers.DIAMOND, 9, -2.5f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    public static final RegistryObject<Item> OSMIUM_SHOVEL = ITEMS.register("osmium_shovel",
            () -> new PickaxeItem(Tiers.DIAMOND, -1, -2.5f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    public static final RegistryObject<Item> OSMIUM_HOE = ITEMS.register("osmium_hoe",
            () -> new PickaxeItem(Tiers.DIAMOND,  -2, -2.5f,
                    new Item.Properties().tab(ModTab.TAB_TEST_MOD).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
