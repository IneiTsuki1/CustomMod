package net.ineitsuki.testmod.world.feature;

import net.ineitsuki.testmod.TestMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, TestMod.MODID);

    public static final RegistryObject<PlacedFeature> DEEPSLATE_ALUMINUM_ORE = PLACED_FEATURES.register("deepslate_aluminum_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.DEEPSLATE_ALUMINUM_ORE.getHolder().get(),
                    commonOrePlacement(12, // ore vein placement
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(0)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_TIN_ORE = PLACED_FEATURES.register("deepslate_tin_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.DEEPSLATE_TIN_ORE.getHolder().get(),
                    commonOrePlacement(13, // ore vein placement
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(0)))));

    public static final RegistryObject<PlacedFeature> TIN_ORE = PLACED_FEATURES.register("tin_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.TIN_ORE.getHolder().get(),
                    commonOrePlacement(13, // ore vein placement
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(8), VerticalAnchor.absolute(80)))));

    public static final RegistryObject<PlacedFeature> TITANIUM_ORE = PLACED_FEATURES.register("deepslate_titanium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.DEEPSLATE_TITANIUM_ORE.getHolder().get(),
                    commonOrePlacement(13, // ore vein placement
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(0)))));

    public static final RegistryObject<PlacedFeature> NETHER_COBALT_ORE = PLACED_FEATURES.register("nether_cobalt_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_COBALT_ORE.getHolder().get(),
                    commonOrePlacement(13, // ore vein placement
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-120), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> ENDSTONE_OSMIUM_ORE = PLACED_FEATURES.register("endstone_osmium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ENDSTONE_OSMIUM_ORE.getHolder().get(), commonOrePlacement(7, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));




    //------------------------------------------------------------------------------------------------------------------//
    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }

}
