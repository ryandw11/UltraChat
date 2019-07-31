package me.ryandw11.ultrachat.api.placeholders;

import java.util.UUID;

public interface PlaceholderAddon {
	
	public String getName();
	public String getString(UUID p);

}
