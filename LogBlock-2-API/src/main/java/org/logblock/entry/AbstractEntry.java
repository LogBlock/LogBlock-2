package org.logblock.entry;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractEntry implements Serializable
{

    private int id = -1;

    public AbstractEntry()
    {
    }

    public AbstractEntry(int id)
    {
        this.id = id;
    }
}
