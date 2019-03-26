package me.ryandw11.ultrachat.api;

import net.md_5.bungee.api.ChatColor;

public class Util {
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static ChatColor getColorFromCode(String s){
		String b = s.substring(1);
		switch (b){
		case "1":
			return ChatColor.DARK_BLUE;
		case "2":
			return ChatColor.DARK_GREEN;
		case "3":
			return ChatColor.DARK_AQUA;
		case "4":
			return ChatColor.DARK_RED;
		case "5":
			return ChatColor.DARK_PURPLE;
		case "6":
			return ChatColor.GOLD;
		case "7":
			return ChatColor.GRAY;
		case "8":
			return ChatColor.DARK_GRAY;
		case "9":
			return ChatColor.BLUE;
		case "c":
			return ChatColor.RED;
		case "d":
			return ChatColor.LIGHT_PURPLE;
		case "e":
			return ChatColor.YELLOW;
		case "f":
			return ChatColor.WHITE;
		case "a":
			return ChatColor.GREEN;
		case "b":
			return ChatColor.AQUA;
		}
		return null;
	}
	

}
