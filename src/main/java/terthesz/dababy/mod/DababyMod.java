package terthesz.dababy.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class DababyMod implements ModInitializer {
	// Dababy Creative Menu Group
	public static final Item DABABY_GROUP_ITEM = new Item(new FabricItemSettings());
	public static final ItemGroup DABABY_GROUP = FabricItemGroupBuilder.create(
		new Identifier("damod", "dababy_group"))
		.icon(() -> new ItemStack(DABABY_GROUP_ITEM))
		.build();

	// Items
	public static final Item SMOOTH_PLATE = new Item(new FabricItemSettings().group(DABABY_GROUP));
	public static final Item SMOOTH_STICK = new Item(new FabricItemSettings().group(DABABY_GROUP));
	
	public static final Item COIN = new Item(new FabricItemSettings().group(DABABY_GROUP));

	// Blocks
	// TODO: sounds
	public static final Block COIN_ORE = new Block(FabricBlockSettings.of(Material.METAL).breakByHand(false).strength(3.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
	
	// World Generation
	public static final ConfiguredFeature<?, ?> COIN_ORE_GENERATION = Feature.ORE
		.configure(new OreFeatureConfig(
			OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
			COIN_ORE.getDefaultState(),
			5))
		.range(new RangeDecoratorConfig(
			UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(40))))
		.spreadHorizontally()
		.repeat(30);

	@Override
	public void onInitialize() {
		// Dababy Creative Menu Item
		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_group_item"), DABABY_GROUP_ITEM);

		// Items
		Registry.register(Registry.ITEM, new Identifier("damod", "smooth_plate"), SMOOTH_PLATE);
		Registry.register(Registry.ITEM, new Identifier("damod", "smooth_stick"), SMOOTH_STICK);

		Registry.register(Registry.ITEM, new Identifier("damod", "coin"), COIN);
	
		// Blocks
		Registry.register(Registry.BLOCK, new Identifier("damod", "coin_ore"), COIN_ORE);
		Registry.register(Registry.ITEM, new Identifier("damod", "coin_ore"), new BlockItem(COIN_ORE, new FabricItemSettings().group(DABABY_GROUP)));

		// World Generation
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("damod", "coin_ore_generation"), COIN_ORE_GENERATION);
	}
}