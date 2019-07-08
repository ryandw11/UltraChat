package me.ryandw11.ultrachat.commands;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.JSON;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.UltraChatAPI;
import me.ryandw11.ultrachat.api.events.WorldChatEvent;
import me.ryandw11.ultrachat.formatting.PlayerFormatting;

public class World implements CommandExecutor {
	private UltraChat plugin;
	public World(){
		plugin = UltraChat.plugin;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§cThis command is for players only!");
			return true;
		}
		Player p = (Player) sender;
		if(!p.hasPermission("ultrachat.world")){
			p.sendMessage(Lang.NO_PERM.toString());
			return true;
		}
		UltraChatAPI uapi = new UltraChatAPI();
		PlayerFormatting pf = new PlayerFormatting(p);
		if(!uapi.isRangeJson()){
			WorldChatEvent e = new WorldChatEvent(p, this.getMessage(args, p), new HashSet<Player>( p.getWorld().getPlayers()));
			Bukkit.getServer().getPluginManager().callEvent(e);
			if(!e.isCancelled()){
				for(Player pl : e.getRecipients()){
					pl.sendMessage(pf.getWorld().replace("%player%", p.getDisplayName()).replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()) + pf.getColor() + e.getMessage());
				}
				Bukkit.getLogger().info(pf.getWorld().replace("%player%", p.getDisplayName()).replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()).replace('&', '§') + pf.getColor() + e.getMessage().replace('&', '§'));
			}
		}else{
			WorldChatEvent e = new WorldChatEvent(p, this.getMessage(args, p), new HashSet<Player>( p.getWorld().getPlayers()));
			Bukkit.getServer().getPluginManager().callEvent(e);
			if(!e.isCancelled()){
				JSON j = new JSON();
				for(Player pl : e.getRecipients()){
					pl.spigot().sendMessage(j.hoverMessage(pf.getWorld().replace("%player%", p.getDisplayName()).replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()), (ArrayList<String>) plugin.getConfig().get("World.json"), e.getMessage(), pf.getColor(), p));
				}
				Bukkit.getLogger().info(pf.getWorld().replace("%player%", p.getDisplayName()).replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()).replace('&', '§') + pf.getColor() + e.getMessage().replace('&', '§'));
			}
		}
		
		return false;
	}
	
	private String getMessage(String[] args, Player p){
		String end = "";
		for(String s : args){
			end += s + " ";
		}
		if(p.hasPermission("ultrachat.color"))
			return ChatColor.translateAlternateColorCodes('&', end);
		return end;
	}

}
