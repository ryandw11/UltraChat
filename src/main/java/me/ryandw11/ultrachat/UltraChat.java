package me.ryandw11.ultrachat;

import me.ryandw11.ultrachat.commands.ChatCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * The main class of UltraChat.
 */
public class UltraChat extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("chat")).setExecutor(new ChatCommand(this));
        getLogger().info("UltraChat v" + getDescription().getVersion() + " was successfully enabled.");
    }
}
