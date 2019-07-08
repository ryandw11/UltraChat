package me.ryandw11.ultrachat.api.events;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
/**
 * Event class - 
 * Async Event
 * @author Ryandw11
 *
 */
public class WorldChatEvent extends Event {
	 private static final HandlerList handlers = new HandlerList();
	 private Player player;
	 private String chat;
	 private Set<Player> recipients;
	 private boolean cancelled;

	 public WorldChatEvent(Player p, String chat, Set<Player> recipients) {
		 super(true);
	     player = p;
	     this.chat = chat;
	     this.recipients = recipients;
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
	 
	 public void setMessage(String message){
		 chat = message;
	 }
	 
	 public boolean isCancelled(){
		 return cancelled;
	 }
	 
	 public void setCancelled(boolean cancel){
		 cancelled = cancel;
	 }
	 
	 public HandlerList getHandlers() {
		 return handlers;
	 }

	 public static HandlerList getHandlerList() {
		 return handlers;
	 }
}
