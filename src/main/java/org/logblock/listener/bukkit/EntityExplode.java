package org.logblock.listener.bukkit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.logblock.Action;
import org.logblock.LogBlock;
import org.logblock.entry.MainEntry;

import java.util.List;

public class EntityExplode extends BukkitListener<EntityExplodeEvent> {

    private byte blank = (byte) 0;

    public EntityExplode(LogBlock lb)
    {
        super(lb);
    }

    @Override
    @EventHandler(priority = EventPriority.MONITOR)
    public void listen(EntityExplodeEvent event) {
        List<Block> blocks = event.blockList();
        for (Block block : blocks ) {
            Location location = block.getLocation();
            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();
            this.lb.getDataStore().write(new MainEntry(Action.EXPLOSION, event.getEntityType().getName(), block.getTypeId(), block.getData(), Material.AIR.getId(), blank, x, y, z));
        }
    }

}
