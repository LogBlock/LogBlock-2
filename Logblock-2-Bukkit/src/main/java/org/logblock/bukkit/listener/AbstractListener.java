package org.logblock.bukkit.listener;

import org.logblock.bukkit.LogBlock;
import org.bukkit.event.Listener;

public abstract class AbstractListener<T> implements Listener
{

    public abstract void listen(T event);

    public abstract void register(LogBlock lb);
}
