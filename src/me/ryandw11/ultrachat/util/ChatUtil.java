package me.ryandw11.ultrachat.util;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.formatting.PlayerFormatting;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

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

    public static String translateColorCodes(Player p, String message){
        return UltraChat.plugin.chatColorUtil.translateChatColor(p, message);
    }

    public static ComponentBuilder translateColorCodesChat(String message, PlayerFormatting pf){
        Map<String, String> result = UltraChat.plugin.chatColorUtil.splitColors(message, pf);
        ComponentBuilder builder = new ComponentBuilder();
        for(Map.Entry<String, String> s : result.entrySet()){
            TextComponent textComponent = new TextComponent(s.getKey());
            textComponent.setColor(UltraChat.plugin.chatColorUtil.translateChatCode(s.getValue()));
            builder.append(textComponent);
        }
        return builder;
    }
}
