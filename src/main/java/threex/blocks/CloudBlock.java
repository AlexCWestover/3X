package threex.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CloudBlock extends BlockBase {

	public CloudBlock(String name, Material material) {
		super(name, material);
		this.setLightOpacity(0);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid){
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
	    return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state){
        return false;
    }

	@Override
	public boolean isCollidable()
    {
        return false;
    }
	
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return this.blockMaterial != Material.LAVA;
    }

}
