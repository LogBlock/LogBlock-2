package org.logblock.entry.blob;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.bukkit.Art;
import org.bukkit.block.BlockFace;

public class PaintingBlob extends BlobEntry
{

    private Art art;
    private BlockFace direction;

    public PaintingBlob(int id, byte type)
    {
        super(id, type);
    }

    @Override
    public void read(DataInput in) throws IOException
    {
        art = Art.getByName(in.readUTF());
        direction = BlockFace.values()[in.readByte()];
    }

    @Override
    public void write(DataOutput out) throws IOException
    {
        out.writeUTF(art.name());
        out.writeByte(direction.ordinal());
    }
}
