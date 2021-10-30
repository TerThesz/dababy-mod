package terthesz.dababy.mod.villager;
import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import terthesz.dababy.mod.DababyMod;
import terthesz.dababy.mod.villager.TradeOfferFactory;

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
    TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(DABABY, copyToFastUtilMap(ImmutableMap.of(1, new TradeOffers.Factory[]{new TradeOfferFactory.SellItem(DababyMod.COIN, 4, DababyMod.MONEY, 1, 10, 3), new TradeOfferFactory.SellItem(DababyMod.MONEY, 2, DababyMod.REINFORCED_GLASS, 1, 10, 3)}, 2, new TradeOffers.Factory[]{new TradeOfferFactory.BuyItem(DababyMod.AMONG_US_DISC, 1, DababyMod.FULL_SWAG_CAPSULE, 1, 5, 4), new TradeOfferFactory.BuyItem(DababyMod.BURGER_KING_DISC, 1, DababyMod.FULL_SWAG_CAPSULE, 1, 5, 4)}, 3, new TradeOffers.Factory[]{new TradeOfferFactory.BuyItem(DababyMod.BZUM_BZUM_DISC, 1, DababyMod.FULL_SWAG_CAPSULE, 1, 5, 4), new TradeOfferFactory.BuyItem(DababyMod.GAGGING_CAT_DISC, 1, DababyMod.FULL_SWAG_CAPSULE, 1, 5, 4)}, 4, new TradeOffers.Factory[]{new TradeOfferFactory.BuyItem(DababyMod.LETS_GO_DISC, 1, DababyMod.COMPRESSED_FULL_SWAG_CAPSULE, 1, 5, 4), new TradeOfferFactory.BuyItem(DababyMod.PULL_UP_DISC, 1, DababyMod.COMPRESSED_FULL_SWAG_CAPSULE, 1, 5, 4), new TradeOfferFactory.BuyItem(DababyMod.RICK_ROLL_DISC, 1, DababyMod.COMPRESSED_FULL_SWAG_CAPSULE, 1, 5, 4)})));
  }

  private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
		return new Int2ObjectOpenHashMap(map);
	}
}
