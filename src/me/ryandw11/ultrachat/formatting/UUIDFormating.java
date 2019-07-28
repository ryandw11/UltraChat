package me.ryandw11.ultrachat.formatting;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Util;
import net.md_5.bungee.api.ChatColor;

/**
 * 
 * @author Ryandw11
 * @since 2.4
 */
public class UUIDFormating {
	
private UltraChat plugin;
	
	/**
	 * Get the formatting for an offline player.
	 * @param ud The UUID for the player.
	 * @param world The world the plugin grabs the prefixes for.
	 */
	public UUIDFormating(UUID ud, String world){
		plugin = UltraChat.plugin;
		
		OfflinePlayer op = Bukkit.getOfflinePlayer(ud);
		
		color = ChatColor.translateAlternateColorCodes('&', plugin.data.getString(ud + ".color"));
		prefix = ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerPrefix(world, op));
		suffix = ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerSuffix(world, op));
		formatOp = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Custom_Chat.Op_Chat.Format"));
		defaults = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Custom_Chat.Default_Chat.Format"));
		global = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Global.format"));
		this.world = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("World.format"));
		local = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Local.format"));
	}
	
	private String prefix;
	private String suffix;
	public String color;
	private String formatOp;
	private String defaults;
	private String global;
	private String world;
	private String local;
	
	public String getGlobal(){
		return global;
	}
	
	public String getWorld(){
		return world;
	}
	
	public String getLocal(){
		return local;
	}
	
	public String getPrefix(){
		return prefix;
	}
	public String getSuffix(){
		return suffix;
	}
	public ChatColor getColor(){
		return Util.getColorFromCode(color);
	}
	public String getOpFormat(){
		return formatOp;
	}
	public String getDefaultFormat(){
		return defaults;
	}
	
	public String getCustomFormat(int num) {
		return plugin.getConfig().getString("Custom_Chat." + num + ".Format");
	}

}
