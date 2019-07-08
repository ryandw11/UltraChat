package me.ryandw11.ultrachat.listner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.events.JsonChatEvent;

/**
 * For 1.12 and below servers.
 */
public class Notify_1_12 implements Listener {
	private UltraChat plugin;
	public Notify_1_12(){
		plugin = UltraChat.plugin;
	}
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event){
		if(plugin.JSON) return;
		for(Player p : Bukkit.getOnlinePlayers()){
			if(event.getMessage().contains("@" + p.getName())){
				p.sendMessage(Lang.MENTION.toString().replace("%p", event.getPlayer().getDisplayName()));
			}
		}
	}
	
	@EventHandler
	public void onJsonChat(JsonChatEvent e){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(e.getMessage().contains("@" + p.getName())){
				p.sendMessage(Lang.MENTION.toString().replace("%p", e.getPlayer().getDisplayName()));
			}
		}
	}
}
