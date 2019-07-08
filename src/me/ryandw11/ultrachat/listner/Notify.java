package me.ryandw11.ultrachat.listner;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.events.JsonChatEvent;
/**
 * 
 * @author Ryandw11
 * Updated for 1.13
 *
 */
public class Notify implements Listener {

	private UltraChat plugin;
	public Notify(){
		plugin = UltraChat.plugin;
	}
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event){
		if(plugin.JSON) return;
		for(Player p : Bukkit.getOnlinePlayers()){
			if(event.getMessage().contains("@" + p.getName())){
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 0);
				p.sendMessage(Lang.MENTION.toString().replace("%p", event.getPlayer().getDisplayName()));
			}
		}
	}
	
	@EventHandler
	public void onJsonChat(JsonChatEvent e){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(e.getMessage().contains("@" + p.getName())){
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 0);
				p.sendMessage(Lang.MENTION.toString().replace("%p", e.getPlayer().getDisplayName()));
			}
		}
	}

	
}
