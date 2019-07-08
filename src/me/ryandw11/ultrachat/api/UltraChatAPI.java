package me.ryandw11.ultrachat.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.channels.ChannelBuilder;
import me.ryandw11.ultrachat.api.channels.ChatChannel;
import me.ryandw11.ultrachat.formatting.PlayerFormatting;
/**
 * UltraChatAPI
 * @author Ryandw11
 * @version 2.1.0
 */

public class UltraChatAPI{
	/*
	 * 
	 * 		UltraChatAPI ch = new UltraChatAPI(UltraChat.plugin); < Method
	 * 
	 * 
	 * 
	 */
	private UltraChat plugin;
	public UltraChatAPI(){
		this.plugin = UltraChat.plugin;
	}
	
	
	/**
	 * Get a format's json.
	 * @param number
	 * @return Json array
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getChatFormatJson(String number){
		return (ArrayList<String>) plugin.getConfig().get("Custom_Chat." + number + ".JSON");
	}
	/**
	 * Set a format's json.
	 * @param json
	 * @param number
	 * 
	 */
	public void setChatFormatJson(ArrayList<String> json, String number){
		plugin.getConfig().set("Custom_Chat." + number + ".JSON", json);
		plugin.saveConfig();
	}
	/**
	 * Get the current chat mode.
	 * @return chat mode
	 */
	public ChatMode getMode(){
		return plugin.md;
	}
	/**
	 * Set the chat mode. Can be disabled in the config.
	 * @param cm The chat manager
	 */
	public void setMode(ChatMode cm){
		if(!plugin.getConfig().getBoolean("apirestrict") && cm != ChatMode.NONE){
			plugin.getConfig().set("chat_format", cm.toString().toLowerCase());
			plugin.getLogger().warning("A plugin has changed your chat mode to " + cm.toString() + "!");
			plugin.saveConfig();
			return;
		}
		if(!plugin.getConfig().getBoolean("apirestrict") && cm == ChatMode.NONE)
		{
			plugin.getConfig().set("chat_format", "");
			plugin.getLogger().warning("A plugin has changed your chat mode to " + cm.toString() + "!");
			plugin.saveConfig();
			return;
		}
		plugin.getLogger().warning("A plugin has tried to changed your chat mode!");
	}
	/**
	 * Grab the player's formatting.
	 * @param p The player.
	 * @return The player's formatting.
	 */
	public PlayerFormatting getPlayerFormatting(Player p){
		PlayerFormatting pf = new PlayerFormatting(p);
		return pf;
	}
	/**
	 * Get a chat format.
	 * @param number
	 * @return Chat format.
	 */
	public String getFormat(String number){
		return plugin.getConfig().getString("Custom_Chat." + number + ".Format");
	}
	/**
	 * Get the op format.
	 * @return op format.
	 */
	public String getOpFormat(){
		return plugin.getConfig().getString("Custom_Chat.Op_Chat.Format");
	}
	/**
	 * Get the default format.
	 * @return op format.
	 */
	public String getDefaultFormat(){
		return plugin.getConfig().getString("Custom_Chat.Default.Format");
	}
	/**
	 * Get the permission of a chat group.
	 * @param number
	 * @return permission
	 */
	public String getPermission(String number){
		return plugin.getConfig().getString("Custom_Chat." + number + ".Permission");
	}
	/**
	 * Get the number of formats
	 * @return Chat Count
	 */
	public int getChatCount(){
		return plugin.getConfig().getInt("Custom_Chat.Chat_Count");
	}
	/**
	 * Get a player's color. Example: &4
	 * @param player
	 * @return color code.
	 */
	public String getPlayerColor(Player player){
		return plugin.data.getString(player.getUniqueId() + ".color");
	}
	/**
	 * Set a player's color.
	 * @param player
	 * @param color
	 */
	public void setPlayerColor(Player player, String color){
		plugin.data.set(player.getUniqueId() + ".color", color);
		plugin.saveFile();
	}
	/**
	 * Get the swear word list.
	 * @return Block swear words.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getSwearWords(){
		return (ArrayList<String>) plugin.getConfig().get("Blocked_Words");
	}
	/**
	 * Set the swear word list.
	 * @param words
	 */
	public void setSwearWords(ArrayList<String> words){
		plugin.getConfig().set("Blocked_Words", words);
		plugin.saveConfig();
	}
	/**
	 * Create a channel.
	 * @param channel
	 * @param prefix
	 * @param permission
	 * @param always_appear
	 * @param format
	 * @param json
	 * @deprecated
	 */
	
	public void createChannel(String channel, String prefix, String permission, boolean always_appear, String format, ArrayList<String> json){
		plugin.channel.set(channel + ".prefix", prefix);
		plugin.channel.set(channel + ".permission", permission);
		plugin.channel.set(channel + ".always_appear", always_appear);
		plugin.channel.set(channel + ".format", format);
		plugin.channel.set(channel + ".JSON", json);
		plugin.saveChannel();
	}
	/**
	 * Remove a channel.
	 * @deprecated
	 * @param channel
	 */
	public void removeChannel(String channel){
		plugin.channel.set(channel, null);
		plugin.saveChannel();
	}
	
	/**
	 * Get the current formatting type.
	 * @return The value of the config.
	 */
	public String getFormattingType(){
		return plugin.getConfig().getString("chat_format");
	}
	/**
	 * Get if the channel has json enabled or not.
	 * @return True or false.
	 */
	public boolean isChannelJson(){
		return plugin.getConfig().getBoolean("Channel_Json");
	}
	
	/**
	 * See if default channel exists.
	 * @param chan - The channel in the config.
	 * @return True if it does, false if not.
	 */
	public boolean legitDefaultChannel(String chan){
		if(plugin.channel.contains(chan))
			return true;
		return false;
	}
	/**
	 * Get if range has json or not.
	 * @return True or False.
	 */
	public boolean isRangeJson(){
		return plugin.getConfig().getBoolean("Range_Json");
	}
	/**
	 * Get the current active hooks.
	 * @return The set witht the names of the plugins. Returns null if no hooks are active.
	 */
	public Set<String> getActiveHooks(){
		Set<String> s = new HashSet<String>();
		if(Bukkit.getServer().getPluginManager().getPlugin("AdvancedBan") != null && plugin.getConfig().getBoolean("pluginhooks.Essentials")){
			s.add("Essentials");
		}
		if(Bukkit.getServer().getPluginManager().getPlugin("Essentials") != null && plugin.getConfig().getBoolean("pluginhooks.AdvancedBan")){
			s.add("AdvancedBan");
		}
		
		return s;
	}
	
}
