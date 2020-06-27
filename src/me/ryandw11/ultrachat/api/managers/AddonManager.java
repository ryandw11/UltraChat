package me.ryandw11.ultrachat.api.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.ryandw11.ultrachat.api.UltraChatAPI;
import me.ryandw11.ultrachat.api.placeholders.PlaceholderAddon;

/**
 * This class handles any addons to the plugin.
 * <p>The instance of this class can be retrieved from {@link UltraChatAPI#getAddonManager()}</p>
 */
public class AddonManager {
	private List<PlaceholderAddon> pa;
	public AddonManager() {
		pa = new ArrayList<>();
	}

	/**
	 * Add an addon
	 * @param pa The addon to add.
	 */
	public void addAddon(PlaceholderAddon pa) {
		this.pa.add(pa);
	}

	/**
	 * Get the list of addons.
	 * @return The list of addons.
	 */
	public List<PlaceholderAddon> getAddons(){
		return pa;
	}

	/**
	 * Replace the placeholders in a string.
	 * @param s The string.
	 * @param p The player UUID.
	 * @return The string with the placeholders replaced.
	 */
	public String replacePlaceholders(String s, UUID p) {
		String output = s;
		for(PlaceholderAddon fs : pa) {
			output = output.replace(fs.getName(), fs.getString(p));
		}
		return output;
	}
}
