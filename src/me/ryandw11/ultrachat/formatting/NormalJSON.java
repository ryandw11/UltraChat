package me.ryandw11.ultrachat.formatting;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.ChatType;
import me.ryandw11.ultrachat.api.events.UltraChatEvent;
import me.ryandw11.ultrachat.api.events.properties.NormalProperties;
import me.ryandw11.ultrachat.api.managers.JComponentManager;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Handles the Normal Chat when in JSON mode.
 * @author Ryandw11
 * @since 2.4
 *
 */
public class NormalJSON implements Listener {

	private UltraChat plugin;

	public NormalJSON() {
		this.plugin = UltraChat.plugin;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(true);
		PlayerFormatting pf = new PlayerFormatting(p);
		NormalProperties np = new NormalProperties(true);
		UltraChatEvent event = new UltraChatEvent(p, e.getMessage(), new HashSet<Player>(Bukkit.getOnlinePlayers()), ChatType.NORMAL, np);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (event.isCancelled()) {
			return;
		}
		if (p.isOp() && p.hasPermission("ultrachat.formatting.op")) {
			String formats = pf.getOpFormat()
					.replace("%prefix%", pf.getPrefix())
					.replace("%suffix%", pf.getSuffix())
					.replace("%player%", p.getDisplayName())
					+ pf.getColor();

			for (Player pl : event.getRecipients()) {
				ComponentBuilder cb = new ComponentBuilder("");
				cb.append(JComponentManager.formatComponents(formats, p));
				TextComponent tc = new TextComponent(event.getMessage());
				cb.append(tc);
				pl.spigot().sendMessage(cb.create());
			}
			return;
		}
		// If the player is not op
		int i = 1;
		boolean complete = false;
		while (i <= plugin.getConfig().getInt("Custom_Chat.Chat_Count")) {
			if (p.hasPermission(plugin.getConfig().getString("Custom_Chat." + i + ".Permission"))) {
				String formats = pf.getCustomFormat(i)
						.replace("%prefix%", pf.getPrefix())
						.replace("%suffix%", pf.getSuffix())
						.replace("%player%", p.getDisplayName())
						+ pf.getColor();

				for (Player pl : event.getRecipients()) {
					ComponentBuilder cb = new ComponentBuilder("");
					cb.append(JComponentManager.formatComponents(formats, p));
					TextComponent tc = new TextComponent(event.getMessage());
					cb.append(tc);
					pl.spigot().sendMessage(cb.create());
					complete = true;
					break;
				}
			}
			if (complete)
				return;
			i++;
		}

		/*
		 * Normal player check
		 */
		String formats = pf.getDefaultFormat()
				.replace("%prefix%", pf.getPrefix())
				.replace("%suffix%", pf.getSuffix())
				.replace("%player%", p.getDisplayName())
				+ pf.getColor();
		for (Player pl : event.getRecipients()) {
			ComponentBuilder cb = new ComponentBuilder("");
			cb.append(JComponentManager.formatComponents(formats, p));
			TextComponent tc = new TextComponent(event.getMessage());
			cb.append(tc);
			pl.spigot().sendMessage(cb.create());
		}
	}

}
