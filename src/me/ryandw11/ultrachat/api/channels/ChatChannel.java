package me.ryandw11.ultrachat.api.channels;

import java.util.List;

import me.ryandw11.ultrachat.UltraChat;

public class ChatChannel {
	
	private String name;
	private String prefix;
	private String permission;
	private boolean alwaysAppear;
	private String format;
	private List<String> json;
	
	/**
	 * For internal use only. Always use the channel builder.
	 * @param cb
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
	 * Auto calls the update() method.
	 * <p>Note: If this channel is the default channel then you will need to change the default channel name manually</p>
	 * @param name
	 */
	public void setName(String name) {
		UltraChat.plugin.channel.set(this.name, null);
		this.name = name;
		update();
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
		update();
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
		update();
	}

	public boolean isAlwaysAppear() {
		return alwaysAppear;
	}

	public void setAlwaysAppear(boolean alwaysAppear) {
		this.alwaysAppear = alwaysAppear;
		update();
	}

	public List<String> getJson() {
		return json;
	}

	public void setJson(List<String> json) {
		this.json = json;
		update();
	}
	
	/**
	 * Call this after every change and after intialization to save the channel to the files.
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
	 */
	public void delete() {
		UltraChat.plugin.channel.set(this.getName(), null);
	}
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
		update();
	}

}
