package org.logblock.entry;

import java.util.List;

public interface EntryManager
{

    public void write(MainEntry entry);

    public void write(ItemEntry entry);

    public void write(PlayerEntry entry);

    public void write(TextEntry entry);

    public void write(BlobEntry entry);

    public PlayerEntry getPlayer(int id);

    public List<AbstractEntry> getChildren(MainEntry entry);
}
