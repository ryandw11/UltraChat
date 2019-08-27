package me.ryandw11.ultrachat.formatting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UnknownFormatConversionException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.ChatType;
import me.ryandw11.ultrachat.api.events.UltraChatEvent;
import me.ryandw11.ultrachat.api.events.properties.RangeProperties;
import me.ryandw11.ultrachat.api.events.properties.RangeType;

public class Range implements Listener {
	private UltraChat plugin;

	public Range() {
		plugin = UltraChat.plugin;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		PlayerFormatting pf = new PlayerFormatting(p);
		
		if (p.hasPermission("ultrachat.chat.color")) {
			e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
		}
		e.getRecipients().removeAll(Bukkit.getOnlinePlayers());
		e.getRecipients().addAll(getNearbyPlayers(p));
		e.getRecipients().add(p);
		
		RangeProperties rp = new RangeProperties(false, RangeType.LOCAL);
		UltraChatEvent uce = new UltraChatEvent(p, e.getMessage(), new HashSet<Player>(Bukkit.getOnlinePlayers()), ChatType.RANGE, rp);
		Bukkit.getServer().getPluginManager().callEvent(uce);
		
		if(uce.isCancelled()) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getRecipients() != uce.getRecipients()) {
			e.getRecipients().removeAll(e.getRecipients());
			e.getRecipients().addAll(uce.getRecipients());
		}
		
		e.setMessage(uce.getMessage());
		
		try {
			e.setFormat(pf.getLocal().replace("%player%", "%s").replace("%prefix%", pf.getPrefix()).replace("%suffix%",
					pf.getSuffix()) + pf.getColor() + "%s");
		} catch (UnknownFormatConversionException ex) {
			Bukkit.getLogger().severe("A fatal error has occured! The local formatting is not correct!");
		}
	}

	public ArrayList<Player> getNearbyPlayers(Player pl) {
		ArrayList<Player> nearby = new ArrayList<Player>();
		double range = plugin.getConfig().getDouble("Local.range");
		for (Entity e : pl.getNearbyEntities(range, range, range)) {
			if (e instanceof Player) {
				nearby.add((Player) e);
			}
		}
		return nearby;
	}
}
