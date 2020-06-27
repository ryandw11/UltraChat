package me.ryandw11.ultrachat.commands;

import java.util.HashSet;

import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.ChatType;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.events.UltraChatEvent;
import me.ryandw11.ultrachat.api.events.properties.RangeProperties;
import me.ryandw11.ultrachat.api.events.properties.RangeType;
import me.ryandw11.ultrachat.api.managers.JComponentManager;
import me.ryandw11.ultrachat.formatting.PlayerFormatting;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

public class World implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command is for players only!");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("ultrachat.world")) {
			p.sendMessage(Lang.NO_PERM.toString());
			return true;
		}
		PlayerFormatting pf = new PlayerFormatting(p);

		RangeProperties rp = new RangeProperties(true, RangeType.WORLD);
		UltraChatEvent uce = new UltraChatEvent(p, this.getMessage(args, p),
				new HashSet<>(p.getWorld().getPlayers()), ChatType.RANGE, rp);
		Bukkit.getScheduler().runTaskAsynchronously(UltraChat.plugin, () -> {
			Bukkit.getServer().getPluginManager().callEvent(uce);

			if (uce.isCancelled()) {
				return;
			}
			
			String form = pf.getWorld().replace("%player%", p.getDisplayName())
					.replace("%prefix%", pf.getPrefix())
					.replace("%suffix%", pf.getSuffix()) + pf.getColor();
			ComponentBuilder cb = new ComponentBuilder("");
			cb.append(JComponentManager.formatComponents(form, p));
			cb.append(new TextComponent(TextComponent.fromLegacyText(pf.getColor() + UltraChat.plugin.chatColorUtil.translateChatColor(uce.getMessage(), p), pf.getColor())), ComponentBuilder.FormatRetention.NONE);
			
			for (Player pl : uce.getRecipients()) {
				pl.spigot().sendMessage(cb.create());
			}
		});

		return false;
	}

	private String getMessage(String[] args, Player p) {
		StringBuilder end = new StringBuilder();
		for (String s : args) {
			end.append(s).append(" ");
		}
		return end.toString();
	}

}
