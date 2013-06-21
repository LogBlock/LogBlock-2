package org.logblock.listener.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.logblock.LogBlock;

public class BlockBreak extends BukkitListener<BlockBreakEvent>
{

    public BlockBreak(LogBlock lb)
    {
        super(lb);
    }

    @Override
    @EventHandler(priority = EventPriority.MONITOR)
    public void listen(BlockBreakEvent event)
    {
    }
}
