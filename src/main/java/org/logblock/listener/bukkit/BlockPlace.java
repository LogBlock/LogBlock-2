package org.logblock.listener.bukkit;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.logblock.Action;
import org.logblock.LogBlock;
import org.logblock.entry.MainEntry;

public class BlockPlace extends BukkitListener<BlockPlaceEvent>
{

    public BlockPlace(LogBlock lb)
    {
        super(lb);
    }

    @Override
    @EventHandler(priority = EventPriority.MONITOR)
    public void listen(BlockPlaceEvent event)
    {
        Block block = event.getBlockPlaced();
        Block fromBlock = event.getBlockReplacedState().getBlock();
        Player player = event.getPlayer();
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        this.lb.getDataStore().write(new MainEntry(Action.BLOCK_PLACE, player.getName(), fromBlock.getTypeId(), fromBlock.getData(), block.getTypeId(), block.getData(), x, y, z));
    }

}
