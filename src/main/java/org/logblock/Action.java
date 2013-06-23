package org.logblock;

import lombok.Getter;

public enum Action
{

    BLOCK_BREAK(1),
    BLOCK_BURN(2),
    BLOCK_FALL(3),
    BLOCK_IGNITE(4),
    BLOCK_PISTON(5),
    BLOCK_PLACE(6),
    CONTAINER_ACCESS(7),
    ENTITY_CHANGE(8),
    EXPLOSION(9),
    LEAF_DECAY(10),
    NATURAL_BREAK(11),
    PLAYER_BUCKET(12),
    PLAYER_INTERACT(13),
    SIGN_TEXT(14),
    STRUCTURE_GROWTH(15),
    WORLDEDIT(16);

    @Getter
    private int actionId;

    private Action(int id)
    {
        this.actionId = id;
    }
}
