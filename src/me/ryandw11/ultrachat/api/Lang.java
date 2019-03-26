package me.ryandw11.ultrachat.api;

import org.bukkit.configuration.file.YamlConfiguration;

import net.md_5.bungee.api.ChatColor;
/**
 * Language file
 */
public enum Lang {
	PLUGIN_PREFIX("plugin-prefix","&7[&aUltraChat&7]"),
	NO_PERM("no-perm", "&cYou do not have permission for this command!"),
	ERROR_CHANNEL_ENABLED("error-channel-enabled", "&cError: Chat channels are not enabled on this server!"),
	ERROR_CHANNEL_USAGE("error-channel-usage", "&cError: Use /channel {channel}!"),
	CHANNEL_JSON_HOVER("channel-json-hover", "&5Click to join the channel!"),
	CHAT_STOP_OFF("chat-stop-off", "&eThe chat has been &2Enabled &eby:&5 %p&e!"),
	CHAT_STOP_ON("chat-stop-on", "&eThe chat has been &4disabled &eby:&5 %p&e!"),
	BROADCAST_PREFIX("broadcast-prefix", "&7[&4UltraChat&7] "),
	CHAT_CLEAR("chat-clear", "&3The chat has been cleared by &9%p&3!"),
	CONFIG_RELOAD("config-reload", "&aThe config has been reloaded!"),
	CHAT_CMD_NOT_VALID("chat-cmd-not-valid", "&cThat is not a valid command. Do /chat help for help!"),
	CMD_SPY_ON("cmd-spy-on", "&bCommand Spy Enabled!"),
	CMD_SPY_OFF("cmd-spy-off", "&bCommand Spy Disabled!"),
	OTH_CMD_SPY_ON("oth-cmd-spy-on", "&a%p&b''s command spy has been enabled!"),
	OTH_CMD_SPY_OFF("oth-cmd-spy-off", "&a%p&b''s command spy has ben disabled!"),
	STAFF_CHAT_ON("staff-chat-on", "&dStaff chat has been enabled!"),
	STAFF_CHAT_OFF("staff-chat-off", "&dStaff chat has been disabled!"),
	STAFF_CHAT_FORMAT("staff-chat-format", "&7[&dStaff Chat&7] &9%p: &3%s"),
	COLOR_GUI("color-gui", "&aChat Color"),
	SILENT_JOIN_MESSAGE("silent-join-message", "%p &7has joined the game silently!"),
	SILENT_LEAVE_MESSAGE("silent-leave-message", "%p &7has left the game silently!"),
	NO_SWEAR("no-swear", "&cSwearing is not allowed on this server!"),
	MENTION("mention", "&aSomeone has mentioned you!"),
	CMD_SPY_FORMAT("cmd-spy-format", "&3[&6CommandSpy&3]&b %p: &3%s"),
	STOP_CHAT_MESSAGE("stop-chat-message", "&cThe chat is currently not active."),
	CONSOLE_CHAT_LOG("console-chat-log", "%p: %s");

	
	 private String path;
	 private String def;
	 private static YamlConfiguration LANG;
	 
	    /**
	    * Lang enum constructor.
	    * @param path The string path.
	    * @param start The default string.
	    */
	    Lang(String path, String start) {
	        this.path = path;
	        this.def = start;
	    }
	 
	    /**
	    * @param config The config to set.
	    */
	    public static void setFile(YamlConfiguration config) {
	        LANG = config;
	    }
	 
	    @Override
	    public String toString() {
	        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
	    }
	    
	    public String toDefaultString(){
	    	return LANG.getString(this.path, def);
	    }
	 
	    /**
	    * Get the default value of the path.
	    * @return The default value of the path.
	    */
	    public String getDefault() {
	        return this.def;
	    }
	 
	    /**
	    * Get the path to the string.
	    * @return The path to the string.
	    */
	    public String getPath() {
	        return this.path;
	    }

}
