package org.logblock.entry;

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
