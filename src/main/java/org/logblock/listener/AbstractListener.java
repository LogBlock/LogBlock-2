package org.logblock.listener;

import org.bukkit.event.Listener;
import org.logblock.LogBlock;

public abstract class AbstractListener<T> implements Listener
{

    public abstract void listen(T event);

    public abstract void register(LogBlock lb);
}
