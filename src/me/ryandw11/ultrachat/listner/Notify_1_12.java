package me.ryandw11.ultrachat.listner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.events.UltraChatEvent;

/**
 * For 1.12 and below servers.
 */
public class Notify_1_12 implements Listener {
	
	@EventHandler
	public void onJsonChat(UltraChatEvent e){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(e.getMessage().contains("@" + p.getName())){
				p.sendMessage(Lang.MENTION.toString().replace("%p", e.getPlayer().getDisplayName()));
			}
		}
	}
}
