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

    private byte blank = new Byte("");

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
        Location location = block.getLocation();
        int x = (int) location.getX();
        int y = (int) location.getX();
        int z = (int) location.getX();
        LogBlock.dataStore.write(new MainEntry(Action.BLOCK_BREAK, player.getName(), block.getTypeId(), blank, Material.AIR.getId(), blank, x, y, z));
    }
}
