package threex.blocks;

import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MachineBlock extends BlockBase implements ITickable{
	
	
	private boolean activated = false;
	private int currentIndex = -1;
	private ArrayList<BlockPos> blocksToDestroy = null;
	private World worldIn = null;
	  
	public MachineBlock(String name, Material material) {
		super(name, material);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
	}
	
	@Override
	public boolean onBlockActivated(World worldIn,BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		for(int i = 0; i<100; ++i) {
			process();
		}
		return true;
    }
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		
		int depth = 16;
		BlockPos center = calculateCenter(pos,placer,depth);
		setBlocksToDestroy(returnBlocksToDestroy(worldIn, center,depth));
		setWorld(worldIn);
		setIndex(0);
		setActivated();
		
		
		
		System.out.println("Machine_Block PLACED");
	}
	
	@Override
	public void update() {
		// This isn't working as expected
	}
	
	private ArrayList<BlockPos> returnBlocksToDestroy(World worldIn, BlockPos pos, int depth) {
		ArrayList<BlockPos> blocksToDestroy = new ArrayList<BlockPos>();
		int radius = depth;
		for(int current = 1; current <= depth; ++current, --radius) {
			for(int x = (-1)*radius; x <= radius; ++x) {
				for(int z =(-1)*radius; z <= radius; ++z) {
					if(!worldIn.isAirBlock(pos.add(x, (-1)*current, z)) &&
							!worldIn.getBlockState(pos.add(x, (-1)*current, z)).getBlock().getClass().toString().equals("class net.minecraft.block.BlockEmptyDrops")) {
						blocksToDestroy.add(pos.add(x, (-1)*current, z));
					}
					
				}
			}
		}
		return blocksToDestroy;	
	}
	
	private void process() {
		if(this.activated && this.currentIndex != -1) {
			if(hasNext()) {
				destroyNext();
			}
			else {
				System.out.println("FINISHED");
				setDeactive();
				setIndex(-1);
			}
		}
	}
	
	private void setIndex(int index) {
		this.currentIndex = index;
	}
	
	private void setActivated() {
		this.activated = true;
	}
	
	private void setDeactive() {
		this.activated = false;
	}
	
	private void setBlocksToDestroy(ArrayList<BlockPos> blocksToDestroy) {
		this.blocksToDestroy = blocksToDestroy;
	}
	
	private void setWorld(World worldIn) {
		this.worldIn = worldIn;
	}
	
	private void destroyNext() {
		this.worldIn.destroyBlock(this.blocksToDestroy.get(currentIndex), false);
		nextIndex();
	}
	
	private boolean hasNext() {
		if(currentIndex + 1 > this.blocksToDestroy.size()) {
			return false;
		}
		return true;
	}
	
	private void nextIndex() {
		++currentIndex;
	}
	
	private BlockPos calculateCenter(BlockPos pos,EntityLivingBase placer, int radius) {
		// North is Negative Z
		// East is Positive X
		// South is Positive Z
		// West is Negative X
		EnumFacing x = placer.getHorizontalFacing();
		switch(x) {
		case NORTH:
			return pos.add(0, 0, (-1) * radius - 1);
		case EAST:
			return pos.add(radius + 1, 0, 0);
		case SOUTH:
			return pos.add(0, 0, radius + 1);
		case WEST:
			return pos.add((-1) * radius - 1, 0, 0);
		default:
			return pos;
		}
	}
}
