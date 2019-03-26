package me.ryandw11.ultrachat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;

public class StaffChat implements CommandExecutor {
	private UltraChat plugin;
	public StaffChat(){
		plugin = UltraChat.plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		CommandSender p = sender;
		if(p.hasPermission("ultrachat.staffchat")){
			String message = "";
				
			if(args.length > 0){
				
				for (int i = 0; i < args.length; i++){
					message = message + " " + args[i];
					
				}
				for(Player p1 : Bukkit.getOnlinePlayers()){
					if(p1.hasPermission("ultrachat.staffchat")){
						if(!plugin.stafftoggle.contains(p1.getUniqueId())){
							p1.sendMessage(Lang.STAFF_CHAT_FORMAT.toString().replace("%p", p.getName()).replace("%s", message));
						}// end of if
					}
				}//end of for
				plugin.getLogger().info(Lang.STAFF_CHAT_FORMAT.toString().replace("%p", p.getName()).replace("%s", message));
			}//end
			else{
				p.sendMessage(ChatColor.RED + "Not enough words. Ussage: /sc (message)");
			}
				
			
		}//end of perm check
		else{
			p.sendMessage(Lang.NO_PERM.toString());
				
		}
		
		
		
		
		
		return false;
	}

}
