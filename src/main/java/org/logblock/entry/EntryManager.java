package org.logblock.entry;

public abstract class EntryManager
{

    public final void write(MainEntry entry)
    {
    }

    public abstract void write(ItemEntry entry);

    public abstract void write(PlayerEntry entry);

    public abstract void write(TextEntry entry);

    public abstract void write(BlobEntry entry);
}
