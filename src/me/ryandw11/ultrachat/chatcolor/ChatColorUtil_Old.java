package me.ryandw11.ultrachat.chatcolor;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class ChatColorUtil_Old implements ChatColorUtils {

    @Override
    public String translateChatColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public String translateChatColor(String message, Player p) {
        if(p.hasPermission("ultrachat.chat.color"))
            return ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

    @Override
    public ChatColor translateChatCode(String code) {
        if(code.startsWith("&")){
            return ChatColor.getByChar(code.replace("&", "").charAt(0));
        }
        return ChatColor.RED;
    }

    @Override
    public boolean isChatCode(String code) {
        return false;
    }
}
