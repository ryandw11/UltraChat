package me.ryandw11.ultrachat.api.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.JSONChatBuilder;
import me.ryandw11.ultrachat.api.MessageBuilder;
import net.md_5.bungee.api.chat.BaseComponent;

public class JComponentManager {
	
	/**
	 * Get the final basecomponent from a message.
	 * @param message The string message
	 * @return The final basecomponent
	 */
	public static BaseComponent[] formatComponents(String message) {
		/*
		 * Parse the message for JSON components
		 */
		List<String> msgs = new ArrayList<String>();
		List<String> compName = new ArrayList<String>();
		
		List<String> fin = new ArrayList<String>();
		
		String temps = "";
		for(int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);
			if(c == '{') {
				msgs.add(temps);
				fin.add(temps);
				temps = "";
			}
			else if(c == '}') {
				compName.add(temps);
				fin.add(temps);
				temps = "";
			}
			else {
				temps += c;
			}
		}
		msgs.add(temps);
		fin.add(temps);
		/*
		 * Generate the final BaseComponent
		 */
		MessageBuilder mb = new MessageBuilder();
		
		// Loops through the final array.
		for(String s : fin) {
			// If the message is a normal message.
			if(msgs.contains(s)) {
				mb.addString(s);
			}
			// If the message is a component
			else if(compName.contains(s)) {
				ConfigurationSection cs = null;
				for(String st : UltraChat.plugin.getConfig().getConfigurationSection("JSON_Components").getKeys(false)) {
					if(st.equals(s)) {
						cs = UltraChat.plugin.getConfig().getConfigurationSection("JSON_Components." + st);
						break;
					}
				}
				
				if(cs == null) {
					Bukkit.getLogger().warning("Error: Can not find JSON Component named: " + s);
				}else {
					JSONChatBuilder jsb = new JSONChatBuilder(cs.getString("Message"));
					// If the component contains the click event.
					if(cs.contains("Events.Click")) {
						if(cs.contains("Events.Click.Open_URL")) 
							jsb.setClickOpenUrl(cs.getString("Events.Click.Open_URL"));
						else if(cs.contains("Events.Click.Run_Command"))
							jsb.setClickRunCommand(cs.getString("Events.Click.Run_Command"));
						else if(cs.contains("Events.Click.Suggest_Command"))
							jsb.setClickSuggestCommand(cs.getString("Events.Click.Suggest_Command"));
					}
					// If the component contains the hover event.
					if(cs.contains("Events.Hover")) {
						if(cs.contains("Events.Hover.Show_Text"))
							jsb.setHoverShowText(cs.getStringList("Events.Hover.Show_Text"));
					}
					
					mb.addJSON(jsb);
				}
			}
		}
		
