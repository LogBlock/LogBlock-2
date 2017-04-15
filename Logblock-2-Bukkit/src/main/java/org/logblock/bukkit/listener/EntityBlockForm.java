package org.logblock.bukkit.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.logblock.Action;
import org.logblock.bukkit.LogBlock;
import org.logblock.entry.MainEntry;


public class EntityBlockForm extends BukkitListener<EntityBlockFormEvent> {

    public EntityBlockForm(LogBlock lb) {
        super(lb);
    }

    @Override
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void listen(EntityBlockFormEvent event) {
        Block block = event.getBlock();
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        Entity entity = event.getEntity();
        if (entity instanceof FallingBlock) {
            FallingBlock falling = (FallingBlock) entity;
            this.lb.getDataStore().write(new MainEntry(Action.BLOCK_FALL, falling.getMaterial().toString(), Material.AIR.getId(), (byte) 0, falling.getBlockId(), falling.getBlockData(), x, y, z));
        } else {
            if (entity instanceof LivingEntity) {
                this.lb.getDataStore().write(new MainEntry(Action.BLOCK_PLACE, entity.getType().getName(), Material.AIR.getId(), (byte) 0, block.getTypeId(), block.getData(), x, y, z));
            }
        }
    }

}
