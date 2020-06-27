package me.ryandw11.ultrachat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;

/**
 * Spy Command class.
 * @author Ryandw11
 *
 */
public class SpyCommand implements CommandExecutor {
	
	

	private UltraChat plugin;
	public SpyCommand(){
		plugin = UltraChat.plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {	
		if(!(sender instanceof Player )){
			plugin.getLogger().info("This command is for players only!");
			return true;
		}
		Player p = (Player) sender;
		
		if(args.length == 0){
		if (p.hasPermission("ultrachat.spy")) {
			if(!(args.length == 0)){
				p.sendMessage(ChatColor.RED + "Invailed subcommand! Ussage: /spy");
					
			}
			else{
				if(plugin.spyToggle.contains(p.getUniqueId())){
					p.sendMessage(Lang.CMD_SPY_OFF.toString());
					plugin.spyToggle.remove(p.getUniqueId());
					plugin.data.set(p.getUniqueId().toString() + ".spy", false);
					plugin.saveFile();
				}
				else{
					plugin.spyToggle.add(p.getUniqueId());
					p.sendMessage(Lang.CMD_SPY_ON.toString()); 
					plugin.data.set(p.getUniqueId().toString() + ".spy", true);
					plugin.saveFile();
				}
			}
						
		}//end of perm check
		}
		else if(args.length == 1){
			if(p.hasPermission("ultrachat.spy.others")){
				Player pl = (Player) Bukkit.getServer().getPlayer(args[0]);
				if(plugin.spyToggle.contains(pl.getUniqueId())){
					plugin.spyToggle.remove(pl.getUniqueId());
					p.sendMessage(Lang.OTH_CMD_SPY_OFF.toString().replace("%p", args[0]));
					plugin.data.set(pl.getUniqueId().toString() + ".spy", false);
					plugin.saveFile();
				}
				else{
					plugin.spyToggle.add(pl.getUniqueId());
					p.sendMessage(Lang.OTH_CMD_SPY_ON.toString().replace("%p", args[0]));
					plugin.data.set(pl.getUniqueId().toString() + ".spy", true);
					plugin.saveFile();
				}
			}
		}
		return false;
	}

}
