package me.ryandw11.ultrachat.api.events.properties;

import me.ryandw11.ultrachat.api.channels.ChannelBuilder;
import me.ryandw11.ultrachat.api.channels.ChatChannel;

public class ChannelProperties implements ChatProperties {
	
	private boolean json;
	private String channel;
	
	public ChannelProperties(boolean json, String channel) {
		this.json = json;
		this.channel = channel;
	}

	@Override
	public boolean isComponent() {
		return json;
	}
	
	public ChatChannel getChannel() {
		ChatChannel cc = new ChannelBuilder(channel).build();
		return cc;
	}

}
