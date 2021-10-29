package terthesz.dababy.mod.tools;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import terthesz.dababy.mod.DababyMod;

public class DababyToolMaterial implements ToolMaterial {
  public static final DababyToolMaterial INSTANCE = new DababyToolMaterial();

  @Override
  public int getDurability() {
    return 1500;
  }

  @Override
  public float getMiningSpeedMultiplier() {
    return 8.0F;
  }

  @Override
  public float getAttackDamage() {
    return 4.0F;
  }

  @Override
  public int getMiningLevel() {
    return 3;
  }

  @Override
  public int getEnchantability() {
    return 15;
  }

  @Override
  public Ingredient getRepairIngredient() {
      return Ingredient.ofItems(DababyMod.DABABY_INGOT);
  }
}