package me.ryandw11.ultrachat.listner;

import java.util.List;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.events.JsonChatEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
/**
 * @author Ryandw11
 */
public class NoSwear implements Listener {
	
	private UltraChat plugin;
	public NoSwear(){
		plugin = UltraChat.plugin;
	}
	

	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if(plugin.JSON) return;
		
		if(plugin.getConfig().getBoolean("Anti_Swear_Enabled")){	
			Player p = event.getPlayer();
		
		
		List <String> swear = plugin.getConfig().getStringList("Blocked_Words");
		int times = 0;
		
		String Message = " " + event.getMessage().toLowerCase().replace(".", "") + " ";
		
		for(String swearWord : swear){
			//Check if world chat is enabled
			if(Message.contains(swearWord + " ") && times == 0 || Message.contains(" " + swearWord + " ") && times == 0 || Message.contains(" " + swearWord) && times == 0 || Message.contains(swearWord)){
				//else do this:
				event.setCancelled(true);
				p.sendMessage(Lang.NO_SWEAR.toString());
				break;
			}
		}
		}
	}
	
	@EventHandler
	public void onJsonChat(JsonChatEvent event){
		if(plugin.getConfig().getBoolean("Anti_Swear_Enabled")){		
			Player p = event.getPlayer();
		
		
			List <String> swear = plugin.getConfig().getStringList("Blocked_Words");
			int times = 0;
		
			String Message = " " + event.getMessage().toLowerCase().replace(".", "") + " ";
		
			for(String swearWord : swear){
				//Check if world chat is enabled
				if(Message.contains(swearWord + " ") && times == 0 || Message.contains(" " + swearWord + " ") && times == 0 || Message.contains(" " + swearWord) && times == 0 || Message.contains(swearWord)){
					//else do this:
					event.setCancelled(true);
					p.sendMessage(Lang.NO_SWEAR.toString());
					break;
				}
			}
		}
	}

}
