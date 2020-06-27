package me.ryandw11.ultrachat.pluginhooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.earth2me.essentials.Essentials;

import me.ryandw11.ultrachat.api.events.UltraChatEvent;

public class EssentialsMute implements Listener{
	
	@EventHandler
	public void jsonChat(UltraChatEvent e){	
		Player p = e.getPlayer();
		Essentials ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
		assert ess != null;
		if(ess.getUser(p).isMuted()){
			e.setCancelled(true);
		}
	}
}
