package org.logblock.storage.mysql;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.logblock.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager
{

    private final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public DatabaseManager(Configuration config)
    {
    }

    public Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }

    public ComboPooledDataSource getDataSource()
    {
        return dataSource;
    }
}
