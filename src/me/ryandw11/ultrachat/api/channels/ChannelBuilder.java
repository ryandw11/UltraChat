package me.ryandw11.ultrachat.api.channels;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import me.ryandw11.ultrachat.UltraChat;


/**
 * A Builder to make a channel.
 * @author Ryandw11
 * @since 2.3.3
 */
public class ChannelBuilder {
	
	private String name;
	private String prefix;
	private String permission;
	private boolean alwaysAppear;
	private String format;
	private List<String> json;
	
	/**
	 * The Builder to create a channel.
	 * @param name The name of the channel (usually lowercase)
	 */
	public ChannelBuilder(String name) {
		this.setName(name);
		setPrefix("[" + name + "]");
		setPermission("none");
		setAlwaysAppear(false);
		setJson(new ArrayList<String>());
		setFormat("%prefix% %player% %suffix%&7 >> ");
	}

	protected String getName() {
		return name;
	}

	/**
	 * Set the name of the channel.
	 * @param name The name. (Lowercase is standard)
	 * @return The builder
	 */
	public ChannelBuilder setName(String name) {
		this.name = name;
		return this;
	}

	protected String getPrefix() {
		return prefix;
	}

	/**
	 * Set the prefix of the channel
	 * @param prefix The prefix (supports & codes)
	 * @return The builder
	 */
	public ChannelBuilder setPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}

	protected String getPermission() {
		return permission;
	}

	/**
	 * Set the permission for the channel.
	 * @param permission The permission
	 * @return The builder.
	 */
	public ChannelBuilder setPermission(String permission) {
		this.permission = permission;
		return this;
	}

	protected boolean isAlwaysAppear() {
		return alwaysAppear;
	}

	/**
	 * Set if the channel is always appear
	 * @param alwaysAppear If the channel should always appear.
	 * @return The builder
	 */
	public ChannelBuilder setAlwaysAppear(boolean alwaysAppear) {
		this.alwaysAppear = alwaysAppear;
		return this;
	}
	
	/**
	 * Build the channel
	 * @return The channel. Note: If the channel name is already used it will return the existing one.
	 */
	public ChatChannel build() {
		if(UltraChat.plugin.channel.contains(this.name)) {
			ConfigurationSection cs = UltraChat.plugin.channel.getConfigurationSection(this.name);
			assert cs != null;
			this.setPrefix(cs.getString("prefix"));
			this.setPermission(cs.getString("permission"));
			this.setAlwaysAppear(cs.getBoolean("always_appear"));
			this.setFormat(cs.getString("format"));
			this.setJson(cs.getStringList("JSON"));
		}
	    return new ChatChannel(this);
	}

	public List<String> getJson() {
		return json;
	}

	/**
	 * Set the json lore.
	 * @param json List of lore.
	 */
	public ChannelBuilder setJson(List<String> json) {
		this.json = json;
		return this;
	}

	public String getFormat() {
		return format;
	}

	public ChannelBuilder setFormat(String format) {
		this.format = format;
		return this;
	}

}
