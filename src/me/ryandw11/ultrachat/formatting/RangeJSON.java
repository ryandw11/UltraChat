package me.ryandw11.ultrachat.formatting;

import java.util.ArrayList;
import java.util.HashSet;

import me.ryandw11.ultrachat.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.ChatType;
import me.ryandw11.ultrachat.api.events.UltraChatEvent;
import me.ryandw11.ultrachat.api.events.properties.RangeProperties;
import me.ryandw11.ultrachat.api.events.properties.RangeType;
import me.ryandw11.ultrachat.api.managers.JComponentManager;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

public class RangeJSON implements Listener {

	private UltraChat plugin;

	public RangeJSON() {
		this.plugin = UltraChat.plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		PlayerFormatting pf = new PlayerFormatting(p);
		e.getRecipients().removeAll(Bukkit.getOnlinePlayers());
		e.getRecipients().addAll(getNearbyPlayers(p));
		e.getRecipients().add(p);
		
		RangeProperties rp = new RangeProperties(true, RangeType.LOCAL);

		UltraChatEvent uce = new UltraChatEvent(p, e.getMessage(), new HashSet<>(e.getRecipients()), ChatType.RANGE, rp);
		Bukkit.getServer().getPluginManager().callEvent(uce);
		e.getRecipients().clear();
		if (!uce.isCancelled()) {
			for (Player pl : uce.getRecipients()) {
				
				String formats = pf.getLocal()
						.replace("%player%", p.getDisplayName())
						.replace("%prefix%", pf.getPrefix())
						.replace("%suffix%", pf.getSuffix())
						+ pf.getColor();

				ComponentBuilder cb = new ComponentBuilder("");
				cb.append(JComponentManager.formatComponents(formats, p));
				cb.append(new TextComponent(TextComponent.fromLegacyText(plugin.chatColorUtil.translateChatColor(uce.getMessage()), pf.getColor())), ComponentBuilder.FormatRetention.NONE);
				pl.spigot().sendMessage(cb.create());
			}
		}
	}

	private ArrayList<Player> getNearbyPlayers(Player pl) {
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
