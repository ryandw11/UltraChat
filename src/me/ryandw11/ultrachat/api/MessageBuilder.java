package me.ryandw11.ultrachat.api;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.ComponentBuilder.FormatRetention;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Easy Builder to build several JSON sections into a single message.
 * {@link JSONChatBuilder}
 * @author Ryandw11
 * @since 2.4
 *
 */
public class MessageBuilder {
	
	private ComponentBuilder compon;
	public MessageBuilder() {
		compon = new ComponentBuilder("");
	}
	
	/**
	 * Add a JSONChatBuilder to the message
	 * @param json the JSONChatBuilder to add
	 * @return The MessageBuilder to chain.
	 */
	public MessageBuilder addJSON(JSONChatBuilder json) {
		compon.append(json.build(), FormatRetention.FORMATTING);
		return this;
	}
	
	/**
	 * Add a string to the message
	 * @param s the string to add
	 * @return the builder to chain
	 */
	public MessageBuilder addString(String s) {
		TextComponent tc = new TextComponent(s);
		compon.append(tc, FormatRetention.FORMATTING);
		return this;
	}
	
	/**
	 * Add a base component to the message
	 * @param bc the component to add
	 * @return The Buider to chain
	 */
	public MessageBuilder addBaseComponent(BaseComponent[] bc) {
		compon.append(bc, FormatRetention.FORMATTING);
		return this;
	}
	
	public BaseComponent[] build() {
		return this.compon.create();
	}
}
