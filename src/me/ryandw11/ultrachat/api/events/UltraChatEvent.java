package me.ryandw11.ultrachat.api.events;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.ryandw11.ultrachat.api.ChatType;
import me.ryandw11.ultrachat.api.events.properties.ChatProperties;

public class UltraChatEvent extends Event{

	private static final HandlerList handlers = new HandlerList();
	private Player player;
	 private String chat;
	 private Set<Player> recipients;
	 private ChatType type;
	 private ChatProperties prop;
	 private boolean cancelled;

	 public UltraChatEvent(Player p, String chat, Set<Player> recipients, ChatType ct, ChatProperties properties) {
		 super(true);
	     player = p;
	     this.chat = chat;
	     this.recipients = recipients;
	     this.type = ct;
	     this.prop = properties;
	 }
	 /**
	  * Grab the recipients.
	  * @return The recipients.
	  */
	 public Set<Player> getRecipients(){
		 return recipients;
	 }
	 /**
	  * Set the recipients.
	  * @param recipents The set of recipients.
	  */
	 public void setRecipients(Set<Player> recipients){
		 this.recipients = recipients;
	 }
	 
	 public Player getPlayer() {
		 return player;
	 }

	 public String getMessage() {
		 return chat;
	 }
	 
	 /**
	  * Set the message
	  * @param message The message.
	  */
	 public void setMessage(String message){
		 chat = message;
	 }
	 
	 public boolean isCancelled(){
		 return cancelled;
	 }
	 
	 public void setCancelled(boolean cancel){
		 cancelled = cancel;
	 }
	 
	 /**
	  * Get the type of the chat used.
	  * @return The type of chat used.
	  */
	 public ChatType getType() {
		 return type;
	 }
	 
	 /**
	  * Get the properties.
	  * @return Get the information for a certain chat event.
	  */
	 public ChatProperties getProperties() {
		 return prop;
	 }
	 
	 public HandlerList getHandlers() {
		 return handlers;
	 }

	 public static HandlerList getHandlerList() {
		 return handlers;
	 }

}
