package net.rw.rwmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.rw.rwmod.world.RWWorldGenMinable;

public class RWBlockGen implements IWorldGenerator
{
	/* The block that is going to be generated in the world. */
	private RWBlock genBlock;
	/* The minimum amount of blocks in one vein. */
	private int minVeinSize;
	/* The maximum amount of blocks in one vein. */
	private int maxVeinSize;
	/* The chance that the block spawns in the world. */
	private int genChance;
	/* The minimum height the block spawns in. Cannot be under 1. */
	private int genMinY;
	/* The maximum height the block spawns in. Cannot go over 256. */
	private int genMaxY;
	/* The block your block spawns in. */
	private Block[] genIn;
	/* The block your blocks spawns close to. */
	private Block[] genNear;
	/* The distance between every vein. */
	private int genDistance;
	/* Generate in the Overworld. DimensionID = 0*/
	private boolean genOverworld;
	/* Generate in the Nether. DimensionID = -1*/
	private boolean genNether;
	/* Generate in the End. DimensionID = 1*/
	private boolean genEnd;
	
	/* This method initializes the variables above. */
	public RWBlockGen(RWBlock block, int minVeinSize, int maxVeinSize, int genChance, int minGenY, int maxGenY, Block[] generateIn, Block[] generateNear, int generateDistance, boolean generateInTheOverworld, boolean generateInTheNether, boolean generateInTheEnd)
	{
		this.genBlock = block;
		this.minVeinSize = minVeinSize;
		this.maxVeinSize = maxVeinSize;
		this.genChance = genChance;
		this.genMinY = minGenY;
		this.genMaxY = maxGenY;
		this.genIn = generateIn;
		this.genNear = generateNear;
		this.genDistance = generateDistance;
		this.genOverworld = generateInTheOverworld;
		this.genNether = generateInTheNether;
		this.genEnd = generateInTheNether;
	}
	/* Just a random method */
	public RWBlockGen(RWBlock block, int minVeinSize, int maxVeinSize, int genChance, int minGenY, int maxGenY, Block[] generateIn, boolean generateInTheOverworld, boolean generateInTheNether, boolean generateInTheEnd)
	{
		this(block, minVeinSize, maxVeinSize, genChance, minGenY, maxGenY, generateIn, null, 1, generateInTheOverworld, generateInTheNether, generateInTheEnd);
	}
	/* This method sets the variables for the generate method. */
	public void generateBlock(Block block, World world, Random random, int chunkX, int chunkZ, int minVeinSize, int maxVeinSize, int chance, int minY, int maxY, Block[] generateIn)
	{
		int veinSize = minVeinSize + random.nextInt(maxVeinSize - minVeinSize);
		int height = maxY - minY;
		
		RWWorldGenMinable gen = new RWWorldGenMinable(block.getDefaultState(), veinSize, generateIn, this.genNear, this.genDistance);
		for (int i = 0; i < chance; i++)
		{
			int xRand = chunkX * 16 + random.nextInt(16);
			int yRand = random.nextInt(height) + minY;
			int zRand = chunkZ * 16 + random.nextInt(16);
			gen.generate(world, random, new BlockPos(xRand, yRand, zRand));
		}
	}
	/* This method takes all the variables and generates the block in the world. */
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		int currentDimension = world.provider.getDimension();
		
		if (currentDimension == -1 && genNether)
		{
			generateBlock((Block) this.genBlock, world, random, chunkX, chunkZ, this.minVeinSize, this.maxVeinSize, this.genChance, this.genMinY, this.genMaxY, this.genIn);
		}
		else if (currentDimension == 0 && genOverworld)
		{
			generateBlock((Block) this.genBlock, world, random, chunkX, chunkZ, this.minVeinSize, this.maxVeinSize, this.genChance, this.genMinY, this.genMaxY, this.genIn);
		}
		else if (currentDimension == 1 && genEnd)
		{
			generateBlock((Block) this.genBlock, world, random, chunkX, chunkZ, this.minVeinSize, this.maxVeinSize, this.genChance, this.genMinY, this.genMaxY, this.genIn);
		}
	}
}