package me.ryandw11.ultrachat.commands;

import java.util.ArrayList;

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
import me.ryandw11.ultrachat.api.events.GlobalChatEvent;
import me.ryandw11.ultrachat.formatting.PlayerFormatting;

public class Global implements CommandExecutor {
	private UltraChat plugin;
	public Global(){
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
		if(!p.hasPermission("ultrachat.global")){
			p.sendMessage(Lang.NO_PERM.toString());
			return true;
		}
		UltraChatAPI uapi = new UltraChatAPI();
		PlayerFormatting pf = new PlayerFormatting(p);
		if(!uapi.isRangeJson()){
			GlobalChatEvent e = new GlobalChatEvent(p, this.getMessage(args, p));
			Bukkit.getServer().getPluginManager().callEvent(e);
			if(!e.isCancelled()){
				Bukkit.broadcastMessage(pf.getGlobal().replace("%player%", p.getDisplayName()).replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()) + pf.getColor() + e.getMessage());
				Bukkit.getLogger().info(pf.getGlobal().replace("%player%", p.getDisplayName()).replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()).replace('&', '§') + pf.getColor() + e.getMessage().replace('&', '§'));
			}
		}else{
			GlobalChatEvent e = new GlobalChatEvent(p, this.getMessage(args, p));
			Bukkit.getServer().getPluginManager().callEvent(e);
			if(!e.isCancelled()){
				JSON j = new JSON();
				for(Player pl : Bukkit.getOnlinePlayers()){
					pl.spigot().sendMessage(j.hoverMessage(pf.getGlobal().replace("%player%", p.getDisplayName()).replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()), (ArrayList<String>) plugin.getConfig().get("Global.json"), e.getMessage(), pf.getColor(), p));
				}
				Bukkit.getLogger().info(pf.getGlobal().replace("%player%", p.getDisplayName()).replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()).replace('&', '§') + pf.getColor() + e.getMessage().replace('&', '§'));
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
