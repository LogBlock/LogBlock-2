package org.logblock.storage;

import org.logblock.entry.AbstractEntry;
import org.logblock.entry.EntryManager;
import org.logblock.query.Query;

import java.util.List;

public abstract class DataStore implements EntryManager
{

    public abstract List<AbstractEntry> retrieveEntries(Query query);

}
