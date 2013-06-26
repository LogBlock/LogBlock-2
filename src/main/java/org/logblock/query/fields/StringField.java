package org.logblock.query.fields;

import org.bukkit.command.CommandException;
import org.logblock.query.Field;
import org.logblock.query.Validator;

public class StringField extends Field<String>
{

    private String value;

    public StringField(Validator<String> validator, String... aliases)
    {
        super(validator, aliases);
    }

    @Override
    public void parse(String... args) throws CommandException
    {
        StringBuilder sb = new StringBuilder();
        for (String string : args)
        {
            sb.append(string).append(" ");
        }
        sb.setLength(sb.length() - 1);
        this.value = sb.toString();
    }

    @Override
    public String getValue()
    {
        return value;
    }
}
