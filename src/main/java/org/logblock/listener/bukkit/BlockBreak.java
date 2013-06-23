package org.logblock.listener.bukkit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.logblock.Action;
import org.logblock.LogBlock;
import org.logblock.entry.MainEntry;

public class BlockBreak extends BukkitListener<BlockBreakEvent>
{

    private byte blank = (byte) 0;

    public BlockBreak(LogBlock lb)
    {
        super(lb);
    }

    @Override
    @EventHandler(priority = EventPriority.MONITOR)
    public void listen(BlockBreakEvent event)
    {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        this.lb.getDataStore().write(new MainEntry(Action.BLOCK_BREAK, player.getName(), block.getTypeId(), block.getData(), Material.AIR.getId(), blank, x, y, z));
    }
}
