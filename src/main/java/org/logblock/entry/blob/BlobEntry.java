package org.logblock.entry.blob;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.logging.Level;
import org.logblock.LogBlock;
import org.logblock.entry.AbstractEntry;

public abstract class BlobEntry extends AbstractEntry
{

    private static final Class<? extends BlobEntry>[] mappings = new Class[256];
    private byte type;

    public BlobEntry(int id, byte type)
    {
        super(id);
        this.type = type;
    }

    public abstract void read(DataInput in) throws IOException;

    public abstract void write(DataOutput out) throws IOException;

    static
    {
        mappings[0] = PaintingBlob.class;
    }

    public static BlobEntry create(int id, byte type)
    {
        Class<? extends BlobEntry> mapping = mappings[type];

        BlobEntry ret = null;
        if (mapping != null)
        {
            try
            {
                ret = mapping.getDeclaredConstructor(int.class, byte.class).newInstance(id, type);
            } catch (Exception ex)
            {
                LogBlock.getInstance().getLogger().log(Level.SEVERE, "Could not create blob with type " + type, ex);
            }
        }

        return ret;
    }
}
