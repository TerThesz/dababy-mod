package terthesz.dababy.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import terthesz.dababy.mod.blocks.CustomSaplingBlock;
import terthesz.dababy.mod.features.CustomSaplingFeature;
import terthesz.dababy.mod.tools.CustomAxeItem;
import terthesz.dababy.mod.tools.CustomHoeItem;
import terthesz.dababy.mod.tools.CustomPickaxeItem;
import terthesz.dababy.mod.tools.MoneyToolMaterial;

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

	public static final Item MONEY = new Item(new FabricItemSettings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));

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

	// Coin Tree
	public static final Block MONEY_LEAVES = new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).breakByTool(FabricToolTags.HOES).ticksRandomly().sounds(BlockSoundGroup.GRASS).strength(0.2f, 0.2f));

	public static final ConfiguredFeature<?, ?> MONEY_TREE = Feature.TREE
  .configure(new TreeFeatureConfig.Builder(
    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
    new StraightTrunkPlacer(4, 3, 0),
    new SimpleBlockStateProvider(MONEY_LEAVES.getDefaultState()),
    new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()),
    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
    new TwoLayersFeatureSize(1, 0, 1)
	).ignoreVines().build());

	public static final CustomSaplingBlock COIN_SAPLING = new CustomSaplingBlock(new CustomSaplingFeature(MONEY_TREE), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING));


	// Tools
	public static ToolItem MONEY_SHOVEL = new ShovelItem(MoneyToolMaterial.INSTANCE, -0.5F, -3, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));
	public static ToolItem MONEY_SWORD = new SwordItem(MoneyToolMaterial.INSTANCE, 1, -2.4F, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));

	public static ToolItem MONEY_PICKAXE = new CustomPickaxeItem(MoneyToolMaterial.INSTANCE, -1, -2.8F, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));
	public static ToolItem MONEY_AXE = new CustomAxeItem(MoneyToolMaterial.INSTANCE, 4, -3.1F, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));
	public static ToolItem MONEY_HOE = new CustomHoeItem(MoneyToolMaterial.INSTANCE, -4, -1, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));

	@Override
	public void onInitialize() {
		// Dababy Creative Menu Item
		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_group_item"), DABABY_GROUP_ITEM);

		// Items
		Registry.register(Registry.ITEM, new Identifier("damod", "smooth_plate"), SMOOTH_PLATE);
		Registry.register(Registry.ITEM, new Identifier("damod", "smooth_stick"), SMOOTH_STICK);

		Registry.register(Registry.ITEM, new Identifier("damod", "coin"), COIN);

		Registry.register(Registry.ITEM, new Identifier("damod", "money"), MONEY);
	
		// Blocks
		Registry.register(Registry.BLOCK, new Identifier("damod", "coin_ore"), COIN_ORE);
		Registry.register(Registry.ITEM, new Identifier("damod", "coin_ore"), new BlockItem(COIN_ORE, new FabricItemSettings().group(DABABY_GROUP)));

		// World Generation
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("damod", "coin_ore_generation"), COIN_ORE_GENERATION);

		// Coin Tree
		RegistryKey<ConfiguredFeature<?, ?>> MONEY_TREE_RK = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("damod", "money_tree"));
  	Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MONEY_TREE_RK.getValue(), MONEY_TREE);

		Registry.register(Registry.BLOCK, new Identifier("damod", "coin_sapling"), COIN_SAPLING);
  	Registry.register(Registry.ITEM, new Identifier("damod", "coin_sapling"), new BlockItem(COIN_SAPLING, new FabricItemSettings().group(DABABY_GROUP)));

		Registry.register(Registry.BLOCK, new Identifier("damod", "money_leaves"), MONEY_LEAVES);
		Registry.register(Registry.ITEM, new Identifier("damod", "money_leaves"), new BlockItem(MONEY_LEAVES, new FabricItemSettings().group(DABABY_GROUP)));

		BlockRenderLayerMap.INSTANCE.putBlock(COIN_SAPLING, RenderLayer.getCutout());
	
		// Tools
		Registry.register(Registry.ITEM, new Identifier("damod", "money_shovel"), MONEY_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier("damod", "money_sword"), MONEY_SWORD);
		Registry.register(Registry.ITEM, new Identifier("damod", "money_pickaxe"), MONEY_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("damod", "money_axe"), MONEY_AXE);
		Registry.register(Registry.ITEM, new Identifier("damod", "money_hoe"), MONEY_HOE);
	}
}