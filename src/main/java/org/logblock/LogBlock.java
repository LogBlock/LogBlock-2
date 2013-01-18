package org.logblock;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.logblock.entry.AbstractEntry;
import org.logblock.entry.EntryManager;
import org.logblock.entry.MainEntry;
import org.logblock.entry.PlayerEntry;
import org.mcstats.Metrics;

public class LogBlock extends JavaPlugin
{

    @Getter
    private static LogBlock instance;
    private Configuration config;
    private Consumer consumer;
    private DatabaseManager database;
    @Getter
    private EntryManager entryManager;
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
        } catch (Exception ex)
        {
            getLogger().severe("=========================");
            getLogger().severe("Error starting up logblock");
            getLogger().severe(ex.getMessage());
            getLogger().severe("");
            ex.printStackTrace();
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
}
