package me.ryandw11.ultrachat.chatcolor;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public interface ChatColorUtils {
    String translateChatColor(String message);
    String translateChatColor(String message, Player p);
    ChatColor translateChatCode(String code);
    boolean isChatCode(String code);
}
