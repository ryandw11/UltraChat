package me.ryandw11.ultrachat.commands.chat;

import me.ryandw11.ultrachat.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This is the clear command for UltraChat.
 * <p>/chat clear</p>
 * <p>Permission:</p>
 * <code>
 *     ultrachat.command.clear
 * </code>
 */
public class ClearCommand implements SubCommand {
    @Override
    public String helpMessage() {
        return "&2/chat clear &3- &bClear all messages from the chat.";
    }

    @Override
    public String commandPermission() {
        return "ultrachat.command.clear";
    }

    @Override
    public boolean subCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!sender.hasPermission(commandPermission())) {
            sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
            return true;
        }

        for(int i = 0; i < 100; i++) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(" ");
            }
        }

        Bukkit.getServer().broadcastMessage(String.format("%s%s%s has cleared the chat!",
                ChatColor.GOLD,
                sender.getName(),
                ChatColor.GREEN));

        return false;
    }
}
