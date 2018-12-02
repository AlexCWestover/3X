package threex.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MachineBlock extends BlockBase {

	  
	public MachineBlock(String name, Material material) {
		super(name, material);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
	}
	
	@Override
	public boolean onBlockActivated(World worldIn,BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		// How deep: 3
		int depth = 3;
		int radius = depth;
		for(int current = 1; current <= depth; ++current, --radius) {
			destroyLevel(worldIn,pos,current,radius);
		}
	
		return true;
    }
	
	private void destroyLevel(World worldIn, BlockPos pos, int level, int radius) {
		for(int x = (-1)*radius; x <= radius; ++x) {
			for(int y =(-1)*radius; y <= radius; ++y) {
				worldIn.destroyBlock(pos.add(x, (-1)*level, y), true);
			}
		}
		
	}
	


}
