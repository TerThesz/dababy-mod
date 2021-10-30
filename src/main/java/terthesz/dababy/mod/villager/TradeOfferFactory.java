package terthesz.dababy.mod.villager;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

public class TradeOfferFactory {
  static class SellItem implements TradeOffers.Factory {
    private final Item item;
    private final int price;
    private final int maxUses;
    private final int experience;
    private final float multiplier;
    private final int count;
    private final Item pay_item;

    public SellItem(ItemConvertible item, int count, ItemConvertible pay_item, int price, int maxUses, int experience) {
      this.item = item.asItem();
      this.price = price;
      this.count = count;
      this.maxUses = maxUses;
      this.experience = experience;
      this.multiplier = 0.05F;
      this.pay_item = pay_item.asItem();
    }

    public TradeOffer create(Entity entity, Random random) {
      ItemStack itemStack = new ItemStack(this.item, this.count);
      return new TradeOffer(itemStack, new ItemStack(pay_item, price), this.maxUses, this.experience, this.multiplier);
    }
  }

  public static class BuyItem implements TradeOffers.Factory {
    private final Item item;
    private final int price;
    private final int maxUses;
    private final int experience;
    private final float multiplier;
    private final int count;
    private final Item pay_item;

    public BuyItem(ItemConvertible item, int count, ItemConvertible pay_item, int price, int maxUses, int experience) {
      this.item = item.asItem();
      this.price = price;
      this.count = count;
      this.maxUses = maxUses;
      this.experience = experience;
      this.multiplier = 0.05F;
      this.pay_item = pay_item.asItem();
    }

    public TradeOffer create(Entity entity, Random random) {
      ItemStack itemStack = new ItemStack(this.item, this.count);
      return new TradeOffer(new ItemStack(pay_item, price), itemStack, this.maxUses, this.experience, this.multiplier);
    }
  }
}