package me.ryandw11.ultrachat.formatting;

import java.util.HashSet;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
 * @since 2.5
 *
 */
public class NormalJSON implements Listener {

	private UltraChat plugin;

	public NormalJSON() {
		this.plugin = UltraChat.plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		PlayerFormatting pf = new PlayerFormatting(p);

		// Call the UltraChatEvent (This is an optional Event).
		NormalProperties np = new NormalProperties(true);
		UltraChatEvent event = new UltraChatEvent(p, e.getMessage(), new HashSet<>(e.getRecipients()), ChatType.NORMAL, np);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (event.isCancelled()) {
			return;
		}

		//Remove all players from the normal event.
		e.getRecipients().clear();

		if (p.isOp()) {
			String formats = pf.getOpFormat()
					.replace("%prefix%", pf.getPrefix())
					.replace("%suffix%", pf.getSuffix())
					.replace("%player%", p.getDisplayName()) + pf.getColor();

			for (Player pl : event.getRecipients()) {
				ComponentBuilder cb = new ComponentBuilder("");
				cb.append(JComponentManager.formatComponents(formats, p));
				cb.append(new TextComponent(TextComponent.fromLegacyText(pf.getColor() + plugin.chatColorUtil.translateChatColor(event.getMessage()), pf.getColor())), ComponentBuilder.FormatRetention.NONE);
				pl.spigot().sendMessage(cb.create());
			}
			return;
		}
		// If the player is not op
		for (String key : Objects.requireNonNull(plugin.getConfig().getConfigurationSection("Custom_Chat.permission_format")).getKeys(false)) {
			String permission = plugin.getConfig().getString("Custom_Chat.permission_format." + key + ".permission");
			assert permission != null;
			if (p.hasPermission(permission)) {
				String formats = pf.getCustomFormat(key)
						.replace("%prefix%", pf.getPrefix())
						.replace("%suffix%", pf.getSuffix())
						.replace("%player%", p.getDisplayName())
						+ pf.getColor();

				for (Player pl : event.getRecipients()) {
					ComponentBuilder cb = new ComponentBuilder("");
					cb.append(JComponentManager.formatComponents(formats, p));
					cb.append(new TextComponent(TextComponent.fromLegacyText(pf.getColor() + plugin.chatColorUtil.translateChatColor(event.getMessage(), p), pf.getColor())), ComponentBuilder.FormatRetention.NONE);
					pl.spigot().sendMessage(cb.create());
				}
				return;
			}
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
			cb.append(new TextComponent(TextComponent.fromLegacyText(pf.getColor() + plugin.chatColorUtil.translateChatColor(event.getMessage(), p), pf.getColor())), ComponentBuilder.FormatRetention.NONE);
			pl.spigot().sendMessage(cb.create());
		}
	}

}
