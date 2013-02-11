package org.logblock.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ItemEntry extends AbstractEntry
{

    private short type;
    private short amount;
    private short damage;
    private short slot;
    private byte[] nbt;

    public ItemEntry(int id, short type, short amount, short damage, byte[] nbt)
    {
        super(id);
        this.type = type;
        this.amount = amount;
        this.damage = damage;
        this.nbt = nbt;
    }
}
