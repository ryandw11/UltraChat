package me.ryandw11.ultrachat.formatting;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Util;
import net.md_5.bungee.api.ChatColor;
/**
 * Class for formatting player chat easily. (Demo: May not stay).
 * @author Ryandw11
 *
 */
public class PlayerFormatting {
	private UltraChat plugin;
	
	/**
	 * Get the formatting for a player.
	 * @param p
	 */
	public PlayerFormatting(Player p){
		plugin = UltraChat.plugin;
		
		color = ChatColor.translateAlternateColorCodes('&', plugin.data.getString(p.getUniqueId() + ".color"));	
		prefix = ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerPrefix(p));
		suffix = ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerSuffix(p));
		formatOp = PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Custom_Chat.Op_Chat.Format")));
		defaults = PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Custom_Chat.Default_Chat.Format")));
		global = PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Global.format")));
		world = PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("World.format")));
		local = PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Local.format")));
	}
	
	/**
	 * Get the formatting for an offline player.
	 * <p>Note: Placeholders will not work in this mode.</p>
	 * @param op The offline player
	 * @param worldname The name of the world to get the preffix and suffix from.
	 * @since 2.3.3
	 */
	public PlayerFormatting(OfflinePlayer op, String worldname) {
		plugin = UltraChat.plugin;
		color = ChatColor.translateAlternateColorCodes('&', plugin.data.getString(op.getUniqueId() + ".color"));	
		prefix = ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerPrefix(worldname, op));
		suffix = ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerSuffix(worldname, op));
		formatOp = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Custom_Chat.Op_Chat.Format"));
		defaults = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Custom_Chat.Default_Chat.Format"));
		global = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Global.format"));
		world = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("World.format"));
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
	
}
