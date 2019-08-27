package me.ryandw11.ultrachat.api.events.properties;

public class RangeProperties implements ChatProperties {
	
	private boolean json;
	private RangeType rt;
	public RangeProperties(boolean json, RangeType rt) {
		this.json = json;
		this.rt = rt;
	}

	@Override
	public boolean isComponent() {
		return json;
	}
	
	/**
	 * Get the Range mode in use.
	 * @return The range type.
	 */
	public RangeType getType() {
		return rt;
	}

}
