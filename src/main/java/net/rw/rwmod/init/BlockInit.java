package net.rw.rwmod.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.rw.rwmod.Main;
import net.rw.rwmod.Reference;
import net.rw.rwmod.block.RWBlock;

public class BlockInit
{
	/* Blocks. Not yet initialized. */
	public static Block MARBLE;
	public static Block BLACK_MARBLE;

	/* Here the blocks gets initialized. */
	public static void init()
	{
		MARBLE = new RWBlock(Material.ROCK, MapColor.SNOW, "marble", 1.0F, 5.0F, SoundType.STONE, RWBlock.HarvestToolEnum.PICKAXE, RWBlock.HarvestLevelEnum.WOOD, Main.tabRW, 1, 1, 30, 200, 1, 256, new Block[]{Blocks.STONE}, new Block[]{Blocks.LAVA}, 1, true, false, false);
		BLACK_MARBLE = new RWBlock(Material.ROCK, MapColor.BLACK, "black_marble", 1.0F, 5.0F, SoundType.STONE, RWBlock.HarvestToolEnum.PICKAXE, RWBlock.HarvestLevelEnum.WOOD, Main.tabRW, 1, 1, 30, 200, 1, 256, 1, true, false, false);
	}
	
	/* Here the blocks gets registered. */
	public static void register()
	{
		GameRegistry.registerBlock(MARBLE, MARBLE.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BLACK_MARBLE, BLACK_MARBLE.getUnlocalizedName().substring(5));
	}
	
	/* Here the blocks gets registered to the rendering queue. */
	public static void registerRenders()
	{
		registerRender(MARBLE);
		registerRender(BLACK_MARBLE);
	}
	
	/* Here the blocks gets rendered after being added to the rendering queue. */
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5).toLowerCase(), "inventory"));
	}
}