package me.ryandw11.ultrachat.chatcolor;

import me.ryandw11.ultrachat.formatting.PlayerFormatting;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class ChatColorUtil_Old implements ChatColorUtils {

    @Override
    public String translateChatColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public String translateChatColor(Player p, String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public Map<String, String> splitColors(String message, PlayerFormatting pf) {
        return null;
    }

    @Override
    public net.md_5.bungee.api.ChatColor translateChatCode(String code) {
        return null;
    }

    @Override
    public boolean isChatCode(String code) {
        return false;
    }

    @Override
    public net.md_5.bungee.api.ChatColor translateChatCode(Player p, String message) {
        return null;
    }
}
