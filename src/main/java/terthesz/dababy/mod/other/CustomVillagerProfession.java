package terthesz.dababy.mod.other;
import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class CustomVillagerProfession {
  public static final PointOfInterestType DABABY_POI = PointOfInterestHelper.register(
    new Identifier("damod", "dababy_poi"),
    1,
    1,
    Blocks.JUKEBOX
  );

  public static final VillagerProfession DABABY = VillagerProfessionBuilder.create()
    .id(new Identifier("damod", "dababy"))
    .workstation(DABABY_POI)
    .build();

  public static final void addTrades() {
    TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(DABABY, copyToFastUtilMap(ImmutableMap.of(1, new TradeOffers.Factory[]{new TradeOfferFactory(Items.WHEAT, 20, 16, 2), new TradeOfferFactory(Items.WHEAT, 20, 16, 2), new TradeOfferFactory(Items.WHEAT, 20, 16, 2)},2, new TradeOffers.Factory[]{new TradeOfferFactory(Items.WHEAT, 20, 16, 2), new TradeOfferFactory(Items.WHEAT, 20, 16, 2), new TradeOfferFactory(Items.WHEAT, 20, 16, 2)},3, new TradeOffers.Factory[]{new TradeOfferFactory(Items.WHEAT, 20, 16, 2), new TradeOfferFactory(Items.WHEAT, 20, 16, 2), new TradeOfferFactory(Items.WHEAT, 20, 16, 2)})));
  }

  private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
		return new Int2ObjectOpenHashMap(map);
	}
}
