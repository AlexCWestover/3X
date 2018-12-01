package threex.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import threex.Main;
import threex.init.ModItems;
import threex.util.IHasModel;

public class ItemBase extends Item implements IHasModel {

		public ItemBase(String name) {
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
			
			ModItems.ITEMS.add(this);
		}
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this,0,"intentory");
	}

}
