
package net.rw.rwmod.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RWWorldGenMinable extends WorldGenerator
{
	/* The block that's going to be generated in the world. */
	private final IBlockState block;
	/* How many blocks that can spawn in the same vein. */
	private final int numberOfBlocks;
	/* In what block the block should spawn in. */
	private final Block[] genIn;
	/* What the block should spawn close to. Example: Sulfur spawns near lava. */
	private final Block[] genNear;
	/* The distance between every vein. */
	private final int genDistance;
	
	/* This method sets all the variables above. */
	public RWWorldGenMinable(IBlockState block, int veinSize, Block[] generateIn, Block[] generateNear, int generateDistance)
	{
		this.block = block;
		this.numberOfBlocks = veinSize;
		this.genIn = generateIn;
		this.genNear = generateNear;
		this.genDistance = generateDistance;
	}
	/* This method sets in what block the block of your choice spawns in. */
	public RWWorldGenMinable(IBlockState block, int veinSize)
	{
		this(block, veinSize, new Block[]{Blocks.STONE});
	}
	/* Mother method to the method above. */
	public RWWorldGenMinable(IBlockState block, int veinSize, Block[] generateIn)
	{
		this(block, veinSize, generateIn, null, 1);
	}
	/* This method checks if the block can be replaced with your block. */
	private boolean isReplaceable(World world, BlockPos pos, Block[] genIn)
	{
		for (Block block : genIn)
		{
			if (world.getBlockState(pos).getBlock() == block)
			{
				return true;
			}
		}
		
		return false;
	}
	/* This method checks if a vein is too close to another vein of your block. */
	private boolean isNear(BlockPos pos, Block[] near, int distance, World world)
	{
		for (int x = (pos.getX() - distance); x <= (pos.getX() + distance); ++x)
		{
			for (int y = (pos.getY() - distance); y <= (pos.getY() + distance); ++y)
			{
				for (int z = (pos.getZ() - distance); z <= (pos.getZ() + distance); ++z)
				{
					BlockPos posNear = new BlockPos(x, y, z);
					
					for (Block blockNear : near)
					{
						if (world.getBlockState(posNear).getBlock() == blockNear && posNear != pos)
						{
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	/* This method generates your block in the world. */
	public boolean generate(World world, Random random, BlockPos pos)
    {
        float f = random.nextFloat() * (float)Math.PI;
        double d0 = (double)((float)(pos.getX() + 8) + MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
        double d1 = (double)((float)(pos.getX() + 8) - MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
        double d2 = (double)((float)(pos.getZ() + 8) + MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
        double d3 = (double)((float)(pos.getZ() + 8) - MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
        double d4 = (double)(pos.getY() + random.nextInt(3) - 2);
        double d5 = (double)(pos.getY() + random.nextInt(3) - 2);

        for (int i = 0; i < this.numberOfBlocks; ++i)
        {
            float f1 = (float)i / (float)this.numberOfBlocks;
            double d6 = d0 + (d1 - d0) * (double)f1;
            double d7 = d4 + (d5 - d4) * (double)f1;
            double d8 = d2 + (d3 - d2) * (double)f1;
            double d9 = random.nextDouble() * (double)this.numberOfBlocks / 16.0D;
            double d10 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            double d11 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            int j = MathHelper.floor_double(d6 - d10 / 2.0D);
            int k = MathHelper.floor_double(d7 - d11 / 2.0D);
            int l = MathHelper.floor_double(d8 - d10 / 2.0D);
            int i1 = MathHelper.floor_double(d6 + d10 / 2.0D);
            int j1 = MathHelper.floor_double(d7 + d11 / 2.0D);
            int k1 = MathHelper.floor_double(d8 + d10 / 2.0D);

            for (int l1 = j; l1 <= i1; ++l1)
            {
                double d12 = ((double)l1 + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D)
                {
                    for (int i2 = k; i2 <= j1; ++i2)
                    {
                        double d13 = ((double)i2 + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D)
                        {
                            for (int j2 = l; j2 <= k1; ++j2)
                            {
                                double d14 = ((double)j2 + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D)
                                {
                                    BlockPos pos1 = new BlockPos(l1, i2, j2);

                                    if (this.isReplaceable(world, pos1, this.genIn))
                                    {
                                    	if (this.genNear == null)
                                    	{
                                    		world.setBlockState(pos1, this.block, 2);
                                    	}
                                    	else
                                    	{
                                    		if (this.isNear(pos1, this.genNear, this.genDistance, world))
                                    		{
                                    			world.setBlockState(pos1, this.block, 2);
                                    		}
                                    	}
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}