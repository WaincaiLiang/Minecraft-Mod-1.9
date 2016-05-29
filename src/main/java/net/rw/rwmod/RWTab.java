package net.rw.rwmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.rw.rwmod.init.BlockInit;

public class RWTab extends CreativeTabs
{
	/* Sets the title that displays in your tab and when you hover over your creative tab icon. */
	public RWTab(String label)
	{
		super(label);
	}
 	/* Sets the icon for your tab. */
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(BlockInit.MARBLE);
	}
}