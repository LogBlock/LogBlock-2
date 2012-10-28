package org.logblock.entry;

public class PlayerEntry extends AbstractEntry
{

    private final String name;

    public PlayerEntry(int id, String name)
    {
        super(id);
        this.name = name;
    }
}
