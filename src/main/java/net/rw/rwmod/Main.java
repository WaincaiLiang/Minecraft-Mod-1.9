package net.rw.rwmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.rw.rwmod.init.BlockInit;
import net.rw.rwmod.init.ItemInit;
import net.rw.rwmod.proxy.CommonProxy;

/* This line makes Forge recognize this file as the mod's main file. */
@Mod(name = Reference.MOD_NAME, modid = Reference.MOD_ID, version = Reference.MOD_VERSION)
public class Main
{
	/* With this line you add your mod's renderers. */
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	/* Your own creative tab. */
	public static final RWTab tabRW = new RWTab("tabRW");
	
	/* Everything that registers an object in-game goes here. */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		/* Here the item gets created. */
		ItemInit.init();
		/* Here the item is registered into the games code. */
		ItemInit.register();
		/* Here the block gets created. */
		BlockInit.init();
		/* Here the block is registered into the games code. */
		BlockInit.register();
	}
	
	/* All renderers goes here. */
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		/* Here the proxy adds all the rendering code.*/
		proxy.registerRenders();
	}
	
	/* OreDictionary goes here. */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}