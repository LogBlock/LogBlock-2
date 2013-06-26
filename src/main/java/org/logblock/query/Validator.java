package org.logblock.query;

import org.bukkit.command.CommandException;

public abstract class Validator<Type>
{
    public abstract void validate (Type t) throws CommandException;
}
