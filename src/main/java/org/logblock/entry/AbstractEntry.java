package org.logblock.entry;

import lombok.Data;

@Data
public abstract class AbstractEntry
{

    private int id;

    public AbstractEntry()
    {
    }

    public AbstractEntry(int id)
    {
        this.id = id;
    }
}
