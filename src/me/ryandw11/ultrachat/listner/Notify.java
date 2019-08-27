package me.ryandw11.ultrachat.listner;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.events.UltraChatEvent;
/**
 * 
 * @author Ryandw11
 * Updated for 1.13
 *
 */
public class Notify implements Listener {
	
	@EventHandler
	public void onJsonChat(UltraChatEvent e){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(e.getMessage().contains("@" + p.getName())){
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 0);
				p.sendMessage(Lang.MENTION.toString().replace("%p", e.getPlayer().getDisplayName()));
			}
		}
	}

	
}
