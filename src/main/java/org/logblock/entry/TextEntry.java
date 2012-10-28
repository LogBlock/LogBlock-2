package org.logblock.entry;

public class TextEntry extends AbstractEntry
{

    private final String text;

    public TextEntry(int id, String text)
    {
        super(id);
        this.text = text;
    }
}
