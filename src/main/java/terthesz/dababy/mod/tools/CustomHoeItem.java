package terthesz.dababy.mod.tools;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class CustomHoeItem extends AxeItem {
  public CustomHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
      super(material, attackDamage, attackSpeed, settings);
  }
}