package terthesz.dababy.mod.blocks;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class CustomSaplingBlock extends SaplingBlock {
  public CustomSaplingBlock(SaplingGenerator generator, Settings settings) {
    super(generator, settings);
  }
}