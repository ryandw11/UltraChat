package me.ryandw11.ultrachat.chatcolor;

import me.ryandw11.ultrachat.UltraChat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class handles the chatcolor.yml file.
 */
public class ChatColorManager {
    private Map<String, ChatColor> colorCodeMap;

    public ChatColorManager(UltraChat plugin, ConfigurationSection section){
        colorCodeMap = new HashMap<>();
        for(String s : section.getKeys(false)){
            String color = Objects.requireNonNull(section.getString(s));
            if(color.equalsIgnoreCase("default"))
                colorCodeMap.put(s, ChatColor.getByChar(s.replace("&", "").charAt(0)));
            else{
                try{
                    color = color.replace("{", "").replace("}", "");
                    colorCodeMap.put(s, ChatColor.of(color));
                }catch(IllegalArgumentException ex){
                    plugin.getLogger().warning("Invalid chat color for " + s);
                }
            }
        }
    }

    public Map<String, ChatColor> getMap(){
        return colorCodeMap;
    }

    public String translateMapColors(String message){
        String finalMessage = message;
        for(Map.Entry<String, ChatColor> entry : colorCodeMap.entrySet()){
            finalMessage = finalMessage.replace(entry.getKey(), entry.getValue().toString());
        }
        return finalMessage;
    }

    public ChatColor getChatColor(String s){
        return colorCodeMap.get(s);
    }
}
