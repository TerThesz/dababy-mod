package terthesz.dababy.mod.tools;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import terthesz.dababy.mod.DababyMod;

public class MoneyToolMaterial implements ToolMaterial {
  public static final MoneyToolMaterial INSTANCE = new MoneyToolMaterial();

  @Override
  public int getDurability() {
      return 250;
  }

  @Override
  public float getMiningSpeedMultiplier() {
      return 5.0F;
  }

  @Override
  public float getAttackDamage() {
      return 4.0F;
  }

  @Override
  public int getMiningLevel() {
      return 2;
  }

  @Override
  public int getEnchantability() {
      return 15;
  }

  @Override
  public Ingredient getRepairIngredient() {
      return Ingredient.ofItems(DababyMod.MONEY);
  }
}