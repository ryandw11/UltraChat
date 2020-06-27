package me.ryandw11.ultrachat.api.channels;

import java.util.List;

import me.ryandw11.ultrachat.UltraChat;

/**
 * This class is used the manage channels.
 * <p>Note: All set methods automatically call the {@link #update()} method.</p>
 */
public class ChatChannel {
	
	private String name;
	private String prefix;
	private String permission;
	private boolean alwaysAppear;
	private String format;
	private List<String> json;
	
	/**
	 * For internal use only. Always use the channel builder.
	 * @param cb The builder that was used to build the channel.
	 */
	public ChatChannel(ChannelBuilder cb) {
		this.name = cb.getName();
		this.prefix = cb.getPrefix();
		this.permission = cb.getPermission();
		this.alwaysAppear = cb.isAlwaysAppear();
		this.json = cb.getJson();
		this.format = cb.getFormat();
	}

	public String getName() {
		return name;
	}

	/**
	 * Set the name of the channel.
	 * <p>Note: If this channel is the default channel then you will need to change the default channel name manually</p>
	 * @param name The name of the channel.
	 */
	public void setName(String name) {
		UltraChat.plugin.channel.set(this.name, null);
		this.name = name;
		update();
	}

	/**
	 * Get the prefix of the channel.
	 * @return The prefix of the channel.
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Set the prefix of the channel.
	 * @param prefix The prefix of the channel.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
		update();
	}

	/**
	 * Get the permission for the channel.
	 * @return The permission for the channel.
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * Set the permission for the channel.
	 * @param permission The permission for the channel.
	 */
	public void setPermission(String permission) {
		this.permission = permission;
		update();
	}

	/**
	 * If the channel always appears.
	 * @return If the channel always appears.
	 */
	public boolean isAlwaysAppear() {
		return alwaysAppear;
	}

	/**
	 * Set if the channel will always appear in chat.
	 * @param alwaysAppear If the channel will always appear.
	 */
	public void setAlwaysAppear(boolean alwaysAppear) {
		this.alwaysAppear = alwaysAppear;
		update();
	}

	/**
	 * Get the json of the channel
	 * @return The json.
	 */
	public List<String> getJson() {
		return json;
	}

	/**
	 * Set the json for the channel.
	 * @param json The json.
	 */
	public void setJson(List<String> json) {
		this.json = json;
		update();
	}
	
	/**
	 * Call this after every change and after initialization to save the channel to the files.
	 */
	private void update() {
		UltraChat plugin = UltraChat.plugin;
		plugin.channel.set(this.getName() + ".prefix", this.getPrefix());
		plugin.channel.set(this.getName() + ".permission", this.getPrefix());
		plugin.channel.set(this.getName() + ".always_appear", this.isAlwaysAppear());
		plugin.channel.set(this.getName() + ".format", this.getFormat());
		plugin.channel.set(this.getName() + ".JSON", this.getJson());
	}

	/**
	 * Delete the channel
	 * @deprecated It is not recommended to use this.
	 */
	public void delete() {
		UltraChat.plugin.channel.set(this.getName(), null);
	}

	/**
	 * Get the format of the channel.
	 * @return The format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Set the format of the channel.
	 * @param format The format.
	 */
	public void setFormat(String format) {
		this.format = format;
		update();
	}

}
