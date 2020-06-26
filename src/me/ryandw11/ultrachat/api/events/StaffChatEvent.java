package me.ryandw11.ultrachat.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * This event is called when the staff chat is used.
 */
public class StaffChatEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private String message;
	private boolean cancelled;

	public StaffChatEvent(Player p, String message) {
	     player = p;
	     this.message = message;
	 }

	/**
	 * Get the player
	 * @return The player. (Can be null if sent by the console)
	 */
	public Player getPlayer() {
		return player;
	}

	public String getMessage() {
		return message;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
