package org.logblock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.logblock.entry.AbstractEntry;

public class Consumer extends Thread
{

    private final BlockingQueue<AbstractEntry> queue = new LinkedBlockingQueue<AbstractEntry>();

    public Consumer()
    {
        super("LogBlock Consumer");
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
                // do stuff with it
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
