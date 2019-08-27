package me.ryandw11.ultrachat.listner;

import java.util.List;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.managers.JComponentManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
/**
 * @author Ryandw11
 */
public class JoinListner implements Listener {
	
	private UltraChat plugin;
	public JoinListner(){
		plugin = UltraChat.plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		
		if(plugin.data.contains(p.getUniqueId().toString())){
			if(!(plugin.channel.contains(plugin.data.getString(p.getUniqueId() + ".channel")))){
				/*
				 * Fail safe so that if the player joins with an invalid channel it goes back to default.
				 */
				plugin.data.set(p.getUniqueId() + ".channel", plugin.getConfig().getString("Default_Channel"));
				plugin.saveFile();
				
			}else if(!(plugin.data.contains(p.getUniqueId().toString() + ".spy"))){
				plugin.data.set(p.getUniqueId().toString() + ".spy", false);
				plugin.saveFile();
			}
		}
		else{
			plugin.data.set(p.getUniqueId().toString() + ".color", "&f");
			plugin.data.set(p.getUniqueId().toString() + ".sjoin", false);
			plugin.data.set(p.getUniqueId().toString() + ".spy", false);
			plugin.data.set(p.getUniqueId().toString() + ".channel", plugin.getConfig().getString("Default_Channel"));
			plugin.saveFile();	
		}
		
		if(plugin.data.getBoolean(p.getUniqueId().toString() + ".sjoin")){
			event.setJoinMessage("");
			for(Player pl : Bukkit.getOnlinePlayers()){
				if(pl.hasPermission("ultrachat.sjoin.alert")){
					pl.sendMessage(Lang.SILENT_JOIN_MESSAGE.toString().replace("%p", p.getDisplayName()));
				}
			}
			
		}else{
		
		String Join = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Join_Message").replace("%player%", p.getName()));
		event.setJoinMessage(Join);
		}
		
		
	}
	
	/*
	 * On leave event
	 */
	@EventHandler
	public void onLeave(PlayerQuitEvent event){
		
		Player p = event.getPlayer();
		if(plugin.data.getBoolean(p.getUniqueId().toString() + ".sjoin")){
			event.setQuitMessage("");
			for(Player pl : Bukkit.getOnlinePlayers()){
				if(pl.hasPermission("ultrachat.sjoin.alert")){
					pl.sendMessage(Lang.SILENT_LEAVE_MESSAGE.toString().replace("%p",  p.getDisplayName()));
				}
			}
		}else{
		
		String leave = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Leave_Message").replace("%player%", p.getName()));
		event.setQuitMessage(leave);
		}
	}
	
	
	
	
	/*
	 * For MOTD
	 */
	@EventHandler
	public void MOTD(PlayerJoinEvent event){
		Player p = event.getPlayer();
		if(plugin.getConfig().getBoolean("Motd_Enabled")){
			List <String> motd = plugin.getConfig().getStringList("Motd");
			for(String OutPut : motd){
				String message = OutPut;
				message = plugin.papi.translatePlaceholders(message, p);
				p.spigot().sendMessage(JComponentManager.formatComponents(ChatColor.translateAlternateColorCodes('&', message), p));
			}

		}
		
	}
	
	/*
	 * If there is a new player.
	 */
	
	@EventHandler
	public void NewPlayer(PlayerJoinEvent event){
		Player p = event.getPlayer();	
		if(!(p.hasPlayedBefore()) && !(plugin.getConfig().getString("New_Player").equalsIgnoreCase("none"))){
			String msg = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("New_Player").replace("%player%", p.getDisplayName()));
			for(Player pl : Bukkit.getOnlinePlayers()) {
				pl.spigot().sendMessage(JComponentManager.formatComponents(msg, p));
			}
		}
		
	}
	
	
	
}
