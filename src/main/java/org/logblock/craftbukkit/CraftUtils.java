package org.logblock.craftbukkit;

import net.minecraft.server.v1_4_6.NBTCompressedStreamTools;
import net.minecraft.server.v1_4_6.NBTTagCompound;
import org.bukkit.craftbukkit.v1_4_6.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class CraftUtils
{

    public byte[] getNBT(ItemStack item)
    {
        NBTTagCompound compound = CraftItemStack.asNMSCopy(item).getTag();
        return NBTCompressedStreamTools.a(compound);
    }

    public ItemStack setNBT(ItemStack item, byte[] nbt)
    {
        net.minecraft.server.v1_4_6.ItemStack nms = CraftItemStack.asNMSCopy(item);
        nms.setTag(NBTCompressedStreamTools.a(nbt));
        return CraftItemStack.asBukkitCopy(nms);
    }
}
