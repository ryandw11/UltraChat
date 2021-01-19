package me.ryandw11.ultrachat.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.ChatType;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.UltraChatAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
/**
 * 
 * @author Ryandw11
 *
 */
public class ChannelCmd implements CommandExecutor {
	private UltraChat plugin;
	public ChannelCmd(){
		plugin = UltraChat.plugin;
	}
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {


		Player p = (Player) sender;
		if(!(p instanceof Player )) return true;
		if(!p.hasPermission("ultrachat.channel")){
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.NO_PERM.toString()));
			return true;
		}
		UltraChatAPI uapi = new UltraChatAPI();
		if(uapi.getChatType() != ChatType.CHANNEL){
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.ERROR_CHANNEL_ENABLED.toString()));
			return true;
		}
		if(args.length != 1 && args.length != 0){
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.ERROR_CHANNEL_USAGE.toString()));
		}
		else if(args.length == 0){
			p.sendMessage(ChatColor.BLUE + "-------------Chat Channels--------------");
			p.sendMessage(ChatColor.GREEN + "/channel {channel} - Change you channel. [Case Sensitive!]");
			p.sendMessage(ChatColor.GREEN + "/channel list - List the channels.");
		}else if(args.length == 1 && args[0].equalsIgnoreCase("list")){
			if(!p.hasPermission("ultrachat.channel.list")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.NO_PERM.toString()));
				return true;
			}
			@SuppressWarnings("unchecked")
			ArrayList<String> chnls = (ArrayList<String>) plugin.getConfig().get("Channel_List");
			p.sendMessage(ChatColor.BLUE + "-------------Channels List--------------");
			for(String st : chnls){
				ComponentBuilder cnl = new ComponentBuilder("- " + st);
				cnl.event(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', Lang.CHANNEL_JSON_HOVER.toString())).create() ) );
				cnl.event(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/channel " + st));
				cnl.color(ChatColor.GREEN);
				p.spigot().sendMessage(cnl.create());
			}
			p.sendMessage(ChatColor.BLUE + "--------------------------------------");
		}
		else{
			if(!plugin.channel.contains(args[0])){
				p.sendMessage(Lang.CHANNEL_NULL.toString());
				return true;
			}
			if(plugin.data.getString(p.getUniqueId() + ".channel").equalsIgnoreCase(args[0])){
				p.sendMessage(Lang.ERROR_CHANNEL_IN.toString());
			}
			else if(p.hasPermission(plugin.channel.getString(args[0] + ".permission")) || plugin.channel.getString(args[0] + ".permission").equalsIgnoreCase("none")){
				plugin.data.set(p.getUniqueId() + ".channel", args[0]);
				plugin.saveFile();
				p.sendMessage(Lang.CHANNEL_CHANGE.toString().replace("%s", args[0]));
			}
			else{
				p.sendMessage(Lang.NO_PERM_CHANNEL.toString());
			}
		}
		return false;
	}

}
