package me.ryandw11.ultrachat.chatcolor;

import me.ryandw11.ultrachat.formatting.PlayerFormatting;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface ChatColorUtils {
    String translateChatColor(String message);
    String translateChatColor(Player p, String message);
    Map<String, String> splitColors(String message, PlayerFormatting pf);
    ChatColor translateChatCode(String code);
    boolean isChatCode(String code);
    ChatColor translateChatCode(Player p, String message);
}
