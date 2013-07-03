package org.logblock.bukkit.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.logblock.Action;
import org.logblock.bukkit.LogBlock;
import org.logblock.entry.MainEntry;


public class BlockPhysics extends BukkitListener<BlockPhysicsEvent> {

    public BlockPhysics(LogBlock lb) {
        super(lb);
    }

    @Override
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void listen(BlockPhysicsEvent event) {
        Material before = event.getChangedType();
        Block block = event.getBlock();
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        this.lb.getDataStore().write(new MainEntry(Action.BLOCK_FALL, before.toString(), before.getId(), block.getData(), Material.AIR.getId(), (byte) 0, x, y, z));
    }

}
