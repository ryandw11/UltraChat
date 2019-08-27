package me.ryandw11.ultrachat.util.papi;

import org.bukkit.entity.Player;

public class PAPIDisabled implements PlaceHolderAPIHook {

	@Override
	public String translatePlaceholders(String s, Player p) {
		return s;
	}

}
