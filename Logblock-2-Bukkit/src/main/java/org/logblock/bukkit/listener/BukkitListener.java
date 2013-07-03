package org.logblock.bukkit.listener;

import org.bukkit.event.Event;
import org.logblock.bukkit.LogBlock;
import org.logblock.bukkit.listener.AbstractListener;

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
