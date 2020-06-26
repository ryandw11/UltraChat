package me.ryandw11.ultrachat.commands;

import com.sun.istack.internal.NotNull;
import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.UltraChatAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 * @author Ryandw11
 */
public class NewChatCommand implements CommandExecutor {


    private UltraChat plugin;

    public NewChatCommand(UltraChat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        // This is not a switch statement since I want to check the argument length along with the sub command name.
        if (args.length == 0) {
            sender.sendMessage(ChatColor.BLUE + "=============={" + ChatColor.GREEN + "Ultra Chat" + ChatColor.BLUE + "}==============");
            sender.sendMessage(ChatColor.BLUE + "Plugin developed by:" + ChatColor.GREEN + " Ryandw11");
            sender.sendMessage(ChatColor.BLUE + "Version: " + ChatColor.GREEN + String.format("%s", plugin.getDescription().getVersion()));
            sender.sendMessage(ChatColor.BLUE + "Plugin wiki:" + ChatColor.GREEN + " https://github.com/ryandw11/UltraChat/wiki");
            sender.sendMessage(ChatColor.BLUE + "Do " + ChatColor.GREEN + " /chat help " + ChatColor.BLUE + "for the list of commands!");
            sender.sendMessage(ChatColor.BLUE + "=============={" + ChatColor.GREEN + "Ultra Chat" + ChatColor.BLUE + "}==============");

        } else if (args.length == 1 && args[0].equalsIgnoreCase("stop")) {
            if (sender.hasPermission("ultrachat.stopchat")) {

                if (plugin.isChatStopped) {
                    plugin.isChatStopped = false;
                    sender.sendMessage(Lang.CHAT_UNSTOP_PERSONAL.toString());
                    Bukkit.getServer().broadcastMessage(Lang.CHAT_STOP_OFF.toString().replace("%p", sender instanceof Player ? ((Player) sender).getDisplayName() :
                            "CONSOLE"));
                } else {
                    plugin.isChatStopped = true;
                    sender.sendMessage(Lang.CHAT_STOP_PERSONAL.toString());
                    Bukkit.getServer().broadcastMessage(Lang.CHAT_STOP_ON.toString().replace("%p", sender instanceof Player ? ((Player) sender).getDisplayName() :
                            "CONSOLE"));
                }


            } else {
                sender.sendMessage(Lang.NO_PERM.toString());
            }


        } else if (args.length > 1 && args[0].equalsIgnoreCase("broadcast")) {
            if (sender.hasPermission("ultrachat.broadcast")) {
                StringBuilder message = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    message.append(" ").append(args[i]);
                }
                Bukkit.getServer().broadcastMessage(Lang.BROADCAST_PREFIX.toString() + ChatColor.translateAlternateColorCodes('&', message.toString()));
            } else {
                sender.sendMessage(Lang.NO_PERM.toString());
            }

        } else if (args.length == 1 && args[0].equalsIgnoreCase("broadcast")) {
            if (sender.hasPermission("ultrachat.broadcast")) {
                sender.sendMessage(ChatColor.RED + "Invalid Usage: /chat broadcast (broadcast)");
                return true;
            }
            sender.sendMessage(Lang.NO_PERM.toString());
        } else if (args.length == 1 && args[0].equalsIgnoreCase("clear")) {
            if (sender.hasPermission("ultrachat.clearchat")) {
                for (int i = 0; i < 100; i++) {
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendMessage(" ");
                    }
                }
                Bukkit.getServer().broadcastMessage(Lang.CHAT_CLEAR.toString().replace("%p", sender.getName()));

            } else {
                sender.sendMessage(Lang.NO_PERM.toString());
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("sjoin")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command is for players only!");
                return true;
            }
            Player p = (Player) sender;
            if (sender.hasPermission("ultrachat.sjoin")) {
                if (plugin.data.getBoolean(p.getUniqueId().toString() + ".sjoin")) {
                    sender.sendMessage(Lang.SJOIN_SHOWN.toString());
                    plugin.data.set(p.getUniqueId().toString() + ".sjoin", false);
                    plugin.saveFile();
                } else {
                    plugin.data.set(p.getUniqueId().toString() + ".sjoin", true);
                    plugin.saveFile();
                    sender.sendMessage(Lang.SJOIN_HIDE.toString());
                }
            } else {
                sender.sendMessage(Lang.NO_PERM.toString());
            }

        } else if (args.length == 2 && args[0].equalsIgnoreCase("sjoin")) {
            if (sender.hasPermission("ultrachat.sjoin.others")) {
                Player pl = Bukkit.getServer().getPlayer(args[1]);
                if (pl == null) {
                    sender.sendMessage(ChatColor.RED + "Player can not be null!");
                    return true;
                }
                if (plugin.data.getBoolean(pl.getUniqueId().toString() + ".sjoin")) {
                    sender.sendMessage(String.format(Lang.SJOIN_OTHER_SHOW.toString(), pl.getName()));
                    plugin.data.set(pl.getUniqueId().toString() + ".sjoin", false);
                    plugin.saveFile();
                } else {
                    plugin.data.set(pl.getUniqueId().toString() + ".sjoin", true);
                    plugin.saveFile();
                    sender.sendMessage(String.format(Lang.SJOIN_OTHER_HIDE.toString(), pl.getName()));
                }
            } else {
                sender.sendMessage(Lang.NO_PERM.toString());
            }

        } else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
            if (sender.hasPermission("ultrachat.fakejoin")) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Join_Message")).replace("%player%", args[1])));
            } else {
                sender.sendMessage(Lang.NO_PERM.toString());
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("leave")) {
            if (sender.hasPermission("ultrachat.fakeleave")) {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Leave_Message")).replace("%player%", args[1])));
            } else {
                sender.sendMessage(Lang.NO_PERM.toString());
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            if (!sender.hasPermission("ultrachat.help")) {
            	sender.sendMessage(Lang.NO_PERM.toString());
            	return true;
			}
            sender.sendMessage(ChatColor.BLUE + "-------------------=[" + ChatColor.GREEN + "Ultra Chat" + ChatColor.BLUE + "]=-------------------");
            sender.sendMessage(ChatColor.GREEN + "/chat help" + ChatColor.BLUE + "  The help command.");
            sender.sendMessage(ChatColor.GREEN + "/chat stop" + ChatColor.BLUE + "  Stop the chat.");
            sender.sendMessage(ChatColor.GREEN + "/chat broadcast (message)" + ChatColor.BLUE + "  Send a message to every player.");
            sender.sendMessage(ChatColor.GREEN + "/sc (message)" + ChatColor.BLUE + "  Talk in staff chat.");
            sender.sendMessage(ChatColor.GREEN + "/sctoggle" + ChatColor.BLUE + "  Toggle staff chat.");
            sender.sendMessage(ChatColor.GREEN + "/spy" + ChatColor.BLUE + "  Enable or disable command spy.");
            sender.sendMessage(ChatColor.GREEN + "/chat clear" + ChatColor.BLUE + "  Clear the chat.");
            sender.sendMessage(ChatColor.GREEN + "/chat sjoin" + ChatColor.BLUE + "  Silently join and leave the server.");
            sender.sendMessage(ChatColor.BLUE + "Do" + ChatColor.GREEN + " /chat help 2" + ChatColor.BLUE + " for more help pages!");
            sender.sendMessage(ChatColor.BLUE + "Plugin made by: " + ChatColor.GREEN + "Ryandw11" + ChatColor.BLUE + "! Help Page: " + ChatColor.GREEN + "1/2" + ChatColor.BLUE + ".");
            sender.sendMessage(ChatColor.BLUE + "---------------------------------------------------");

        } else if (args.length == 2 && args[0].equalsIgnoreCase("help")) {
            if (sender.hasPermission("ultrachat.help")) {
                if (args[1].equals("2")) {
                    sender.sendMessage(ChatColor.BLUE + "-------------------=[" + ChatColor.GREEN + "Ultra Chat" + ChatColor.BLUE + "]=-------------------");
                    sender.sendMessage(ChatColor.GREEN + "/chat leave (player)" + ChatColor.BLUE + "  Send a fake leave message.");
                    sender.sendMessage(ChatColor.GREEN + "/chat join (player) " + ChatColor.BLUE + "  Send a fake join message.");
                    sender.sendMessage(ChatColor.GREEN + "/chat color" + ChatColor.BLUE + "  Change your chat color.");
                    sender.sendMessage(ChatColor.GREEN + "/chat raw {Message}" + ChatColor.BLUE + "  Send a message in the chat without a prefix.");
                    sender.sendMessage(ChatColor.GREEN + "/chat reload" + ChatColor.BLUE + "  Reload the config file.");
                    sender.sendMessage(ChatColor.GREEN + "/chat hooks" + ChatColor.BLUE + "  Get the current plugin hooks!");
                    sender.sendMessage(ChatColor.GREEN + "/channels" + ChatColor.BLUE + "  The channel command.");
                    sender.sendMessage(ChatColor.BLUE + "Plugin made by: " + ChatColor.GREEN + "Ryandw11" + ChatColor.BLUE + "! Help Page: " + ChatColor.GREEN + "2/2" + ChatColor.BLUE + ".");
                    sender.sendMessage(ChatColor.BLUE + "---------------------------------------------------");
                }
                if (!(args[1].equals("1") || args[1].equals("2"))) {
                    sender.sendMessage(Lang.HELP_PAGE_ERROR.toString());
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("No_Permission"))));
            }
        } else if (args.length > 1 && args[0].equalsIgnoreCase("raw")) {
            if (sender.hasPermission("ultrachat.raw")) {
                StringBuilder message = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    if (i == 1) {
                        message.append(args[i]);
                    } else {
                        message.append(" ").append(args[i]);
                    }
                }
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', message.toString())));
            } else {
                sender.sendMessage(Lang.NO_PERM.toString());
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("ultrachat.reload")) {
                plugin.reloadConfig();
                plugin.loadChannel();
                sender.sendMessage(Lang.CONFIG_RELOAD.toString());
            } else {
                sender.sendMessage(Lang.NO_PERM.toString());
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("color")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command is for players only!");
                return true;
            }
            if (sender.hasPermission("ultrachat.color"))
                plugin.getColorGUI().openGUI((Player) sender, 1);
            else
                sender.sendMessage(Lang.NO_PERM.toString());
        } else if (args.length == 1 && args[0].equalsIgnoreCase("hooks")) {
            if (sender.hasPermission("ultrachat.hooks")) {
                UltraChatAPI uapi = new UltraChatAPI();
                sender.sendMessage(ChatColor.BLUE + "Ultra Chat Hooks:");
                if (uapi.getActiveHooks() == null) {
                    sender.sendMessage(ChatColor.GREEN + "No hooks are currently active!");
                } else {
                    for (String st : uapi.getActiveHooks()) {
                        sender.sendMessage(ChatColor.GREEN + " - " + st);
                    }
                }
            } else {
                sender.sendMessage(Lang.NO_PERM.toString());
            }
        } else {
            sender.sendMessage(Lang.CHAT_CMD_NOT_VALID.toString());
        }

        return false;
    }

}
