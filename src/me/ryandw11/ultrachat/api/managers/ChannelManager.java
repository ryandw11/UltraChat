package me.ryandw11.ultrachat.api.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.channels.ChannelBuilder;
import me.ryandw11.ultrachat.api.channels.ChatChannel;

public class ChannelManager {
	private UltraChat plugin;
	public ChannelManager() {
		this.plugin = UltraChat.plugin;
	}

	/**
	 * Get a channel via its name
	 * @param name The name of the channel
	 * @return The channel.
	 */
	public ChatChannel getChannelByName(String name) {
		ChannelBuilder cb = new ChannelBuilder(name);
		return cb.build();
	}
	
	/**
	 * Grab the player's current channel.
	 * @param player
	 * @return The player's current channel.
	 */
	public ChatChannel getPlayerChannel(Player player){
		ChannelBuilder cb = new ChannelBuilder(plugin.data.getString(player.getUniqueId() + ".channel"));
		return cb.build();
	}
	
	/**
	 * Grab an offline player's current channel.
	 * @param player
	 * @return
	 */
	public ChatChannel getPlayerChannel(UUID player) {
		ChannelBuilder cb = new ChannelBuilder(plugin.data.getString(player + ".channel"));
		return cb.build();
	}
	
	/**
	 * Get the servers default channel.
	 * @return Default Channel
	 */
	public ChatChannel getDefaultChannel(){
		ChannelBuilder cb = new ChannelBuilder(plugin.getConfig().getString("Default_Channel"));
		return cb.build();
	}
	
	/**
	 * Set the player's channel.
	 * @param player
	 * @param channel
	 */
	public void setPlayerChannel(Player player, ChatChannel channel){
		plugin.data.set(player.getUniqueId() + ".channel", channel.getName());
		plugin.saveFile();
	}
	
	/**
	 * Set the player's channel.
	 * @param player
	 * @param channel
	 */
	public void setPlayerChannel(UUID player, ChatChannel channel) {
		plugin.data.set(player + ".channel", channel.getName());
		plugin.saveFile();
	}
	
	
	/**
	 * Set the default channel
	 * @param channel
	 */
	public void setDefaultChannel(ChatChannel channel){
		plugin.getConfig().set("Default_Config", channel.getName());
		plugin.saveConfig();
	}
	
	/**
	 * If a given channel exists (In this instance if it is save in the files)
	 * @param channel
	 */
	public boolean channelExists(ChatChannel channel) {
		return plugin.channel.contains(channel.getName());
	}
	
	/**
	 * If a given channel exists based upon the name.
	 * @param name
	 * @return
	 */
	public boolean channelExists(String name) {
		return plugin.channel.contains(name);
	}
	
	/**
	 * Get all online players in a given channel.
	 * @param channel
	 * @return
	 */
	public List<Player> getPlayersInChannel(ChatChannel channel){
		List<Player> output = new ArrayList<>();
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(this.getPlayerChannel(p).getName().equals(channel.getName())) {
				output.add(p);
			}
		}
		return output;
	}
	
	/**
	 * Get all players in a channel online or offline.
	 * @param channel
	 * @return
	 */
	public List<UUID> getAllPlayersInChannel(ChatChannel channel){
		List<UUID> output = new ArrayList<>();
		for(String s : plugin.data.getConfigurationSection("").getKeys(false)) {
			UUID ud = UUID.fromString(s);
			if(this.getPlayerChannel(ud).getName().equals(channel.getName())) {
				output.add(ud);
			}
		}
		return output;
	}
	
}
