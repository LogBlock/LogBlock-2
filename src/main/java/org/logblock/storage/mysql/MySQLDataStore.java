package org.logblock.storage.mysql;

import org.logblock.entry.*;
import org.logblock.query.Query;
import org.logblock.storage.DataStore;

import java.util.List;

public class MySQLDataStore extends DataStore
{
    @Override
    public List<AbstractEntry> retrieveEntries(Query query)
    {
        return null;
    }

    @Override
    public void write(MainEntry entry)
    {

    }

    @Override
    public void write(ItemEntry entry)
    {

    }

    @Override
    public void write(PlayerEntry entry)
    {

    }

    @Override
    public void write(TextEntry entry)
    {

    }

    @Override
    public void write(BlobEntry entry)
    {

    }

    @Override
    public PlayerEntry getPlayer(int id)
    {
        return null;
    }

    @Override
    public List<AbstractEntry> getChildren(MainEntry entry)
    {
        return null;
    }
}
