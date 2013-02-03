package org.logblock.storage.mysql;

import org.logblock.Configuration;
import org.logblock.LogBlock;
import org.logblock.entry.*;
import org.logblock.query.Query;
import org.logblock.storage.DataStore;

import java.util.List;

public class MySQLDataStore extends DataStore
{
    private LogBlock lb;
    private DatabaseManager database;

    public MySQLDataStore(LogBlock lb, Configuration config) {
        this.lb = lb;
        this.database = new DatabaseManager(config);
    }

    @Override
    public List<AbstractEntry> retrieveEntries(Query query)
    {
        return null;
    }

    @Override
    public void onDisable()
    {
        if (database != null) {
            lb.getLogger().info("Closing remaining SQL connections");
            this.database.getDataSource().close();
        }
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
