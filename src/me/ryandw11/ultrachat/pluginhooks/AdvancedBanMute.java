package me.ryandw11.ultrachat.pluginhooks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.manager.UUIDManager;
import me.ryandw11.ultrachat.api.events.UltraChatEvent;

public class AdvancedBanMute implements Listener{
	
	@EventHandler
	public void jsonChat(UltraChatEvent e){
		Player p = e.getPlayer();
		if(PunishmentManager.get().isMuted(UUIDManager.get().getUUID(p.getName()))){	
			e.setCancelled(true);
		}
	}
}
