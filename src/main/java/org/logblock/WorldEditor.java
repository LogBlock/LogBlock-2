package org.logblock;

import org.bukkit.World;

public abstract class WorldEditor
{

    protected final World world;

    public WorldEditor(World world)
    {
        this.world = world;
    }

    public abstract void setTypeAndData(int x, int y, int z, int type, byte data);
}
