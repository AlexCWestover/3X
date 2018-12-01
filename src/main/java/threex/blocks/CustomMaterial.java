package threex.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class CustomMaterial extends Material {

	public CustomMaterial(MapColor color) {
		super(color);
	}
	
	
    public boolean isSolid()
    {
        return false;
    }

    
    public boolean blocksLight()
    {
        return false;
    }

    
    public boolean blocksMovement()
    {
        return false;
    }

}
