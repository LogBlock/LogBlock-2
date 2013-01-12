package org.logblock.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PlayerEntry extends AbstractEntry
{

    private final String name;

    public PlayerEntry(int id, String name)
    {
        super(id);
        this.name = name;
    }
}
