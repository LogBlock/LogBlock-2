package org.logblock;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;
import org.logblock.entry.AbstractEntry;
import org.logblock.entry.MainEntry;
import org.logblock.entry.PlayerEntry;
import org.mcstats.Metrics;

public class LogBlock extends JavaPlugin
{

    private static LogBlock instance;
    private Configuration config;
    private Consumer consumer;
    private DatabaseManager database;
    private Metrics metrics;

    @Override
    public void onLoad()
    {
        instance = this;
    }

    @Override
    public void onEnable()
    {
        getCommand("logblock").setExecutor(new Commands());

        try
        {
            config = new Configuration();
            consumer = new Consumer();
            database = new DatabaseManager(config);
        } catch (Exception e)
        {
            getLogger().severe("=========================");
            getLogger().severe("Error starting up logblock");
            getLogger().severe(e.getMessage());
            getLogger().severe("");
            e.printStackTrace();
            getLogger().severe("=========================");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        try
        {
            metrics = new Metrics(this);
            metrics.start();
        } catch (IOException ex)
        {
            getLogger().log(Level.WARNING, "Could not start metrics system!", ex);
        }
    }

    @Override
    public void onDisable()
    {
        if (consumer != null)
        {
            getLogger().info("Shutting down consumer");
            consumer.interrupt();
            try
            {
                consumer.join();
            } catch (InterruptedException ex)
            {
                getLogger().warning("Could not shut down consumer!");
            }
        }

        if (database != null)
        {
            getLogger().info("Closing remaining SQL connections");
            database.getDataSource().close();
        }

        instance = null;
    }

    public static LogBlock getInstance()
    {
        return instance;
    }

    public PlayerEntry getPlayer(int id)
    {
        return null;
    }

    public List<AbstractEntry> getChildren(MainEntry entry)
    {
        return null;
    }
}
