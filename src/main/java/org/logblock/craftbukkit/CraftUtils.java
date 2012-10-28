package org.logblock.craftbukkit;

import net.minecraft.server.NBTCompressedStreamTools;
import net.minecraft.server.NBTTagCompound;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class CraftUtils
{

    public byte[] getNBT(ItemStack item)
    {
        CraftItemStack craft = (CraftItemStack) item;
        NBTTagCompound compound = craft.getHandle().getTag();
        return NBTCompressedStreamTools.a(compound);
    }

    public void setNBT(ItemStack item, byte[] nbt)
    {
        CraftItemStack craft = (CraftItemStack) item;
        NBTTagCompound compound = NBTCompressedStreamTools.a(nbt);
        craft.getHandle().setTag(compound);
    }
}
