package org.logblock;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Commands implements CommandExecutor
{

    private static final int COMMANDS_PER_PAGE = 8;

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
                            Permission perm = new Permission(annotation.name(), "Access to '/lb " + annotation.name() + "' command");
                            perm.addParent("logblock.command.*", true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length < 1)
        {
            help(sender, new String[]{});
            return false;
        }
        label = args[0];
        Method method = commandMap.get(label);
        if (method != null)
        {
            if (!sender.hasPermission("logblock.command." + label))
            {
                sender.sendMessage(ChatColor.RED + "You do not have permission to perform the specified command!");
            } else
            {
                String[] commandArgs = new String[args.length - 1];
                System.arraycopy(args, 1, commandArgs, 0, args.length - 1);
                try
                {
                    method.invoke(null, sender, commandArgs);
                } catch (CommandException e)
                {
                    sender.sendMessage(ChatColor.RED + e.getMessage());
                } catch (Exception ex)
                {
                    throw new CommandException("Error occurred whilst executing LogBlock subcommand " + label, ex);
                }
            }
        } else
        {
            sender.sendMessage(ChatColor.RED + "The command you have specified does not exist. Below is a list of all commands");
            help(sender, new String[]{});
        }
        return true;
    }

    @AnnotatedCommand(name = "help", usage = "help <page/command>", description = "Gets a list of commands or specific help for a single command")
    public static void help(CommandSender sender, String[] args)
    {
        int numCommands = commandMap.values().size();
        int pages = (int) Math.ceil(numCommands / COMMANDS_PER_PAGE);

        if (args.length == 0)
        {
            if (pages > 1)
            {
                sender.sendMessage(ChatColor.GOLD + "==[Commands - Page 1/" + pages + "]==");
            } else
            {
                sender.sendMessage(ChatColor.GOLD + "=====[Commands]====");
            }
            StringBuilder commandLine = new StringBuilder();
            for (Method method : commandMap.values())
            {
                AnnotatedCommand annotation = method.getAnnotation(AnnotatedCommand.class);
                commandLine.setLength(0);
                commandLine.append(ChatColor.AQUA).append(annotation.name());
                if (!annotation.usage().equals(""))
                {
                    commandLine.append(" (").append(ChatColor.GREEN).append("/lb ").append(annotation.usage()).append(ChatColor.AQUA).append(")");
                }
                if (!annotation.description().equals(""))
                {
                    commandLine.append(ChatColor.GOLD).append(" - ").append(annotation.description());
                }
                sender.sendMessage(commandLine.toString());
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
                    if (annotation.usage().equals("")) {
                        sender.sendMessage(ChatColor.GOLD + "Usage: " + ChatColor.AQUA + "/lb " + annotation.name());
                    } else {
                        sender.sendMessage(ChatColor.GOLD + "Usage: " + ChatColor.AQUA + "/lb " + annotation.usage());
                    }
                    sender.sendMessage(ChatColor.GOLD + "Description: " + ChatColor.AQUA + annotation.description());
                } else
                {
                    throw new CommandException("No command or page under '" + args[0] + "'");
                }
            }
        }
    }

    @AnnotatedCommand(name = "version", description = "Gets the version of logblock running on this server")
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

    }
}
