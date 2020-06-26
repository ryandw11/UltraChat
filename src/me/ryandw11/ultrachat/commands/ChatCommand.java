package me.ryandw11.ultrachat.commands;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.UltraChatAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 
 * @author Ryandw11
 *
 */
public class ChatCommand implements CommandExecutor {

	
	private UltraChat plugin;
	public ChatCommand(){
		plugin = UltraChat.plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(!(sender instanceof Player )){
			plugin.getLogger().info(ChatColor.RED + "This command is for players only!");
			return true;
		}
		Player p = (Player) sender;
		
			
			if(args.length == 0){
				p.sendMessage(ChatColor.BLUE + "=============={" + ChatColor.GREEN + "Ultra Chat" + ChatColor.BLUE + "}==============");
				p.sendMessage(ChatColor.BLUE + "Plugin developed by:" + ChatColor.GREEN + " Ryandw11");
				p.sendMessage(ChatColor.BLUE + "Version: " + ChatColor.GREEN + String.format("%s", plugin.getDescription().getVersion()));
				p.sendMessage(ChatColor.BLUE + "Plugin wiki:" + ChatColor.GREEN + " https://github.com/ryandw11/UltraChat/wiki");
				p.sendMessage(ChatColor.BLUE + "Do " + ChatColor.GREEN + " /chat help " + ChatColor.BLUE + "for the list of commands!");
				p.sendMessage(ChatColor.BLUE + "=============={" + ChatColor.GREEN + "Ultra Chat" + ChatColor.BLUE + "}==============");
				
			}
			
			
			//Chat Stop Command
			else if(args.length == 1 && args[0].equalsIgnoreCase("stop")){
				if(p.hasPermission("ultrachat.stopchat")){
					
					if(plugin.chatStop == true){
						plugin.chatStop = false;
						p.sendMessage(Lang.CHAT_UNSTOP_PERSONAL.toString());
						Bukkit.getServer().broadcastMessage(Lang.CHAT_STOP_OFF.toString().replace("%p", p.getDisplayName()));
					}
					else if(plugin.chatStop == false){
						plugin.chatStop = true;
						p.sendMessage(Lang.CHAT_STOP_PERSONAL.toString());
						Bukkit.getServer().broadcastMessage(Lang.CHAT_STOP_ON.toString().replace("%p", p.getDisplayName()));
					}
					else{
						p.sendMessage("An error has occured!");
					}
					
					
					
				}
				else{
					p.sendMessage(Lang.NO_PERM.toString());
				}
				
				
			}
			
				
				else if(args.length >= 1 && args[0].equalsIgnoreCase("broadcast")){
					if(p.hasPermission("ultrachat.broadcast")){
						String Message = "";
						for (int i = 1; i < args.length; i++){
							Message = Message + " " + args[i];
						}
						Bukkit.getServer().broadcastMessage(Lang.BROADCAST_PREFIX.toString() + ChatColor.translateAlternateColorCodes('&', Message));
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
					
				}
				else if(args.length == 1 && args[0].equalsIgnoreCase("broadcast")){
					if(p.hasPermission("ultrachat.broadcast")){
						p.sendMessage(ChatColor.RED + "Invalid Usage: /chat broadcast (broadcast)");
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
				}//end of broadcast======================================================================================
			//
			//================================================================================================================
			
				else if(args.length == 1 && args[0].equalsIgnoreCase("clear")){
					if(p.hasPermission("ultrachat.clearchat")){
						for (int i = 0; i < 100; i++){
							for(Player pl : Bukkit.getOnlinePlayers()){
			                  pl.sendMessage(" ");
							}
			            }
					Bukkit.getServer().broadcastMessage(Lang.CHAT_CLEAR.toString().replace("%p", p.getName()));
					
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
				}
			//========================================================
			
			//==========================================================
			
				else if(args.length == 1 && args[0].equalsIgnoreCase("sjoin")){
					if(p.hasPermission("ultrachat.sjoin")){
						if(plugin.data.getBoolean(p.getUniqueId().toString() + ".sjoin")){
							p.sendMessage(Lang.SJOIN_SHOWN.toString());
							plugin.data.set(p.getUniqueId().toString() + ".sjoin", false);
							plugin.saveFile();
						}
						else{
							plugin.data.set(p.getUniqueId().toString() + ".sjoin", true);
							plugin.saveFile();
							p.sendMessage(Lang.SJOIN_HIDE.toString()); 
						}
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
					
				}
				else if(args.length == 2 && args[0].equalsIgnoreCase("sjoin")){
					if(p.hasPermission("ultrachat.sjoin.others")){
						Player pl = (Player) Bukkit.getServer().getPlayer(args[1]);
						if(pl == null){p.sendMessage(ChatColor.RED + "Player can not be null!"); return true;}
						if(plugin.data.getBoolean(p.getUniqueId().toString() + ".sjoin")){
							p.sendMessage(String.format(Lang.SJOIN_OTHER_SHOW.toString(), pl.getName()));
							plugin.data.set(p.getUniqueId().toString() + ".sjoin", false);
							plugin.saveFile();
						}
						else{
							plugin.data.set(p.getUniqueId().toString() + ".sjoin", true);
							plugin.saveFile();
							p.sendMessage(String.format(Lang.SJOIN_OTHER_HIDE.toString(), pl.getName())); 
						}
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
					
				}
			
			//======================================================================================================
			//                                      Break
			//======================================================================================================
			
			
				else if(args.length == 2 && args[0].equalsIgnoreCase("join")){
					if(p.hasPermission("ultrachat.fakejoin")){
						Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Join_Message").replace("%player%", args[1])));
						
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
				}//end of fake join
				
				else if(args.length == 2 && args[0].equalsIgnoreCase("leave")){
					if(p.hasPermission("ultrachat.fakeleave")){
						Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Leave_Message").replace("%player%", args[1])));
						
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
				}//end of fake leave
			//====================================================================
			
			//================================================================
				else if(args.length == 1 && args[0].equalsIgnoreCase("help")){
						if(!p.hasPermission("ultrachat.help"))
						p.sendMessage(ChatColor.BLUE + "-------------------=[" + ChatColor.GREEN + "Utlra Chat" + ChatColor.BLUE + "]=-------------------");
						p.sendMessage(ChatColor.GREEN + "/chat help" + ChatColor.BLUE + "  The help command.");
						p.sendMessage(ChatColor.GREEN + "/chat stop" + ChatColor.BLUE + "  Stop the chat.");
						p.sendMessage(ChatColor.GREEN + "/chat broadcast (message)" + ChatColor.BLUE + "  Send a message to every player.");
						p.sendMessage(ChatColor.GREEN + "/sc (message)" + ChatColor.BLUE + "  Talk in staff chat.");
						p.sendMessage(ChatColor.GREEN + "/sctoggle" + ChatColor.BLUE + "  Toggle staff chat.");
						p.sendMessage(ChatColor.GREEN + "/spy" + ChatColor.BLUE + "  Enable or disable command spy.");
						p.sendMessage(ChatColor.GREEN + "/chat clear" + ChatColor.BLUE + "  Clear the chat.");
						p.sendMessage(ChatColor.GREEN + "/chat sjoin" + ChatColor.BLUE + "  Silently join and leave the server.");
						p.sendMessage(ChatColor.GREEN + "/chat leave (player)" + ChatColor.BLUE + "  Send a fake leave message.");
						p.sendMessage(ChatColor.GREEN + "/chat join (player) " + ChatColor.BLUE + "  Send a fake join message.");
						p.sendMessage(ChatColor.BLUE + "Do" + ChatColor.GREEN + " /chat help 2" + ChatColor.BLUE + " for more help pages!");
						p.sendMessage(ChatColor.BLUE + "Plugin made by: " + ChatColor.GREEN + "Ryandw11" + ChatColor.BLUE + "! Help Page: " + ChatColor.GREEN + "1/2" + ChatColor.BLUE + ".");
						p.sendMessage(ChatColor.BLUE + "---------------------------------------------------");
					
				}//end of  help
				
				else if(args.length == 2 && args[0].equalsIgnoreCase("help")){
					if(p.hasPermission("ultrachat.help")){
						if(args[1].equals("2")){
							p.sendMessage(ChatColor.BLUE + "-------------------=[" + ChatColor.GREEN + "Utlra Chat" + ChatColor.BLUE + "]=-------------------");
							p.sendMessage(ChatColor.GREEN + "/chat color" + ChatColor.BLUE + "  Change your chat color.");
							p.sendMessage(ChatColor.GREEN + "/chat raw {Message}" + ChatColor.BLUE + "  Send a message in the chat without a prefix.");
							p.sendMessage(ChatColor.GREEN + "/chat reload" + ChatColor.BLUE + "  Reload the config file.");
							p.sendMessage(ChatColor.GREEN + "/chat hooks" + ChatColor.BLUE + "  Get the current plugin hooks!");
							p.sendMessage(ChatColor.GREEN + "/channels" + ChatColor.BLUE + "  The channel command.");
							p.sendMessage(ChatColor.BLUE + "Plugin made by: " + ChatColor.GREEN + "Ryandw11" + ChatColor.BLUE + "! Help Page: " + ChatColor.GREEN + "2/2" + ChatColor.BLUE + ".");
							p.sendMessage(ChatColor.BLUE + "---------------------------------------------------");
						}
						if(!(args[1].equals("1") || args[1].equals("2"))){
							p.sendMessage(Lang.HELP_PAGE_ERROR.toString());
						}
					}
					else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("No_Permission")));
					}
				}//end of help page ========
			
				else if(args.length > 1 && args[0].equalsIgnoreCase("raw")){
					if(p.hasPermission("ultrachat.raw")){
						String Message = "";
						for (int i = 1; i < args.length; i++){
							if(i == 1){
								Message = Message + args[i]; // Fixed bugs
							}else{
								Message = Message + " " + args[i];
							}
						}
						Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', Message)));
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
				}
			
				else if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
					if(p.hasPermission("ultrachat.reload")){
						plugin.reloadConfig();
						plugin.loadChannel();
						p.sendMessage(Lang.CONFIG_RELOAD.toString());
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
				}
				else if(args.length == 1 && args[0].equalsIgnoreCase("color")){
					if(p.hasPermission("ultrachat.color")){
						plugin.getColorGUI().openGUI(p.getPlayer(), 1);
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
				}
				else if(args.length == 1 && args[0].equalsIgnoreCase("hooks")){
					if(p.hasPermission("ultrachat.hooks")){
						UltraChatAPI uapi = new UltraChatAPI();
						p.sendMessage(ChatColor.BLUE + "Ultra Chat Hooks:");
						if(uapi.getActiveHooks() == null){
							p.sendMessage(ChatColor.GREEN + "No hooks are currently active!");
						}else{
							for(String st : uapi.getActiveHooks()){
								p.sendMessage(ChatColor.GREEN + " - " + st);
							}
						}
					}
					else{
						p.sendMessage(Lang.NO_PERM.toString());
					}
				}
//=========================================----------------------------------------===========================================
			else{
				p.sendMessage(Lang.CHAT_CMD_NOT_VALID.toString());
			}
		
		return false;
	}

}