		// Build the message builder and return it
		return mb.build();
	}
	
	/**
	 * Get the final basecomponent from a message.
	 * @param message The string message
	 * @param p The player to translate PAPI for.
	 * @return The final basecomponent
	 */
	public static BaseComponent[] formatComponents(String message, Player p) {
		/*
		 * Parse the message for JSON components
		 */
		List<String> msgs = new ArrayList<String>();
		List<String> compName = new ArrayList<String>();
		
		List<String> fin = new ArrayList<String>();
		
		String temps = "";
		for(int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);
			if(c == '{') {
				msgs.add(temps);
				fin.add(temps);
				temps = "";
			}
			else if(c == '}') {
				compName.add(temps);
				fin.add(temps);
				temps = "";
			}
			else {
				temps += c;
			}
		}
		msgs.add(temps);
		fin.add(temps);
		/*
		 * Generate the final BaseComponent
		 */
		MessageBuilder mb = new MessageBuilder();
		
		// Loops through the final array.
		for(String s : fin) {
			// If the message is a normal message.
			if(msgs.contains(s)) {
				mb.addString(s);
			}
			// If the message is a component
			else if(compName.contains(s)) {
				ConfigurationSection cs = null;
				for(String st : UltraChat.plugin.getConfig().getConfigurationSection("JSON_Components").getKeys(false)) {
					if(st.equals(s)) {
						cs = UltraChat.plugin.getConfig().getConfigurationSection("JSON_Components." + st);
						break;
					}
				}
				
				if(cs == null) {
					Bukkit.getLogger().warning("Error: Can not find JSON Component named: " + s);
				}else {
					JSONChatBuilder jsb = new JSONChatBuilder(cs.getString("Message"), p);
					// If the component contains the click event.
					if(cs.contains("Events.Click")) {
						if(cs.contains("Events.Click.Open_URL")) 
							jsb.setClickOpenUrl(cs.getString("Events.Click.Open_URL"));
						else if(cs.contains("Events.Click.Run_Command"))
							jsb.setClickRunCommand(cs.getString("Events.Click.Run_Command"));
						else if(cs.contains("Events.Click.Suggest_Command"))
							jsb.setClickSuggestCommand(cs.getString("Events.Click.Suggest_Command"));
					}
					// If the component contains the hover event.
					if(cs.contains("Events.Hover")) {
						if(cs.contains("Events.Hover.Show_Text")) {
							List<String> finals = new ArrayList<String>();
							for(String st : cs.getStringList("Events.Hover.Show_Text")) {
								finals.add(UltraChat.plugin.addonManager.replacePlaceholders(st, p.getUniqueId()));
							}
							jsb.setHoverShowText(finals);
						}
							
					}
					
					mb.addJSON(jsb);
				}
			}
		}
		
		// Build the message builder and return it
		return mb.build();
	}
	
	/**
	 * Get the final basecomponent from a message.
	 * @param message The string message
	 * @param p The player to translate PAPI for.
	 * @return The final basecomponent
	 */
	public static BaseComponent[] formatComponents(String message, UUID p) {
		/*
		 * Parse the message for JSON components
		 */
		List<String> msgs = new ArrayList<String>();
		List<String> compName = new ArrayList<String>();
		
		List<String> fin = new ArrayList<String>();
		
		String temps = "";
		for(int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);
			if(c == '{') {
				msgs.add(temps);
				fin.add(temps);
				temps = "";
			}
			else if(c == '}') {
				compName.add(temps);
				fin.add(temps);
				temps = "";
			}
			else {
				temps += c;
			}
		}
		msgs.add(temps);
		fin.add(temps);
		/*
		 * Generate the final BaseComponent
		 */
		MessageBuilder mb = new MessageBuilder();
		
		// Loops through the final array.
		for(String s : fin) {
			// If the message is a normal message.
			if(msgs.contains(s)) {
				mb.addString(s);
			}
			// If the message is a component
			else if(compName.contains(s)) {
				ConfigurationSection cs = null;
				for(String st : UltraChat.plugin.getConfig().getConfigurationSection("JSON_Components").getKeys(false)) {
					if(st.equals(s)) {
						cs = UltraChat.plugin.getConfig().getConfigurationSection("JSON_Components." + st);
						break;
					}
				}
				
				if(cs == null) {
					Bukkit.getLogger().warning("Error: Can not find JSON Component named: " + s);
				}else {
					JSONChatBuilder jsb = new JSONChatBuilder(cs.getString("Message"));
					// If the component contains the click event.
					if(cs.contains("Events.Click")) {
						if(cs.contains("Events.Click.Open_URL")) 
							jsb.setClickOpenUrl(cs.getString("Events.Click.Open_URL"));
						else if(cs.contains("Events.Click.Run_Command"))
							jsb.setClickRunCommand(cs.getString("Events.Click.Run_Command"));
						else if(cs.contains("Events.Click.Suggest_Command"))
							jsb.setClickSuggestCommand(cs.getString("Events.Click.Suggest_Command"));
					}
					// If the component contains the hover event.
					if(cs.contains("Events.Hover")) {
						if(cs.contains("Events.Hover.Show_Text")) {
							List<String> finals = new ArrayList<String>();
							for(String st : cs.getStringList("Events.Hover.Show_Text")) {
								finals.add(UltraChat.plugin.addonManager.replacePlaceholders(st, p));
							}
							jsb.setHoverShowText(finals);
						}
							
					}
					
					mb.addJSON(jsb);
				}
			}
		}
		
		// Build the message builder and return it
		return mb.build();
	}

}
