package me.ryandw11.ultrachat.api;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.ryandw11.ultrachat.formatting.PlayerFormatting;
//import me.ryandw11.ultrachat.UltraChat;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ComponentBuilder.FormatRetention;




public class JSON {
	
	//private UltraChat plugin;
	public JSON(){
		//plugin = UltraChat.plugin;
	}
	
	/**
	 * Builds the hover message.
	 * @param msg The play name and format around it.
	 * @param lore The lore of the text.
	 * @param p The player it is being applied to.
	 * @return TextComponent with hover message.
	 */
	public BaseComponent[] hoverMessage(String msg, ArrayList<String> lore, String chat, ChatColor color, Player p){
		PlayerFormatting pf = new PlayerFormatting(p);
		int i = 0;
		int l = lore.size() - 1;
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		ComponentBuilder build = new ComponentBuilder("");
		for(String s : lore){
			build.append(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', s)));
			if(i<l)
				build.append("\n");
			i++;
		}
		ComponentBuilder name = new ComponentBuilder(msg);
		name.event( new HoverEvent( HoverEvent.Action.SHOW_TEXT, build.create() ) );
		TextComponent chatMsg;
		if(color != null){
			chatMsg = new TextComponent(chat); //Important May break!
			chatMsg.setColor(color); 
		}else{
			String smgsd = ChatColor.translateAlternateColorCodes('&', pf.color + chat);
			chatMsg = new TextComponent(smgsd);
		}
		name.append(this.getMsg(chat, color, p), FormatRetention.FORMATTING);
		return name.create();
	}
	/**
	 * Format the chat.
	 * @param chat The player message.
	 * @param color The player's chat color
	 * @return Chat message with string.
	 */
	public String getMsg(String chat, ChatColor color, Player p){
		String msg = "";
		if(color != null){
			if(p.hasPermission("ultrachat.chat.color")){
				msg = ChatColor.translateAlternateColorCodes('&', color + chat);
			}else{
				msg = ChatColor.translateAlternateColorCodes('&', color + chat);
			}
		}else{
			msg = ChatColor.translateAlternateColorCodes('&', chat);
		}
		return msg;
	}
}
