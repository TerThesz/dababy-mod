package terthesz.dababy.mod.features;

import java.util.Random;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class CustomSaplingFeature extends SaplingGenerator {
  private final ConfiguredFeature<TreeFeatureConfig, ?> feature;
 
  public CustomSaplingFeature(ConfiguredFeature<?, ?> feature) {
    this.feature = (ConfiguredFeature<TreeFeatureConfig, ?>) feature;
  }
 
  @Nullable
  @Override
  protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
    return feature;
  }
}