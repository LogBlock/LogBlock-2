package org.logblock.query.fields;

import org.bukkit.command.CommandException;
import org.logblock.query.Field;
import org.logblock.query.Validator;

public class IntegerField extends Field<Integer>
{

    private int value;

    public IntegerField(Validator<Integer> validator, String... aliases)
    {
        super(validator, aliases);
    }

    @Override
    public void parse(String... args)
    {
        try
        {
            value = Integer.parseInt(args[0]);
        } catch (NumberFormatException e)
        {
            throw new CommandException(args[0] + " isn't a valid number");
        }
    }

    @Override
    public Integer getValue()
    {
        return value;
    }
}
