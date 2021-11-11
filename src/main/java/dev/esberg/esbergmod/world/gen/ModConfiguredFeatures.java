package dev.esberg.esbergmod.world.gen;

import dev.esberg.esbergmod.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<TreeConfiguration, ?> GREENWOOD =
            register("greenwood", Feature.TREE.configured((
                    new TreeConfiguration.TreeConfigurationBuilder(
                            new SimpleStateProvider(ModBlocks.GREENWOOD_LOG.get().defaultBlockState()),
                            new StraightTrunkPlacer(5, 2, 0),
                            new SimpleStateProvider(ModBlocks.GREENWOOD_LEAVES.get().defaultBlockState()),
                            new SimpleStateProvider(ModBlocks.GREENWOOD_SAPLING.get().defaultBlockState()),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                            new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));


    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String pId, ConfiguredFeature<FC, ?> pConfiguredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, pId, pConfiguredFeature);
    }

}
