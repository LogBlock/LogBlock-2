package org.logblock.listener;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public abstract class AbstractListener<T extends Event> implements Listener
{

    public abstract void listen(T event);
}
