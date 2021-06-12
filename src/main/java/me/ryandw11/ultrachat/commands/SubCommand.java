package me.ryandw11.ultrachat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {

    /**
     * Get the help message for the command.
     * @return The help message for the command.
     */
    String helpMessage();

    /**
     * The permission for the command.
     * @return The permission for the command.
     */
    String commandPermission();

    /**
     * The sub command.
     *
     * @param sender The sender.
     * @param cmd    The command.
     * @param s      The command string.
     * @param args   The arguments. (Does not include the subcommand name. So args.length for `/chat color` is 0.)
     * @return If the sub-command is valid.
     */
    boolean subCommand(CommandSender sender, Command cmd, String s, String[] args);
}
