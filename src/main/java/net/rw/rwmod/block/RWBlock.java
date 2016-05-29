package net.rw.rwmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RWBlock extends Block
{
	/* What the block should drop when mined. */
	private Item dropOnHarvest;
	/* How much of the item or block the block should drop. */
	private int dropAmountMax;
	/* How much EXP you get when mining the block. */
	private int maxHarvestEXP = 2;
	
	/* Default constructor should NOT be used. */ 
	public RWBlock(Material material)
	{
		super(material);
	}
	/* Default constructor with map color should NOT be used. */
	public RWBlock(Material material, MapColor mapColor)

	{
		super(material, mapColor);
	}
	/* Custom constructor should be used for normal blocks. */
	public RWBlock(Material material, String name, float hardness, float resistance, SoundType sound, HarvestToolEnum harvestTool, HarvestLevelEnum harvestLevel, CreativeTabs creativeTab, int dropAmountMax)
	{
		super(material);
		this.dropAmountMax = dropAmountMax;
		this.setUnlocalizedName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setSoundType(sound);
		this.setHarvestLevel(harvestTool, harvestLevel);
		this.setCreativeTab(creativeTab);
		this.dropOnHarvest = Item.getItemFromBlock(this);
	}
	/* Custom constructor with map color should be used for normal blocks. */
	public RWBlock(Material material, MapColor mapColor, String name, float hardness, float resistance, SoundType sound, HarvestToolEnum harvestTool, HarvestLevelEnum harvestLevel, CreativeTabs creativeTab, int dropAmountMax)
	{
		super(material, mapColor);
		this.dropAmountMax = dropAmountMax;
		this.setUnlocalizedName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setSoundType(sound);
		this.setHarvestLevel(harvestTool, harvestLevel);
		this.setCreativeTab(creativeTab);
		this.dropOnHarvest = Item.getItemFromBlock(this);
	}
	/* Custom constructor that generates the block in the world. USE ONLY IF THE BLOCK IS SUPPOSED TO BE GENERATED IN THE WORLD. */
	public RWBlock(Material material, String name, float hardness, float resistance, SoundType sound, HarvestToolEnum harvestTool, HarvestLevelEnum harvestLevel, CreativeTabs creativeTab, int dropAmountMax, int minVeinSize, int maxVeinSize, int genChance, int minGenY, int maxGenY, Block[] generateIn, Block[] generateNear, int generateDistance, boolean generateInTheOverworld, boolean generateInTheNether, boolean generateInTheEnd)
	{
		super(material);
		this.dropAmountMax = dropAmountMax;
		this.setUnlocalizedName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setSoundType(sound);
		this.setHarvestLevel(harvestTool, harvestLevel);
		this.setCreativeTab(creativeTab);
		this.dropOnHarvest = Item.getItemFromBlock(this);
		
		GameRegistry.registerWorldGenerator(new RWBlockGen(this, minVeinSize, maxVeinSize, genChance, minGenY, maxGenY, generateIn, generateNear, generateDistance, generateInTheOverworld, generateInTheNether, generateInTheEnd), 1);
	}
 	/* Custom constructor with map color that generates the block in the world. USE ONLY IF THE BLOCK IS SUPPOSED TO BE GENERATED IN THE WORLD. */
	public RWBlock(Material material, MapColor mapColor, String name, float hardness, float resistance, SoundType sound, HarvestToolEnum harvestTool, HarvestLevelEnum harvestLevel, CreativeTabs creativeTab, int dropAmountMax, int minVeinSize, int maxVeinSize, int genChance, int minGenY, int maxGenY, Block[] generateIn, Block[] generateNear, int generateDistance, boolean generateInTheOverworld, boolean generateInTheNether, boolean generateInTheEnd)
	{
		super(material, mapColor);
		this.dropAmountMax = dropAmountMax;
		this.setUnlocalizedName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setSoundType(sound);
		this.setHarvestLevel(harvestTool, harvestLevel);
		this.setCreativeTab(creativeTab);
		this.dropOnHarvest = Item.getItemFromBlock(this);
		
		GameRegistry.registerWorldGenerator(new RWBlockGen(this, minVeinSize, maxVeinSize, genChance, minGenY, maxGenY, generateIn, generateNear, generateDistance, generateInTheOverworld, generateInTheNether, generateInTheEnd), 1);
	}
	/* The same as the method above the method above this method except it doesn't have generate near. */
	public RWBlock(Material material, String name, float hardness, float resistance, SoundType sound, HarvestToolEnum harvestTool, HarvestLevelEnum harvestLevel, CreativeTabs creativeTab, int dropAmountMax, int minVeinSize, int maxVeinSize, int genChance, int minGenY, int maxGenY, Block[] generateIn, int generateDistance, boolean generateInTheOverworld, boolean generateInTheNether, boolean generateInTheEnd)
	{
		this(material, name, hardness, resistance, sound, harvestTool, harvestLevel, creativeTab, dropAmountMax, minVeinSize, maxVeinSize, genChance, minGenY, maxGenY, generateIn, null, 1, generateInTheOverworld, generateInTheNether, generateInTheEnd);
	}
	/* The same as the method above with map color. */
	public RWBlock(Material material, MapColor mapColor, String name, float hardness, float resistance, SoundType sound, HarvestToolEnum harvestTool, HarvestLevelEnum harvestLevel, CreativeTabs creativeTab, int dropAmountMax, int minVeinSize, int maxVeinSize, int genChance, int minGenY, int maxGenY, Block[] generateIn, int generateDistance, boolean generateInTheOverworld, boolean generateInTheNether, boolean generateInTheEnd)
	{
		this(material, name, hardness, resistance, sound, harvestTool, harvestLevel, creativeTab, dropAmountMax, minVeinSize, maxVeinSize, genChance, minGenY, maxGenY, generateIn, null, 1, generateInTheOverworld, generateInTheNether, generateInTheEnd);
	}
	/* The same as the method above the method above this method except it doesn't have generate in*/
	public RWBlock(Material material, String name, float hardness, float resistance, SoundType sound, HarvestToolEnum harvestTool, HarvestLevelEnum harvestLevel, CreativeTabs creativeTab, int dropAmountMax, int minVeinSize, int maxVeinSize, int genChance, int minGenY, int maxGenY, int generateDistance, boolean generateInTheOverworld, boolean generateInTheNether, boolean generateInTheEnd)
	{
		this(material, name, hardness, resistance, sound, harvestTool, harvestLevel, creativeTab, dropAmountMax, minVeinSize, maxVeinSize, genChance, minGenY, maxGenY, new Block[]{Blocks.STONE}, 1, generateInTheOverworld, generateInTheNether, generateInTheEnd);
	}
	/* The same as the method above with map color. */
	public RWBlock(Material material, MapColor mapColor, String name, float hardness, float resistance, SoundType sound, HarvestToolEnum harvestTool, HarvestLevelEnum harvestLevel, CreativeTabs creativeTab, int dropAmountMax, int minVeinSize, int maxVeinSize, int genChance, int minGenY, int maxGenY, int generateDistance, boolean generateInTheOverworld, boolean generateInTheNether, boolean generateInTheEnd)
	{
		this(material, name, hardness, resistance, sound, harvestTool, harvestLevel, creativeTab, dropAmountMax, minVeinSize, maxVeinSize, genChance, minGenY, maxGenY, new Block[]{Blocks.STONE}, 1, generateInTheOverworld, generateInTheNether, generateInTheEnd);
	}
	
	/* Nothing important. */
	public static enum HarvestToolEnum
	{
		PICKAXE,
		SHOVEL,
		AXE,
		SWORD,
		SHEARS;
	}
	/* Nothing important. */
	public static enum HarvestLevelEnum
	{
		WOOD,
		STONE,
		IRON,
		DIAMOND,
		GOLD;
	}
	/* Nothing important. */
	public void setHarvestLevel(HarvestToolEnum harvestTool, HarvestLevelEnum harvestLevel)
	{
		int level;
		String tool;
		
		switch (harvestTool)
		{
			case PICKAXE:
				tool = "pickaxe";
				break;
			case SHOVEL:
				tool = "shovel";
				break;
			case AXE:
				tool = "axe";
				break;
			case SWORD:
				tool = "sword";
				break;
			case SHEARS:
				tool = "shears";
				break;
			default:
				tool = "pickaxe";
		}
		
		switch (harvestLevel)
		{
			case WOOD:
				level = 0;
				break;
			case STONE:
				level = 1;
				break;
			case IRON:
				level = 2;
				break;
			case DIAMOND:
				level = 3;
				break;
			case GOLD:
				level = 0;
				break;
			default:
				level = 0;
		}
		
		super.setHarvestLevel(tool, level);
	}
	/* Sets the max amount EXP you get when you mine the block. */
	public void setMaxHarvestEXP(int expAmount)
	{
		maxHarvestEXP = expAmount;
	}
	/* Sets what ITEM you get when you mine the block. */
	public void setDropItem(Item item)
	{
		this.dropOnHarvest = item;
	}
	/* Sets what BLOCK you get when you mine the block. */
	public void setDropBlock(Block block)
	{
		this.dropOnHarvest = Item.getItemFromBlock(block);
	}
	/* Sets how many blocks or items you get when you mine the block. */
	public void setDropAmountMax(int dropAmount)
	{
		this.dropAmountMax = dropAmount;
	}
	/* Tells the game what the block drops when you mine it. */
	public Item getItemDropped(IBlockState state, Random random, int fortune)
	{
		return this.dropOnHarvest;
	}
	/* How much you get when you break the block. (Choosen randomly) */
	public int quantityDropped(Random random)
	{
		int amount = random.nextInt(this.dropAmountMax);
		return amount;
	}
	/* Sets how much EXP you get when you mine the block. */
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
	{
		IBlockState state1 = world.getBlockState(pos);
		Random random = world instanceof World ? ((World) world).rand : new Random();
		if (this.getItemDropped(state1, random, fortune) != Item.getItemFromBlock(this))
		{
			return MathHelper.getRandomIntegerInRange(random, 0, maxHarvestEXP);
		}
		
		return 0;
	}
}