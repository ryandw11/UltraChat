package me.ryandw11.ultrachat.chatcolor;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.formatting.PlayerFormatting;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.MutablePair;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatColorUtil_Latest implements ChatColorUtils {

    private UltraChat plugin;
    public ChatColorUtil_Latest(UltraChat plugin){
        this.plugin = plugin;
    }

    @Override
    public String translateChatColor(String message) {
        String finalMessage = translateHexColor(message);
        finalMessage = plugin.chatColorManager.translateMapColors(finalMessage);
        return finalMessage;
    }

    @Override
    public String translateChatColor(Player p, String message) {
        String finalMessage = message;
        if(p.hasPermission("ultrachat.chatcolor.hex"))
            finalMessage = translateHexColor(finalMessage);
        if(p.hasPermission("ultrachat.chatcolor.colorcodes"))
            finalMessage = plugin.chatColorManager.translateMapColors(finalMessage);
        return finalMessage;
    }

    @Override
    public Map<String, String> splitColors(String message, PlayerFormatting pf) {
        List<String> normalMessage = new ArrayList<>(Arrays.asList(message.split("\\{(#[^}]+)}|&[^&]")));
        List<String> colorCodes = new ArrayList<>();
        // TODO fix this
        colorCodes.add(pf.getColor().toString());
        Matcher m = Pattern.compile("\\{(#[^}]+)}|&[^&]").matcher(message);
        while(m.find()) {
            colorCodes.add(m.group());
        }
        for(int i = 0; i < colorCodes.size(); i++){
            if(!isChatCode(colorCodes.get(i)) && i != 0){
                normalMessage.set(i-1, normalMessage.get(i-1) +colorCodes.get(i)+normalMessage.get(i));
                normalMessage.remove(i);
                colorCodes.remove(i);
                i--;
            }
        }
        Map<String, String> output = new LinkedHashMap<>();
        for(int i = 0; i < normalMessage.size(); i++){
            output.put(normalMessage.get(i), colorCodes.get(i));
        }
        return output;
    }

    @Override
    public ChatColor translateChatCode(String code) {
        if(code.startsWith("&")){
            return plugin.chatColorManager.getChatColor(code);
        }
        String hexCode = code.replace("{", "").replace("}", "");
        try{
            return ChatColor.of(hexCode);
        }catch(IllegalArgumentException ex){
            return ChatColor.RED;
        }
    }

    @Override
    public boolean isChatCode(String code) {
        if(code.startsWith("&")){
            return plugin.chatColorManager.getMap().containsKey(code);
        }
        String hexCode = code.replace("{", "").replace("}", "");
        try{
            ChatColor.of(hexCode);
        }catch(IllegalArgumentException ex){
            return false;
        }
        return true;
    }

    @Override
    public ChatColor translateChatCode(Player p, String code) {
        return null;
    }

    protected static String translateHexColor(String message){
        if(!message.contains("{"))
            return message;
        StringBuilder finalMessage = new StringBuilder();
        StringBuilder interior = new StringBuilder();
        boolean readInterior = false;
        for (int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(c == '{' && !readInterior){
                readInterior = true;
            }else if(!readInterior){
                finalMessage.append(c);
            } else if(c == '{'){
                finalMessage.append('{');
                finalMessage.append(interior);
                interior = new StringBuilder();
            }else if(c == '}' && interior.toString().contains("#")){
                readInterior = false;
                try {
                    finalMessage.append(ChatColor.of(interior.toString()));
                }catch(IllegalArgumentException ex){
                    finalMessage.append("{");
                    finalMessage.append(interior);
                    finalMessage.append("}");
                }finally{
                    interior = new StringBuilder();
                }

            }else if(c == '}'){
                readInterior = false;
                finalMessage.append("{");
                finalMessage.append(interior);
                finalMessage.append("}");
                interior = new StringBuilder();
            }
            else{
                interior.append(c);
            }
        }
        if(interior.toString().length() > 0){
            finalMessage.append('{');
            finalMessage.append(interior);
        }
        return finalMessage.toString();
    }
}
