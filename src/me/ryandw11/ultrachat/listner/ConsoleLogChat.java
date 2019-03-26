package me.ryandw11.ultrachat.listner;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.ryandw11.ultrachat.api.JsonChatEvent;
import me.ryandw11.ultrachat.api.Lang;

public class ConsoleLogChat implements Listener{
	
	@EventHandler
	public void jsonChat(JsonChatEvent e){
		String msg = e.getMessage();
		String pname = e.getPlayer().getDisplayName();
		Bukkit.getLogger().log(Level.INFO, Lang.CONSOLE_CHAT_LOG.toString().replace("%p", pname).replace("%s", msg).replace('&', '§'));
	}

}
