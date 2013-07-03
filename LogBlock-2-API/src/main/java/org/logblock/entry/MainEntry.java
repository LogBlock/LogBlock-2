package org.logblock.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.logblock.Action;
import org.logblock.storage.DataStore;

import java.util.Date;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class MainEntry extends AbstractEntry
{

    private Action action;
    private Date date;
    private int playerId;
    private int from;
    private byte fromData;
    private int to;
    private byte toData;
    private int x;
    private int y;
    private int z;
    //
    private List<AbstractEntry> children;
    private PlayerEntry player;

    /**
     * Used to create a main entry that happened "right now"
     * @param action What caused the entry
     * @param player The player which caused the entry
     * @param from The type of block which was originally there
     * @param fromData The data of the block which was originally there
     * @param to The type of block now
     * @param toData The data of the block now
     * @param x x coordinate of the block
     * @param y y coordinate of the block
     * @param z z coordinate of the block
     */
    public MainEntry(Action action, String player, int from, byte fromData, int to, byte toData, int x, int y, int z)
    {
        this.action = action;
        this.player = new PlayerEntry(player);
        this.from = from;
        this.fromData = fromData;
        this.to = to;
        this.toData = toData;
        this.x = x;
        this.y = y;
        this.z = z;
        this.date = new Date();
    }

    public MainEntry(Action action, Date date, int playerId, int from, byte fromData, int to, byte toData, int x, int y, int z)
    {
        this.action = action;
        this.date = date;
        this.playerId = playerId;
        this.from = from;
        this.fromData = fromData;
        this.to = to;
        this.toData = toData;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MainEntry(int id, Action action, Date date, int playerId, int from, byte fromData, int to, byte toData, int x, int y, int z)
    {
        super(id);
        this.action = action;
        this.date = date;
        this.playerId = playerId;
        this.from = from;
        this.fromData = fromData;
        this.to = to;
        this.toData = toData;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PlayerEntry getPlayer()
    {
        return (player == null) ? player = DataStore.getInstance().getPlayer(playerId) : player;
    }

    public List<AbstractEntry> getChildren()
    {
        return (children == null) ? children = DataStore.getInstance().getChildren(this) : children;
    }
}
