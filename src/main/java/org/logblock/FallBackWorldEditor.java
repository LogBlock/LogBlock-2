package org.logblock;

import org.bukkit.World;

public class FallBackWorldEditor extends WorldEditor
{

    public FallBackWorldEditor(World world)
    {
        super(world);
    }

    @Override
    public void setTypeAndData(int x, int y, int z, int type, byte data)
    {
        world.getBlockAt(x, y, z).setTypeIdAndData(type, data, true);
    }
}
