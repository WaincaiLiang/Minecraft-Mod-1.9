package net.rw.rwmod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.rw.rwmod.Main;
import net.rw.rwmod.Reference;

public class ItemInit
{
	/* Items. Not yet initialized. */
	public static Item TEST_ITEM;
	
	/* Here the items gets initialized. */
	public static void init()
	{
		TEST_ITEM = new Item().setUnlocalizedName("test_item").setCreativeTab(Main.tabRW);
	}
	
	/* Here the items gets registered. */
	public static void register()
	{
		GameRegistry.registerItem(TEST_ITEM, TEST_ITEM.getUnlocalizedName().substring(5));
	}
	
	/* Here the items gets registered to the rendering queue. */
	public static void registerRenders()
	{
		registerRender(TEST_ITEM);
	}
	
	/* Here the items gets rendered after being added to the rendering queue. */
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}