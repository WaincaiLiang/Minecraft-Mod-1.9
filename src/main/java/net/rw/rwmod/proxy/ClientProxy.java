package net.rw.rwmod.proxy;

import net.rw.rwmod.init.BlockInit;
import net.rw.rwmod.init.ItemInit;

public class ClientProxy extends CommonProxy
{
	/* Even though you add the CommonProxy in your main file you still put all code here. */
	public void registerRenders()
	{
		/* Registers the item renderers. */
		ItemInit.registerRenders();
		/* Registers the block renderers. */
		BlockInit.registerRenders();
	}
}