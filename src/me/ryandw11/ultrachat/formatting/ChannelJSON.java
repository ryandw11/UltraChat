package me.ryandw11.ultrachat.formatting;

import java.util.HashSet;
import java.util.Objects;

import me.ryandw11.ultrachat.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.ChatType;
import me.ryandw11.ultrachat.api.events.UltraChatEvent;
import me.ryandw11.ultrachat.api.events.properties.ChannelProperties;
import me.ryandw11.ultrachat.api.managers.JComponentManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Handles channels with components enabled.
 * @author Ryandw11
 * @since 2.5
 *
 */
public class ChannelJSON implements Listener {
	
	private UltraChat plugin;
	public ChannelJSON() {
		this.plugin = UltraChat.plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		PlayerFormatting pf = new PlayerFormatting(p);
		String channel = plugin.data.getString(p.getUniqueId() + ".channel");
		
		ChannelProperties cp = new ChannelProperties(true, channel);
		
		if(!plugin.channel.getBoolean(channel + ".always_appear")){
			UltraChatEvent uce = new UltraChatEvent(p, e.getMessage(), new HashSet<>(e.getRecipients()), ChatType.CHANNEL, cp);
			Bukkit.getServer().getPluginManager().callEvent(uce);
			// Remove recipients from main events to prevent double messages.
			e.getRecipients().clear();
			if(uce.isCancelled()) return;
			for(Player pl : uce.getRecipients()){
				if(plugin.data.getString(pl.getUniqueId() + ".channel").equals(channel)){
					if(pl.hasPermission(Objects.requireNonNull(plugin.channel.getString(channel + ".permission"))) || Objects.requireNonNull(plugin.channel.getString(channel + ".permission")).equalsIgnoreCase("none")){
						String format = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.channel.getString(channel + ".prefix")))
								+ ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.channel.getString(channel + ".format")))
								.replace("%prefix%", pf.getPrefix())
								.replace("%suffix%", pf.getSuffix())
								.replace("%player%", p.getDisplayName())
								+ pf.getColor();

						ComponentBuilder cb = new ComponentBuilder("");
						cb.append(JComponentManager.formatComponents(format, p));
						cb.append(new TextComponent(TextComponent.fromLegacyText(plugin.chatColorUtil.translateChatColor(uce.getMessage()), pf.getColor())), ComponentBuilder.FormatRetention.NONE);
						pl.spigot().sendMessage(cb.create());
					}
				}
			}
		}
		else{
			UltraChatEvent uce = new UltraChatEvent(p, e.getMessage(), new HashSet<>(e.getRecipients()), ChatType.CHANNEL, cp);
			Bukkit.getServer().getPluginManager().callEvent(uce);
			e.getRecipients().clear();
			if(!uce.isCancelled())
				for(Player pl : uce.getRecipients()){
					String formats = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.channel.getString(channel + ".prefix")))
							+ ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.channel.getString(channel + ".format")))
							.replace("%prefix%", pf.getPrefix())
							.replace("%suffix%", pf.getSuffix())
							.replace("%player%", p.getDisplayName())
							+ pf.getColor();

					ComponentBuilder cb = new ComponentBuilder("");
					cb.append(JComponentManager.formatComponents(formats, p));
					cb.append(new TextComponent(TextComponent.fromLegacyText(plugin.chatColorUtil.translateChatColor(uce.getMessage()), pf.getColor())), ComponentBuilder.FormatRetention.NONE);
					pl.spigot().sendMessage(cb.create());
				}
		}
	}

}
