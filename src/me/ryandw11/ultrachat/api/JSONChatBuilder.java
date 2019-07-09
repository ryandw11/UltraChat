package me.ryandw11.ultrachat.api;

import java.util.List;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

/**
 * API to standardize JSONChatBuilding
 * <p>To be used with the MessageBuilder {@link MessageBuilder}
 * @author Ryandw11
 * @since 2.4
 *
 */
public class JSONChatBuilder {
	
	private ComponentBuilder displayMessage;
	
	public JSONChatBuilder(String displayMessage) {
		this.displayMessage = new ComponentBuilder(displayMessage);
	}
	
	/**
	 * Add a hove message.
	 * @param lore Lore
	 */
	public JSONChatBuilder setHoverShowText(List<String> lore) {
		ComponentBuilder lores = new ComponentBuilder("");
		for(String s : lore) {
			lores.append(ChatColor.translateAlternateColorCodes('&', s));
		}
		this.displayMessage.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, lores.create()));
		return this;
	}
	
	/**
	 * Have the JSON open up a url.
	 * @param url The url
	 * @return The builder
	 */
	public JSONChatBuilder setClickOpenUrl(String url) {
		this.displayMessage.event(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
		return this;
	}
	
	/**
	 * Have the JSON run a command.
	 * @param command The command to run
	 * @return The builder
	 */
	public JSONChatBuilder setClickRunCommand(String command) {
		this.displayMessage.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		return this;
	}
	
	/**
	 * Have the JSON suggest a command
	 * @param command The command
	 * @return The Builder
	 */
	public JSONChatBuilder setClickSuggestCommand(String command) {
		this.displayMessage.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
		return this;
	}
	
	/**
	 * Build the JSONChatMessage
	 * @return The BaseComponent[]
	 */
	public BaseComponent[] build() {
		return this.displayMessage.create();
	}

}
