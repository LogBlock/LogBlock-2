package org.logblock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor
{

    private static final Map<String, Method> commandMap = new HashMap<String, Method>();

    static
    {
        for (Method m : Commands.class.getDeclaredMethods())
        {
            if (Modifier.isStatic(m.getModifiers()))
            {
                Class<?>[] params = m.getParameterTypes();
                if (params.length == 2)
                {
                    if (params[0] == CommandSender.class && params[1] == String[].class)
                    {
                        AnnotatedCommand annotation = m.getAnnotation(AnnotatedCommand.class);
                        if (annotation != null)
                        {
                            commandMap.put(annotation.name(), m);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        label = label.toLowerCase();
        Method method = commandMap.get(label);
        if (method != null)
        {
            AnnotatedCommand annotation = method.getAnnotation(AnnotatedCommand.class);
            if (!sender.hasPermission("logblock." + label))
            {
                sender.sendMessage(ChatColor.RED + "You do not have permission to perform the specified command!");
            } else if (args.length < annotation.minArgs())
            {
                sender.sendMessage(ChatColor.RED + "Please check your argument count, the command you have requested requires " + annotation.minArgs() + " or more arguments.");
            } else if (args.length > annotation.maxArgs())
            {
                sender.sendMessage(ChatColor.RED + "Please check your argument count, the command you have requested only accepts up to " + annotation.maxArgs() + " arguments.");
            } else
            {
                invoker:
                {
                    try
                    {
                        method.invoke(null, sender, args);
                    } catch (CommandException e)
                    {
                        sender.sendMessage(ChatColor.RED + e.getMessage());
                        break invoker;
                    } catch (Exception ex)
                    {
                        throw new CommandException("Error occured whilst executing LogBlock subcommand " + label, ex);
                    }
                }
            }
        } else
        {
            sender.sendMessage(ChatColor.RED + "The command you have specified does not exist. Below is a list of all commands");
            help(sender, args);
        }
        return true;
    }

    @AnnotatedCommand(name = "help", usage = "help <page/command>", description = "Gets a list of commands or specific help for a single command", maxArgs = 1)
    public static void help(CommandSender sender, String[] args)
    {
        int numCommands = commandMap.values().size();
        int pages = (int) Math.ceil(numCommands / 8);

        if (args.length == 0)
        {
            if (pages > 1)
            {
                sender.sendMessage(ChatColor.GOLD + "==[Commands - Page 1/" + pages + "]==");
            } else
            {
                sender.sendMessage(ChatColor.GOLD + "=====[Commands]====");
            } for (Method method : commandMap.values())
            {
                AnnotatedCommand annotation = method.getAnnotation(AnnotatedCommand.class);
                sender.sendMessage(ChatColor.AQUA + annotation.name() + " (" + ChatColor.GREEN + annotation.usage() + ChatColor.AQUA + ") " + ChatColor.GOLD + "- " + annotation.description());
            }
            sender.sendMessage(ChatColor.GOLD + "===================");
        }

        if (args.length == 1)
        {
            try
            {
                int page = Integer.parseInt(args[0]);
            } catch (NumberFormatException e)
            {
                Method method = commandMap.get(args[0].toLowerCase());
                if (method != null)
                {
                    AnnotatedCommand annotation = method.getAnnotation(AnnotatedCommand.class);
                    sender.sendMessage(ChatColor.GOLD + "Help for " + ChatColor.AQUA + annotation.name());
                    sender.sendMessage(ChatColor.GOLD + "Usage: " + ChatColor.AQUA + annotation.usage());
                    sender.sendMessage(ChatColor.GOLD + "Description " + ChatColor.AQUA + annotation.description());
                } else
                {
                    throw new CommandException("Invalid page number and no command by the name of '" + args[0] + "'");
                }
            }
        }
    }

    @AnnotatedCommand(name = "version")
    public static void version(CommandSender sender, String[] args)
    {
        sender.sendMessage(ChatColor.GREEN + "This server is running LogBlock version " + LogBlock.getInstance().getDescription().getVersion());
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface AnnotatedCommand
    {

        String name();

        String usage() default "";

        String description() default "";

        int minArgs() default 0;

        int maxArgs() default 0;
    }
}
