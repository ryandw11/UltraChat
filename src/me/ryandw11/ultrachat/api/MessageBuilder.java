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
	
	private ComponentBuilder component;
	public MessageBuilder() {
		component = new ComponentBuilder("");
	}
	
	/**
	 * Add a JSONChatBuilder to the message
	 * @param json the JSONChatBuilder to add
	 * @return The MessageBuilder to chain.
	 */
	public MessageBuilder addJSON(JSONChatBuilder json) {
		component.append(json.build(), FormatRetention.NONE);
		return this;
	}
	
	/**
	 * Add a string to the message
	 * @param s the string to add
	 * @return the builder to chain
	 */
	public MessageBuilder addString(String s) {
		TextComponent tc = new TextComponent(s);
		component.append(tc, FormatRetention.NONE);
		return this;
	}
	
	/**
	 * Add a base component to the message
	 * @param bc the component to add
	 * @return The Buider to chain
	 */
	public MessageBuilder addBaseComponent(BaseComponent[] bc) {
		component.append(bc, FormatRetention.NONE);
		return this;
	}
	
	public BaseComponent[] build() {
		return this.component.create();
	}
}
