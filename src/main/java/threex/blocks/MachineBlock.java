package threex.blocks;

import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MachineBlock extends BlockBase{
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	//private boolean activated = false;
	//private int currentIndex = -1;
	//private ArrayList<BlockPos> blocksToDestroy = null;
	//private World worldIn = null;
	//private BlockPos location = null;
	//private EnumFacing facing = null;
	  
	public MachineBlock(String name, Material material) {
		super(name, material);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn,BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		System.out.println("Stop hitting me.");
		return true;
    }
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		// Attempting to pass Facing enum to entitiy. 
		// worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()),2);
		
		/* Moved Calculations to Tile Entity
		
		setLocation(pos);
		int depth = 16;
		BlockPos center = calculateCenter(pos,placer,depth);
		setBlocksToDestroy(returnBlocksToDestroy(worldIn, center,depth));
		setWorld(worldIn);
		setIndex(0);
		setActivated();
		
		*/
		
		System.out.println("Machine_Block PLACED");
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	/*
	@Override
	public void update() {
		// This isn't working as expected
	}
	*/
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new MachineTE();
	}
	
	/*
	@Override
	public int tickRate(World worldIn) {
		return 10;
	}
	
	*/
	
	/*

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
	
	
	
	public void process() {
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

		IBlockState ibs = this.worldIn.getBlockState(this.blocksToDestroy.get(currentIndex));
		Block block = ibs.getBlock();
		this.worldIn.playEvent(2001, this.blocksToDestroy.get(currentIndex), Block.getStateId(ibs));
		block.dropBlockAsItem(worldIn, this.location, ibs, 0);
		//this.worldIn.setBlockToAir(this.blocksToDestroy.get(currentIndex));
		this.worldIn.setBlockState(this.blocksToDestroy.get(currentIndex), Blocks.AIR.getDefaultState(),3);
		//this.worldIn.destroyBlock(this.blocksToDestroy.get(currentIndex), false);
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
	
	private void setLocation(BlockPos pos) {
		this.location = pos;
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
	
	public EnumFacing getFacing() {
		return this.facing;
	}
	*/
}
