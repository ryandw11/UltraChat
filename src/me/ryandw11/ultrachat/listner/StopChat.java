package me.ryandw11.ultrachat.listner;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.events.UltraChatEvent;
/**
 * Prevent players from chatting when the chat is stopped.
 * @author Ryandw11
 *
 */
public class StopChat implements Listener {
	
	private UltraChat plugin;
	public StopChat(){
		plugin = UltraChat.plugin;
	}
	
	@EventHandler
	public void onChat(UltraChatEvent e){
		Player p = e.getPlayer();
		if(plugin.chatStop){
			if(!p.hasPermission("ultrachat.stopchat.bypass")){
				e.setCancelled(true);
				p.sendMessage(Lang.STOP_CHAT_MESSAGE.toString());
			}
		}
	}
	
	
	
}
