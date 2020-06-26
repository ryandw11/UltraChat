package me.ryandw11.ultrachat.formatting;

import me.ryandw11.ultrachat.util.ChatUtil;
import org.bukkit.entity.Player;

import me.ryandw11.ultrachat.UltraChat;
import net.md_5.bungee.api.ChatColor;

import java.util.Objects;

/**
 * Class for formatting player chat easily.
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
		
		color =  plugin.data.getString(p.getUniqueId() + ".color");
		prefix = ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerPrefix(p));
		suffix = ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerSuffix(p));
		formatOp = plugin.papi.translatePlaceholders(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Custom_Chat.Op_Chat"))), p);
		defaults = plugin.papi.translatePlaceholders(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Custom_Chat.Default_Chat"))), p);
		global = plugin.papi.translatePlaceholders(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Global.format"))), p);
		world = plugin.papi.translatePlaceholders(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("World.format"))), p);
		local = plugin.papi.translatePlaceholders(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Local.format"))), p);
		this.p = p;
	}
	
	private Player p;
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
		return ChatUtil.translateColorCode(color);
	}
	public String getOpFormat(){
		return formatOp;
	}
	public String getDefaultFormat(){
		return defaults;
	}

	public String getCustomFormat(String name) {
		return plugin.papi.translatePlaceholders(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Custom_Chat.permission_format." + name))), p);
	}
	
}
