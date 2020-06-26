package me.ryandw11.ultrachat.chatcolor;

import net.md_5.bungee.api.ChatColor;

public class ChatColorUtil_Old implements ChatColorUtils {

    @Override
    public String translateChatColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public ChatColor translateChatCode(String code) {
        return null;
    }

    @Override
    public boolean isChatCode(String code) {
        return false;
    }
}
