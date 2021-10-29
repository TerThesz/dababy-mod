package terthesz.dababy.mod.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableModifications {
  private static final List<Identifier> identifiers = Arrays.asList(
    EntityType.CAVE_SPIDER.getLootTableId(),
    EntityType.SPIDER.getLootTableId(),
    EntityType.ZOMBIE.getLootTableId(),
    EntityType.SKELETON.getLootTableId(),
    EntityType.CREEPER.getLootTableId()
  );
  
  public void modify() {
    LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
      if (identifiers.contains(id)) {
          FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                  .rolls(ConstantLootNumberProvider.create(1))
                  .with(ItemEntry.builder(Items.EGG));
   
          table.pool(poolBuilder);
      }
    });
  }
}
