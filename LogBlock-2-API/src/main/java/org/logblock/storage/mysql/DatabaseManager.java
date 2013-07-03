package org.logblock.storage.mysql;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.logblock.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager
{

    private final BoneCP connectionPool;

    public DatabaseManager(Configuration config) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");

        // setup the connection pool
        BoneCPConfig boneConfig = new BoneCPConfig();
        boneConfig.setJdbcUrl("jdbc:mysql://127.0.0.1/yourdb"); // jdbc url specific to your database
        boneConfig.setUsername("");
        boneConfig.setPassword("");
        boneConfig.setMinConnectionsPerPartition(5);
        boneConfig.setMaxConnectionsPerPartition(10);
        boneConfig.setPartitionCount(1);
        this.connectionPool = new BoneCP(boneConfig); // setup the connection pool
    }

    protected void shutDown() {
        this.connectionPool.shutdown();
    }

    public Connection getConnection() throws SQLException
    {
        return this.connectionPool.getConnection();
    }

    public BoneCP getConnectionPool()
    {
        return connectionPool;
    }
}
