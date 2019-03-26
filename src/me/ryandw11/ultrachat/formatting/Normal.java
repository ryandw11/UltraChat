package me.ryandw11.ultrachat.formatting;

import java.util.UnknownFormatConversionException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import me.ryandw11.ultrachat.UltraChat;
/**
 * Normal chat formatting with no channels or Json.
 * @author Ryandw11
 *
 */
public class Normal implements Listener {
	private UltraChat plugin;
	public Normal(){
		plugin = UltraChat.plugin;
	}
	//TODO Fix this and make it work
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		PlayerFormatting pf = new PlayerFormatting(e.getPlayer());
		Player p = e.getPlayer();
		if(p.hasPermission("ultrachat.chat.color")){
			e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
		}
		if(p.isOp()){
			try{
				e.setFormat(pf.getOpFormat().replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()).replace("%player%", "%s") + pf.getColor() + "%s");
			}catch (UnknownFormatConversionException ex){
				p.sendMessage(ChatColor.RED + "A fatal error has occured. Check the console for more info!");
				Bukkit.getLogger().warning(ChatColor.RED + "A fatal error has occured!");
				Bukkit.getLogger().warning(ChatColor.RED + "Your formatting seems to be a bit off! Check the config.yml Fortmat: OP");
			}
		}else{
			int i = 1;
			while(i <= plugin.getConfig().getInt("Custom_Chat.Chat_Count")){
				if(p.hasPermission(plugin.getConfig().getString("Custom_Chat." + i + ".Permission"))){
					try{
						e.setFormat(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Custom_Chat." + i +".Format").replace("%player%", "%s").replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix())) + pf.getColor() + "%s"));	
					}catch (UnknownFormatConversionException ex){
						p.sendMessage(ChatColor.RED + "A fatal error has occured. Contact an administrator!");
						Bukkit.getLogger().warning(ChatColor.RED + "A fatal error has occured!");
						Bukkit.getLogger().warning(ChatColor.RED + "Your formatting seems to be a bit off! Check the config.yml Fortmat #: " + i);
					}
					
					return;
				}
				i++;
			}
			try{
				e.setFormat(pf.getDefaultFormat().replace("%prefix%", pf.getPrefix()).replace("%suffix%", pf.getSuffix()).replace("%player%", "%s") + pf.getColor() + "%s");
			}catch(UnknownFormatConversionException ex){
				p.sendMessage(ChatColor.RED + "A fatal error has occured. Contact an administrator!");
				Bukkit.getLogger().warning(ChatColor.RED + "A fatal error has occured!");
				Bukkit.getLogger().warning(ChatColor.RED + "Your formatting seems to be a bit off! Check the config.yml Fortmat: Defualt");
			}
		}
	}

}
