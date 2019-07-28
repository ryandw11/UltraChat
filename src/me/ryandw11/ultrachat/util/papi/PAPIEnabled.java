package me.ryandw11.ultrachat.util.papi;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class PAPIEnabled implements PlaceHolderAPIHook{

	@Override
	public String translatePlaceholders(String s, Player p) {
		return PlaceholderAPI.setPlaceholders(p, s);
	}

	
}
