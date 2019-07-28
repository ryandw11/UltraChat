package me.ryandw11.ultrachat.formatting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.ChatType;
import me.ryandw11.ultrachat.api.events.UltraChatEvent;
import me.ryandw11.ultrachat.api.events.properties.ChannelProperties;
/**
 * Channels without any kind of json involved.
 * @author Ryandw11
 *
 */
public class Channels implements Listener {
	private UltraChat plugin;
	public Channels(){
		plugin = UltraChat.plugin;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		PlayerFormatting pf = new PlayerFormatting(e.getPlayer());
		Player p = e.getPlayer();
		
		String channel = plugin.data.getString(p.getUniqueId() + ".channel");
		if(!plugin.channel.getBoolean(channel + ".always_appear")){
			e.getRecipients().removeAll(Bukkit.getOnlinePlayers());
			if(p.hasPermission("ultrachat.chat.color")){
				e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
			}
			for(Player pl : Bukkit.getOnlinePlayers()){
				if(plugin.data.getString(pl.getUniqueId() + ".channel").equals(channel)){
					if(pl.hasPermission(plugin.channel.getString(channel + ".permission")) || plugin.channel.getString(channel + ".permission").equalsIgnoreCase("none")){
						e.getRecipients().add(pl);
					}
				}
			}
		}
		
		ChannelProperties cp = new ChannelProperties(false, channel);
		UltraChatEvent uce = new UltraChatEvent(p, e.getMessage(), e.getRecipients(), ChatType.CHANNEL, cp);
		Bukkit.getServer().getPluginManager().callEvent(uce);
		
		if (uce.isCancelled()) {
			e.setCancelled(true);
			return;
		}
		if(uce.getRecipients() != e.getRecipients()) {
			e.getRecipients().removeAll(Bukkit.getOnlinePlayers());
			e.getRecipients().addAll(uce.getRecipients());
		}
		
		e.setMessage(uce.getMessage());
		
		
		e.setFormat(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', plugin.channel.getString(channel + ".prefix")) + ChatColor.translateAlternateColorCodes('&', plugin.channel.getString(channel + ".format").replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()).replace("%player%", "%s") + pf.getColor() + "%s")));	
	}
}
