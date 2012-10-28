package org.logblock.craftbukkit;

import net.minecraft.server.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.logblock.FallBackWorldEditor;
import org.logblock.WorldEditor;

public class CraftWorldEditor extends WorldEditor
{

    private World world;
    private WorldEditor delegate;

    public CraftWorldEditor(org.bukkit.World world)
    {
        super(world);
        if (world instanceof CraftWorld)
        {
            this.world = ((CraftWorld) world).getHandle();
        } else
        {
            delegate = new FallBackWorldEditor(world);
        }
    }

    @Override
    public void setTypeAndData(int x, int y, int z, int type, byte data)
    {
        if (delegate == null)
        {
            world.setRawTypeIdAndData(x, y, z, type, data);
        } else
        {
            delegate.setTypeAndData(x, y, z, type, data);
        }
    }
}
