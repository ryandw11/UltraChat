package me.ryandw11.ultrachat.formatting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UnknownFormatConversionException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.JSON;
import me.ryandw11.ultrachat.api.UltraChatAPI;
import me.ryandw11.ultrachat.api.events.JsonChatEvent;

public class Range implements Listener{
	private UltraChat plugin;
	public Range(){
		plugin = UltraChat.plugin;
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		PlayerFormatting pf = new PlayerFormatting(p);
		UltraChatAPI uapi = new UltraChatAPI();
		if(p.hasPermission("ultrachat.chat.color")){
			e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
		}
		if(!uapi.isRangeJson()){
			e.getRecipients().removeAll(Bukkit.getOnlinePlayers());
			e.getRecipients().addAll(getNearbyPlayers(p));
			e.getRecipients().add(p);
			try{
				e.setFormat(pf.getLocal().replace("%player%", "%s").replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()) + pf.getColor() + "%s");
			}catch(UnknownFormatConversionException ex){
				Bukkit.getLogger().severe("A fatal error has occured! The local formatting is not correct!");
			}
		}else{
			e.getRecipients().removeAll(Bukkit.getOnlinePlayers());
			e.getRecipients().addAll(getNearbyPlayers(p));
			e.getRecipients().add(p);
			e.setCancelled(true);
			
			JsonChatEvent event = new JsonChatEvent(p, e.getMessage(), new HashSet<Player>(e.getRecipients()));
			Bukkit.getServer().getPluginManager().callEvent(event);
			if(!event.isCancelled()){
				JSON j = new JSON();
				for(Player pl : event.getRecipients()){
					pl.spigot().sendMessage(j.hoverMessage(pf.getLocal().replace("%player%", p.getDisplayName()).replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()), (ArrayList<String>) plugin.getConfig().get("Local.json"), event.getMessage(), pf.getColor(), p));
				}
			}
		}
	}
	
	public ArrayList<Player> getNearbyPlayers(Player pl){
        ArrayList<Player> nearby = new ArrayList<Player>();
        double range = plugin.getConfig().getDouble("Local.range");
        for (Entity e : pl.getNearbyEntities(range, range, range)){
            if (e instanceof Player){
                nearby.add((Player) e);
            }
        }
        return nearby;
    }
}
