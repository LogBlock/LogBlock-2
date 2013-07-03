package org.logblock;

import org.logblock.storage.DataStore;

import java.util.logging.Logger;

public interface LogBlockPlugin
{
    public abstract DataStore getDataStore();

    public abstract Logger getLogger();
}
