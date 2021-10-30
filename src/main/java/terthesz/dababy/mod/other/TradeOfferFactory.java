package terthesz.dababy.mod.other;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

public class TradeOfferFactory implements TradeOffers.Factory {
  private final Item buy;
  private final int price;
  private final int maxUses;
  private final int experience;
  private final float multiplier;

  public TradeOfferFactory(ItemConvertible item, int price, int maxUses, int experience) {
    this.buy = item.asItem();
    this.price = price;
    this.maxUses = maxUses;
    this.experience = experience;
    this.multiplier = 0.05F;
  }

  public TradeOffer create(Entity entity, Random random) {
    ItemStack itemStack = new ItemStack(this.buy, this.price);
    return new TradeOffer(itemStack, new ItemStack(Items.EMERALD), this.maxUses, this.experience, this.multiplier);
  }
}