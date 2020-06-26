package me.ryandw11.ultrachat.chatcolor;

import net.md_5.bungee.api.ChatColor;

public interface ChatColorUtils {
    String translateChatColor(String message);
    ChatColor translateChatCode(String code);
    boolean isChatCode(String code);
}
