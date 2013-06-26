package org.logblock.query;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandException;
import org.logblock.query.fields.IntegerField;

import java.util.EnumMap;
import java.util.List;

public class Query
{
    public enum Fields
    {
        Radius(IntegerField.class);

        private Class<? extends Field> type;

        Fields(Class<? extends Field> type)
        {
            this.type = type;
        }
    }

    private static final List<Field> fields;

    static
    {
        ImmutableList.Builder<Field> b = ImmutableList.builder();

        b.add(new IntegerField(new Validator<Integer>()
        {
            @Override
            public void validate(Integer t)
            {
                if (t < 1)
                {
                    throw new CommandException("Radius can't be less than 1");
                }
            }
        }, "area"));

        fields = b.build();
    }

    private EnumMap<Fields, Field> args;

}
