package org.logblock.storage;

import lombok.Getter;
import org.logblock.LogBlockPlugin;
import org.logblock.entry.AbstractEntry;
import org.logblock.entry.EntryManager;
import org.logblock.query.Query;

import java.util.List;
import java.util.logging.Logger;

public abstract class DataStore implements EntryManager
{
    @Getter
    private static DataStore instance;
    protected LogBlockPlugin lb;

    public DataStore(LogBlockPlugin lb) {
        this.lb = lb;
        instance = this;
    }

    public Logger getLogger() {
        return this.lb.getLogger();
    }

    public void write(AbstractEntry entry)
    {
        throw new IllegalStateException("Cannot write an abstract entry");
    }

    public abstract List<AbstractEntry> retrieveEntries(Query query);

    public abstract void onDisable();
}
