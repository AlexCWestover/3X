package threex.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import threex.blocks.CloudBlock;
import threex.blocks.CustomMaterial;
import threex.blocks.MachineBlock;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final Block MACHINE_BLOCK = new MachineBlock("machine_block", Material.IRON);
	public static final Block CLOUD_BLOCK = new CloudBlock("cloud_block", new CustomMaterial(MapColor.GRAY)); 
}
