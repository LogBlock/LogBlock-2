package org.logblock.entry.blob;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.logblock.entry.BlobEntry;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class PaintingBlob extends BlobEntry
{

    private String art;
    private byte direction;

    public PaintingBlob(int id, byte type)
    {
        super(id, type);
    }

    @Override
    public void read(DataInput in) throws IOException
    {
        art = in.readUTF();
        direction = in.readByte();
    }

    @Override
    public void write(DataOutput out) throws IOException
    {
        out.writeUTF(art);
        out.writeByte(direction);
    }
}
