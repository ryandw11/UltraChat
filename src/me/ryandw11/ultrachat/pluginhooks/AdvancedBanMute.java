package me.ryandw11.ultrachat.pluginhooks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.manager.UUIDManager;
import me.ryandw11.ultrachat.api.GlobalChatEvent;
import me.ryandw11.ultrachat.api.JsonChatEvent;
import me.ryandw11.ultrachat.api.WorldChatEvent;

public class AdvancedBanMute implements Listener{
	
	@EventHandler
	public void jsonChat(JsonChatEvent e){
		Player p = e.getPlayer();
		if(PunishmentManager.get().isMuted(UUIDManager.get().getUUID(p.getName()))){	
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void globalChat(GlobalChatEvent e){
		Player p = e.getPlayer();
		if(PunishmentManager.get().isMuted(UUIDManager.get().getUUID(p.getName()))){	
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void worldChat(WorldChatEvent e){
		Player p = e.getPlayer();
		if(PunishmentManager.get().isMuted(UUIDManager.get().getUUID(p.getName()))){	
			e.setCancelled(true);
		}
	}
}
