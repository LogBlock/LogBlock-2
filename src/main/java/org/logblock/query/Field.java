package org.logblock.query;

import com.google.common.collect.ImmutableSet;
import org.bukkit.command.CommandException;

import java.util.Set;

public abstract class Field<Type>
{
    private final Validator<Type> validator;
    private final Set<String> aliases;

    public abstract void parse(String... args) throws CommandException;
    public abstract Type getValue();

    public Field (String name, Validator<Type> validator, String... aliases) {
        this.validator = validator;
        this.aliases = ImmutableSet.<String>builder().add(aliases).build();
    }

}
