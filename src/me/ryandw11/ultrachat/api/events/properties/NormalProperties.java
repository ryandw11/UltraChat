package me.ryandw11.ultrachat.api.events.properties;

public class NormalProperties implements ChatProperties {
	
	private boolean json;
	public NormalProperties(boolean isJson) {
		this.json = isJson;
	}

	@Override
	public boolean isComponent() {
		return json;
	}
	
	

}
