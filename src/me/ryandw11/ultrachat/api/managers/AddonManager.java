package me.ryandw11.ultrachat.api.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.ryandw11.ultrachat.api.placeholders.PlaceholderAddon;

public class AddonManager {
	private List<PlaceholderAddon> pa;
	public AddonManager() {
		pa = new ArrayList<>();
	}
	
	public void addAddon(PlaceholderAddon pa) {
		this.pa.add(pa);
	}
	
	public List<PlaceholderAddon> getAddons(){
		return pa;
	}
	
	public String replacePlaceholders(String s, UUID p) {
		String output = s;
		for(PlaceholderAddon fs : pa) {
			output.replace(fs.getName(), fs.getString(p));
		}
		return output;
	}
}
