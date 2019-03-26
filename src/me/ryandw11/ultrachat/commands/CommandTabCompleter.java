package me.ryandw11.ultrachat.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandTabCompleter implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
		if(cmd.getName().equalsIgnoreCase("chat")){
			ArrayList<String> completions = new ArrayList<>();
			if(args.length == 1){
				completions = new ArrayList<>( Arrays.asList("help", "stop", "broadcast", "clear", "sjoin", "leave", "join", "color", "raw", "reload", "hooks"));
				completions = getAppliableTabCompleters(args.length == 1 ? args[0] : "", completions);
			}else{
				if(args[0].equalsIgnoreCase("help")){
					completions = new ArrayList<>(Arrays.asList("2"));
	                completions = getAppliableTabCompleters(args.length == 2 ? args[1] : "", completions);
				}
				else{
					return null;
				}
			}
			Collections.sort(completions);
			return completions;
		}
		
		return null;
	}
	
	public ArrayList<String> getAppliableTabCompleters(String arg, ArrayList<String> completions) {
	       if (arg == null || arg.equalsIgnoreCase("")) {
	           return completions;
	       }
	       ArrayList<String> valid = new ArrayList<>();
	       for (String posib : completions) {
	           if (posib.startsWith(arg)) {
	               valid.add(posib);
	           }
	       }
	       return valid;
	   }

}
