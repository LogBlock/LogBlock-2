package org.logblock.listener.bukkit;

import org.bukkit.event.block.BlockBreakEvent;
import org.logblock.LogBlock;

public class BlockBreak extends BukkitListener<BlockBreakEvent>
{

    public BlockBreak(LogBlock lb)
    {
        super(lb);
    }

    @Override
    public void listen(BlockBreakEvent event)
    {
    }
}
