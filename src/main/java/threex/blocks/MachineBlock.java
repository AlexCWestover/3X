package threex.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class MachineBlock extends BlockBase implements ITickable {

	  public static final PropertyDirection FACING = PropertyDirection.create("facing");
	  
	public MachineBlock(String name, Material material) {
		super(name, material);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

	@Override
	public void update() {
		breakBlocks();
	}
	
	private void breakBlocks() {
		
	}
	
}
