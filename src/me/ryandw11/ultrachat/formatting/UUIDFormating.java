package me.ryandw11.ultrachat.formatting;

import java.util.Objects;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.ChatType;
import me.ryandw11.ultrachat.api.UltraChatAPI;
import me.ryandw11.ultrachat.api.Util;
import me.ryandw11.ultrachat.api.channels.ChatChannel;
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
		
		color =  Objects.requireNonNull(plugin.data.getString(ud + ".color"));
		try {
			prefix = ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerPrefix(world, op));
			suffix = ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerSuffix(world, op));
		}
		catch(NullPointerException ex) {
			prefix = plugin.chat.getPlayerPrefix(world, op);
			suffix = plugin.chat.getPlayerSuffix(world, op);
		}
		formatOp = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Custom_Chat.Op_Chat")));
		defaults = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Custom_Chat.Default_Chat")));
		global = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Global.format")));
		this.world = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("World.format")));
		local = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Local.format")));
		this.op = op;
		worldName = world;
	}
	
	private String prefix;
	private String suffix;
	public String color;
	private String formatOp;
	private String defaults;
	private String global;
	private String world;
	private String local;
	private OfflinePlayer op;
	private String worldName;
	
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
	
	public String getCustomFormat(String name) {
		return plugin.getConfig().getString("Custom_Chat.permission_format." + name);
	}
	
	public OfflinePlayer getOfflinePlayer() {
		return op;
	}
	
	/**
	 * Get the active format for a player.
	 * @return The active format
	 * @since 2.4
	 */
	public String getActiveFormat() {
		UltraChatAPI uapi = new UltraChatAPI();
		if(uapi.getChatType() == ChatType.NORMAL) {
			if(op.isOp()) return this.getOpFormat();

			for (String permission : Objects.requireNonNull(plugin.getConfig().getConfigurationSection("Custom_Chat.permission_format")).getKeys(false)) {
				if (plugin.perms.playerHas(worldName, op, plugin.getConfig().getString(permission))) {
					return this.getCustomFormat(permission);
				}
			}
			return this.getDefaultFormat();
		}
		else if(uapi.getChatType() == ChatType.CHANNEL) {
			ChatChannel cc = uapi.getPlayerCurrentChannel(op.getUniqueId());
			return cc.getFormat();
		}
		else if(uapi.getChatType() == ChatType.RANGE) {
			return plugin.getConfig().getString("Local.format");
		}
		
		return "ERROR: COULD NOT GET ACTIVE TYPE";
	}

}
