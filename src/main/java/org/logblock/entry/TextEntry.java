package org.logblock.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TextEntry extends AbstractEntry
{

    private final String text;

    public TextEntry(int id, String text)
    {
        super(id);
        this.text = text;
    }
}
