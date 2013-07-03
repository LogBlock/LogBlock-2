package org.logblock.bukkit;

import org.logblock.bukkit.listener.BlockBreak;
import org.logblock.bukkit.listener.BlockPlace;
import org.logblock.bukkit.listener.EntityExplode;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.logblock.Configuration;
import org.logblock.Consumer;
import org.logblock.LogBlockPlugin;
import org.logblock.storage.DataStore;
import org.logblock.storage.mysql.MySQLDataStore;
import org.mcstats.Metrics;

import java.io.IOException;
import java.util.logging.Level;

public class LogBlock extends JavaPlugin implements LogBlockPlugin
{

    @Getter
    private static LogBlock instance;
    @Getter
    private DataStore dataStore;

    private Configuration config;
    private Consumer consumer;
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
            consumer = new Consumer(this);
            dataStore = new MySQLDataStore(this, config); // TODO: base this off the config option
        } catch (Throwable ex)
        {
            getLogger().severe("=========================");
            getLogger().severe("Error starting up LogBlock");
            getLogger().severe("Please provide this entire error if you are going to report this");
            getLogger().severe(ex.getMessage());
            getLogger().severe("");
            for (StackTraceElement element : ex.getStackTrace())
            {
                getLogger().severe(element.toString());
            }
            getLogger().severe("=========================");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new BlockBreak(this); // TODO: uhh, needs to be configurable (what is logged) and needs a proper method
        new EntityExplode(this);
        new BlockPlace(this);


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

        if (dataStore != null)
        {
            dataStore.onDisable();
        }

        instance = null;
    }
}
