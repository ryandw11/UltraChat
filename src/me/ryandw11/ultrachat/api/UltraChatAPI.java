package me.ryandw11.ultrachat.api;

import java.util.*;

import me.ryandw11.ultrachat.api.managers.ChannelManager;
import me.ryandw11.ultrachat.chatcolor.ChatColorManager;
import me.ryandw11.ultrachat.chatcolor.ChatColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.channels.ChannelBuilder;
import me.ryandw11.ultrachat.api.channels.ChatChannel;
import me.ryandw11.ultrachat.api.managers.AddonManager;
import me.ryandw11.ultrachat.formatting.PlayerFormatting;
/**
 * UltraChatAPI
 * @author Ryandw11
 * @version 2.5
 */

public class UltraChatAPI{
	private UltraChat plugin;
	public UltraChatAPI(){
		this.plugin = UltraChat.plugin;
	}
	
	/**
	 * Get the current channel of a player.
	 * @param player The uuid of the player you want to get.
	 * @return The chat channel.
	 * @deprecated Use the channel manager instead.
	 */
	public ChatChannel getPlayerCurrentChannel(UUID player) {
		return new ChannelBuilder(plugin.data.getString(player + ".channel")).build();
	}
	
	/**
	 * Get the addon manager.
	 * @return The addon manager.
	 */
	public AddonManager getAddonManager() {
		return plugin.addonManager;
	}
	
	
	/**
	 * Get the current chat type.
	 * @return chat type
	 */
	public ChatType getChatType(){
		return plugin.chatType;
	}
	
	
	/**
	 * Set the chat mode. Can be disabled in the config.
	 * @param cm The chat manager
	 */
	public void setType(ChatType cm){
		if(!plugin.getConfig().getBoolean("apirestrict")){
			plugin.getConfig().set("chat_format", cm.toString().toLowerCase());
			plugin.getLogger().warning("A plugin has changed your chat mode to " + cm.toString() + "!");
			plugin.saveConfig();
			return;
		}
		plugin.getLogger().warning("A plugin has tried to changed your chat type!");
	}
	
	/**
	 * Grab the player's formatting.
	 * @param p The player.
	 * @return The player's formatting.
	 * @deprecated Construct the {@link PlayerFormatting} class yourself.
	 */
	public PlayerFormatting getPlayerFormatting(Player p){
		return new PlayerFormatting(p);
	}

	/**
	 * Get a player's color.
	 * <p>This will return a string Such as &4 or {#FFFFFF}.</p>
	 * <p>Note: The colors interpreted are from the chatcolor.yml file. To also get color data from the use {@link me.ryandw11.ultrachat.util.ChatUtil#translateColorCode(String)}</p>
	 * @param player The player to get the color for
	 * @return color code.
	 */
	public String getPlayerColor(Player player){
		return plugin.data.getString(player.getUniqueId() + ".color");
	}

	/**
	 * Set a player's color.
	 * @param player The player color to set
	 * @param color The color to set
	 *              <p>Keep in mind that this color is interpreted from the chatcolor.yml file, not the default colors.</p>
	 */
	public void setPlayerColor(Player player, String color){
		plugin.data.set(player.getUniqueId() + ".color", color);
		plugin.saveFile();
	}

	/**
	 * Get the swear word list.
	 * @return Block swear words.
	 */
	public List<String> getSwearWords(){
		return plugin.getConfig().getStringList("Blocked_Words");
	}

	/**
	 * Set the swear word list.
	 * @param words The words to add
	 */
	public void setSwearWords(List<String> words){
		plugin.getConfig().set("Blocked_Words", words);
		plugin.saveConfig();
	}
	
	/**
	 * Get the current formatting type.
	 * @return The value of the config.
	 */
	public ChatType getFormattingType(){
		return plugin.chatType;
	}

	/**
	 * Get the chat color utility interface.
	 * <p>Note: Most methods from this interface are wrapped in the {@link me.ryandw11.ultrachat.util.ChatUtil} class.</p>
	 * @return The chat color utility interface.
	 */
	public ChatColorUtils getChatColorUtil(){
		return plugin.chatColorUtil;
	}

	/**
	 * Get the chat color manager.
	 * <p>Note: Most uses for this class is covered by the {@link me.ryandw11.ultrachat.util.ChatUtil} class.</p>
	 * <p><b>This class is only available on 1.16+ servers. This WILL return null on any version below 1.16.</b></p>
	 * @return The chat color manager.
	 */
	public ChatColorManager getChatColorManager(){
		return plugin.chatColorManager;
	}

	public ChannelManager getChannelManager(){
		return new ChannelManager();
	}

	/**
	 * Get the current active hooks.
	 * @return The set with the names of the plugins. Returns null if no hooks are active.
	 */
	public Set<String> getActiveHooks(){
		Set<String> s = new HashSet<>();
		if(Bukkit.getServer().getPluginManager().getPlugin("AdvancedBan") != null && plugin.getConfig().getBoolean("pluginhooks.Essentials")){
			s.add("Essentials");
		}
		if(Bukkit.getServer().getPluginManager().getPlugin("Essentials") != null && plugin.getConfig().getBoolean("pluginhooks.AdvancedBan")){
			s.add("AdvancedBan");
		}
		
		return s;
	}
	
}
