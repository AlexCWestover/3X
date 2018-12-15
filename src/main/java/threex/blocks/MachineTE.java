package threex.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MachineTE extends TileEntity implements ITickable {
	
	private ArrayList<BlockPos> blocksToDestroy = null;
	private int currentIndex = -1;
	private boolean activated = false;
	private boolean isInitalized = false;
	public MachineTE() {
		super();
	}
	
	public void init() {
		this.blocksToDestroy = returnBlocksToDestroy(this.world,this.pos,16);
		currentIndex = 0;
		activated = true;
		isInitalized = true;
		// Get Facing somehow
		//System.out.println((EnumFacing)this.world.getBlockState(this.pos).getValue(BlockHorizontal.FACING));
	}

	@Override
	public void update() {
		if(isInitalized) {
			// SUPER FAST
			for(int i = 0; i < 5; ++i) {
				process();
			}
		}
		else {
			init();
		}
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
	
	private void destroyNext() {
		IBlockState ibs = this.world.getBlockState(this.blocksToDestroy.get(currentIndex));
		Block block = ibs.getBlock();
		this.world.playEvent(2001, this.blocksToDestroy.get(currentIndex), Block.getStateId(ibs));
		block.dropBlockAsItem(this.world, this.pos, ibs, 0);
		this.world.setBlockState(this.blocksToDestroy.get(currentIndex), Blocks.AIR.getDefaultState(),3);
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
	
	private void setDeactive() {
		this.activated = false;
	}
	
	private void setIndex(int index) {
		this.currentIndex = index;
	}
	
	

}
