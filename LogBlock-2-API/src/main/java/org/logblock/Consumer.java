package org.logblock;

import org.logblock.entry.AbstractEntry;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer extends Thread
{

    private final BlockingQueue<AbstractEntry> queue = new LinkedBlockingQueue<AbstractEntry>();
    private LogBlockPlugin lb;

    public Consumer(LogBlockPlugin lb)
    {
        super("LogBlock Consumer");

        this.lb = lb;
    }

    @Override
    public void run()
    {
        while (!isInterrupted())
        {
            AbstractEntry entry;
            try
            {
                entry = queue.take();
            } catch (InterruptedException ex)
            {
                continue;
            }
            if (entry != null)
            {
                lb.getDataStore().write(entry);
            }
        }
    }

    public void queue(AbstractEntry entry)
    {
        if (entry == null)
        {
            throw new IllegalArgumentException("entry cannot be null!");
        }
        queue.add(entry);
    }
}
