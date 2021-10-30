package terthesz.dababy.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
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
import terthesz.dababy.mod.items.CustomMusicDisc;
import terthesz.dababy.mod.tools.CustomAxeItem;
import terthesz.dababy.mod.tools.CustomHoeItem;
import terthesz.dababy.mod.tools.CustomPickaxeItem;
import terthesz.dababy.mod.tools.DababyToolMaterial;
import terthesz.dababy.mod.tools.MoneyToolMaterial;
import terthesz.dababy.mod.villager.CustomVillagerProfession;

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

	public static final Item DABABY_INGOT = new Item(new FabricItemSettings().group(DABABY_GROUP).rarity(Rarity.RARE));

	public static final Item DROP_OF_SWAG = new Item(new FabricItemSettings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));
	public static final Item SWAG_CAPSULE = new Item(new FabricItemSettings().group(DABABY_GROUP));
	public static final Item FULL_SWAG_CAPSULE = new Item(new FabricItemSettings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));
	public static final Item COMPRESSED_FULL_SWAG_CAPSULE = new Item(new FabricItemSettings().group(DABABY_GROUP).rarity(Rarity.RARE));

	// Blocks
	// TODO: sounds
	public static final Block COIN_ORE = new Block(FabricBlockSettings.of(Material.METAL).breakByHand(false).strength(3.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
	
	public static final Block DABABY_ORE = new Block(FabricBlockSettings.of(Material.METAL).breakByHand(false).strength(5.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

	// TODO: Make it reinforced
	public static final Block REINFORCED_GLASS = new Block(FabricBlockSettings.of(Material.GLASS).nonOpaque().hardness(2.0F).resistance(1200.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

	public static final Block DABABY_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).breakByHand(false).strength(5.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

	// Tools
	public static ToolItem MONEY_SHOVEL = new ShovelItem(MoneyToolMaterial.INSTANCE, -0.5F, -3, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));
	public static ToolItem MONEY_SWORD = new SwordItem(MoneyToolMaterial.INSTANCE, 1, -2.4F, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));

	public static ToolItem MONEY_PICKAXE = new CustomPickaxeItem(MoneyToolMaterial.INSTANCE, -1, -2.8F, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));
	public static ToolItem MONEY_AXE = new CustomAxeItem(MoneyToolMaterial.INSTANCE, 4, -3.1F, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));
	public static ToolItem MONEY_HOE = new CustomHoeItem(MoneyToolMaterial.INSTANCE, -4, -1, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.UNCOMMON));


	public static ToolItem DABABY_SHOVEL = new ShovelItem(DababyToolMaterial.INSTANCE, 0.5F, -3, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.RARE));
	public static ToolItem DABABY_SWORD = new SwordItem(DababyToolMaterial.INSTANCE, 2, -2.4F, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.RARE));

	public static ToolItem DABABY_PICKAXE = new CustomPickaxeItem(DababyToolMaterial.INSTANCE, 0, -2.8F, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.RARE));
	public static ToolItem DABABY_AXE = new CustomAxeItem(DababyToolMaterial.INSTANCE, 4, -3.1F, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.RARE));
	public static ToolItem DABABY_HOE = new CustomHoeItem(DababyToolMaterial.INSTANCE, -4, -1, new Item.Settings().group(DABABY_GROUP).rarity(Rarity.RARE));

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

	public static final ConfiguredFeature<?, ?> DABABY_ORE_GENERATION = Feature.ORE
		.configure(new OreFeatureConfig(
			OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
			DABABY_ORE.getDefaultState(),
			5))
		.range(new RangeDecoratorConfig(
			UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(20))))
		.spreadHorizontally()
		.repeat(15);

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

	// Sounds
	public static final SoundEvent AMONG_US_SOUND = new SoundEvent(new Identifier("damod", "among_us_sound"));
	public static final SoundEvent BURGER_KING_SOUND = new SoundEvent(new Identifier("damod", "burger_king_sound"));
	public static final SoundEvent BZUM_BZUM_SOUND = new SoundEvent(new Identifier("damod", "bzum_bzum_sound"));
	public static final SoundEvent GAGGING_CAT_SOUND = new SoundEvent(new Identifier("damod", "gagging_cat_sound"));
	public static final SoundEvent LETS_GO_SOUND = new SoundEvent(new Identifier("damod", "lets_go_sound"));
	public static final SoundEvent PULL_UP_SOUND = new SoundEvent(new Identifier("damod", "pull_up_sound"));
	public static final SoundEvent RICK_ROLL_SOUND = new SoundEvent(new Identifier("damod", "rick_roll_sound"));

	// Discs
	public static final Item AMONG_US_DISC = new CustomMusicDisc(14, AMONG_US_SOUND, new FabricItemSettings().rarity(Rarity.EPIC).group(DABABY_GROUP).maxCount(1));
	public static final Item BURGER_KING_DISC = new CustomMusicDisc(14, BURGER_KING_SOUND, new FabricItemSettings().rarity(Rarity.EPIC).group(DABABY_GROUP).maxCount(1));
	public static final Item BZUM_BZUM_DISC = new CustomMusicDisc(14, BZUM_BZUM_SOUND, new FabricItemSettings().rarity(Rarity.EPIC).group(DABABY_GROUP).maxCount(1));
	public static final Item GAGGING_CAT_DISC = new CustomMusicDisc(14, GAGGING_CAT_SOUND, new FabricItemSettings().rarity(Rarity.EPIC).group(DABABY_GROUP).maxCount(1));
	public static final Item LETS_GO_DISC = new CustomMusicDisc(14, LETS_GO_SOUND, new FabricItemSettings().rarity(Rarity.EPIC).group(DABABY_GROUP).maxCount(1));
	public static final Item PULL_UP_DISC = new CustomMusicDisc(14, PULL_UP_SOUND, new FabricItemSettings().rarity(Rarity.EPIC).group(DABABY_GROUP).maxCount(1));
	public static final Item RICK_ROLL_DISC = new CustomMusicDisc(14, RICK_ROLL_SOUND, new FabricItemSettings().rarity(Rarity.EPIC).group(DABABY_GROUP).maxCount(1));

	@Override
	public void onInitialize() {
		// Dababy Creative Menu Item
		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_group_item"), DABABY_GROUP_ITEM);

		// Items
		Registry.register(Registry.ITEM, new Identifier("damod", "smooth_plate"), SMOOTH_PLATE);
		Registry.register(Registry.ITEM, new Identifier("damod", "smooth_stick"), SMOOTH_STICK);

		Registry.register(Registry.ITEM, new Identifier("damod", "coin"), COIN);
		Registry.register(Registry.ITEM, new Identifier("damod", "money"), MONEY);

		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_ingot"), DABABY_INGOT);

		Registry.register(Registry.ITEM, new Identifier("damod", "drop_of_swag"), DROP_OF_SWAG);
		Registry.register(Registry.ITEM, new Identifier("damod", "swag_capsule"), SWAG_CAPSULE);
		Registry.register(Registry.ITEM, new Identifier("damod", "full_swag_capsule"), FULL_SWAG_CAPSULE);
		Registry.register(Registry.ITEM, new Identifier("damod", "compressed_full_swag_capsule"), COMPRESSED_FULL_SWAG_CAPSULE);
	
		// Blocks
		Registry.register(Registry.BLOCK, new Identifier("damod", "coin_ore"), COIN_ORE);
		Registry.register(Registry.ITEM, new Identifier("damod", "coin_ore"), new BlockItem(COIN_ORE, new FabricItemSettings().group(DABABY_GROUP)));

		Registry.register(Registry.BLOCK, new Identifier("damod", "dababy_ore"), DABABY_ORE);
		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_ore"), new BlockItem(DABABY_ORE, new FabricItemSettings().group(DABABY_GROUP)));

		Registry.register(Registry.BLOCK, new Identifier("damod", "reinforced_glass"), REINFORCED_GLASS);
		Registry.register(Registry.ITEM, new Identifier("damod", "reinforced_glass"), new BlockItem(REINFORCED_GLASS, new FabricItemSettings().group(DABABY_GROUP)));

		Registry.register(Registry.BLOCK, new Identifier("damod", "dababy_block"), DABABY_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_block"), new BlockItem(DABABY_BLOCK, new FabricItemSettings().group(DABABY_GROUP).rarity(Rarity.RARE)));

		// World Generation
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("damod", "coin_ore_generation"), COIN_ORE_GENERATION);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("damod", "dababy_ore_generation"), DABABY_ORE_GENERATION);

		// Coin Tree
		RegistryKey<ConfiguredFeature<?, ?>> MONEY_TREE_RK = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("damod", "money_tree"));
  	Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MONEY_TREE_RK.getValue(), MONEY_TREE);

		Registry.register(Registry.BLOCK, new Identifier("damod", "coin_sapling"), COIN_SAPLING);
  	Registry.register(Registry.ITEM, new Identifier("damod", "coin_sapling"), new BlockItem(COIN_SAPLING, new FabricItemSettings().group(DABABY_GROUP)));

		Registry.register(Registry.BLOCK, new Identifier("damod", "money_leaves"), MONEY_LEAVES);
		Registry.register(Registry.ITEM, new Identifier("damod", "money_leaves"), new BlockItem(MONEY_LEAVES, new FabricItemSettings().group(DABABY_GROUP)));
				
		// Tools
		Registry.register(Registry.ITEM, new Identifier("damod", "money_shovel"), MONEY_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier("damod", "money_sword"), MONEY_SWORD);
		Registry.register(Registry.ITEM, new Identifier("damod", "money_pickaxe"), MONEY_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("damod", "money_axe"), MONEY_AXE);
		Registry.register(Registry.ITEM, new Identifier("damod", "money_hoe"), MONEY_HOE);
		
		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_shovel"), DABABY_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_sword"), DABABY_SWORD);
		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_pickaxe"), DABABY_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_axe"), DABABY_AXE);
		Registry.register(Registry.ITEM, new Identifier("damod", "dababy_hoe"), DABABY_HOE);
	
		// DaBaby
		Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("damod", "dababy"), CustomVillagerProfession.DABABY);
		CustomVillagerProfession.addTrades();

		// Sounds
		Registry.register(Registry.SOUND_EVENT, new Identifier("damod", "among_us_sound"), AMONG_US_SOUND);
		Registry.register(Registry.SOUND_EVENT, new Identifier("damod", "burger_king_sound"), BURGER_KING_SOUND);
		Registry.register(Registry.SOUND_EVENT, new Identifier("damod", "bzum_bzum_sound"), BZUM_BZUM_SOUND);
		Registry.register(Registry.SOUND_EVENT, new Identifier("damod", "gagging_cat_sound"), GAGGING_CAT_SOUND);
		Registry.register(Registry.SOUND_EVENT, new Identifier("damod", "lets_go_sound"), LETS_GO_SOUND);
		Registry.register(Registry.SOUND_EVENT, new Identifier("damod", "pull_up_sound"), PULL_UP_SOUND);
		Registry.register(Registry.SOUND_EVENT, new Identifier("damod", "rick_roll_sound"), RICK_ROLL_SOUND);
	
		// Discs
		Registry.register(Registry.ITEM, new Identifier("damod", "music_disc_among_us"), AMONG_US_DISC);
		Registry.register(Registry.ITEM, new Identifier("damod", "music_disc_burger_king"), BURGER_KING_DISC);
		Registry.register(Registry.ITEM, new Identifier("damod", "music_disc_bzum_bzum"), BZUM_BZUM_DISC);
		Registry.register(Registry.ITEM, new Identifier("damod", "music_disc_gagging_cat"), GAGGING_CAT_DISC);
		Registry.register(Registry.ITEM, new Identifier("damod", "music_disc_lets_go"), LETS_GO_DISC);
		Registry.register(Registry.ITEM, new Identifier("damod", "music_disc_pull_up"), PULL_UP_DISC);
		Registry.register(Registry.ITEM, new Identifier("damod", "music_disc_rick_roll"), RICK_ROLL_DISC);
	}
}