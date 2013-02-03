package org.logblock.storage;

import org.logblock.entry.AbstractEntry;
import org.logblock.entry.EntryManager;
import org.logblock.query.Query;

import java.util.List;

public abstract class DataStore implements EntryManager
{
    public void write(AbstractEntry entry)
    {
        throw new IllegalStateException("Cannot write an abstract entry");
    }

    public abstract List<AbstractEntry> retrieveEntries(Query query);

    public abstract void onDisable();
}
