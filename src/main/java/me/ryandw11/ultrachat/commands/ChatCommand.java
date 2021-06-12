package me.ryandw11.ultrachat.commands;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.commands.chat.ClearCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * The main chat command for the plugin.
 */
public class ChatCommand implements CommandExecutor {
    private final UltraChat plugin;
    private final CommandHandler commandHandler;

    public ChatCommand(UltraChat plugin) {
        this.plugin = plugin;
        this.commandHandler = new CommandHandler();
        this.commandHandler.registerCommand(new ClearCommand(), "clear");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        try {
            if (args.length != 0)
                return commandHandler.handleCommand(sender, cmd, s, args);
        } catch (IllegalArgumentException ex) {
            // Do nothing as this exception was triggered purposefully.
        }

        if (sender.hasPermission("ultrachat.command.help")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&3=============[&2UltraChat&3]============="));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Created by: &2Ryandw11"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&3Version: &2" + plugin.getDescription().getVersion()));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&3Github wiki:&2 https://github.com/ryandw11/CustomStructures/wiki"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Commands:"));

            for (SubCommand command : commandHandler.getSubCommands()) {
                if (!sender.hasPermission(command.commandPermission())) continue;
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        command.helpMessage()));
            }
        }
        return false;
    }
}
