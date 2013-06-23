package org.logblock.listener.bukkit;

import org.bukkit.event.Event;
import org.logblock.LogBlock;
import org.logblock.listener.AbstractListener;

public abstract class BukkitListener<T extends Event> extends AbstractListener<T>
{
    protected LogBlock lb;

    public BukkitListener(LogBlock lb)
    {
        this.lb = lb;
    }

    public void register(LogBlock lb)
    {
        lb.getServer().getPluginManager().registerEvents(this, lb);
    }
}
