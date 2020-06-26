package me.ryandw11.ultrachat.util;

import me.ryandw11.ultrachat.UltraChat;
import net.md_5.bungee.api.ChatColor;

/**
 * This is a utility class to make chat easier.
 */
public class ChatUtil {

    /**
     * Translates the message into color codes.
     * <p>This also translates hex color codes: {#FFFFFF}</p>
     * @param message The message to translate
     * @return The translated message.
     */
    public static String translateColorCodes(String message){
        return UltraChat.plugin.chatColorUtil.translateChatColor(message);
    }

    public static ChatColor translateColorCode(String code){
        return UltraChat.plugin.chatColorUtil.translateChatCode(code);
    }
}
