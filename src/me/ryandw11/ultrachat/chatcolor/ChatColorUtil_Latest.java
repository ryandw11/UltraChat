package me.ryandw11.ultrachat.chatcolor;

import me.ryandw11.ultrachat.UltraChat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

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
    public String translateChatColor(String message, Player p) {
        String finalMessage = message;
        if(p.hasPermission("ultrachat.chat.hex"))
            finalMessage = translateHexColor(message);
        if(p.hasPermission("ultrachat.chat.color"))
            finalMessage = plugin.chatColorManager.translateMapColors(finalMessage);
        return finalMessage;
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
