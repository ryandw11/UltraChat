package me.ryandw11.ultrachat.api.events.properties;

public interface ChatProperties {

	/**
	 * @deprecated As of version 2.5 this will always be true as the plugin is always in JSON mode.
	 * @return If the plugin has json components enabled.
	 */
	boolean isComponent();

}
