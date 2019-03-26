package me.ryandw11.ultrachat.pluginhooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.earth2me.essentials.Essentials;
import me.ryandw11.ultrachat.api.GlobalChatEvent;
import me.ryandw11.ultrachat.api.JsonChatEvent;
import me.ryandw11.ultrachat.api.WorldChatEvent;

public class EssentialsMute implements Listener{
	
	@EventHandler
	public void jsonChat(JsonChatEvent e){	
		Player p = e.getPlayer();
		Essentials ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
		if(ess.getUser(p).isMuted()){	
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void globalChat(GlobalChatEvent e){
		Player p = e.getPlayer();
		Essentials ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
		if(ess.getUser(p).isMuted()){	
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void worldChat(WorldChatEvent e){
		Player p = e.getPlayer();
		Essentials ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
		if(ess.getUser(p).isMuted()){	
			e.setCancelled(true);
		}
	}
}
