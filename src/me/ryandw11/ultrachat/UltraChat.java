package me.ryandw11.ultrachat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import me.ryandw11.ultrachat.api.ChatType;
import me.ryandw11.ultrachat.api.Lang;
import me.ryandw11.ultrachat.api.managers.AddonManager;
import me.ryandw11.ultrachat.chatcolor.ChatColorManager;
import me.ryandw11.ultrachat.chatcolor.ChatColorUtil_Latest;
import me.ryandw11.ultrachat.chatcolor.ChatColorUtil_Old;
import me.ryandw11.ultrachat.chatcolor.ChatColorUtils;
import me.ryandw11.ultrachat.commands.ChannelCmd;
import me.ryandw11.ultrachat.commands.ChatCommand;
import me.ryandw11.ultrachat.commands.CommandTabCompleter;
import me.ryandw11.ultrachat.commands.Global;
import me.ryandw11.ultrachat.commands.StaffChat;
import me.ryandw11.ultrachat.commands.StaffChatToggle;
import me.ryandw11.ultrachat.commands.World;
import me.ryandw11.ultrachat.commands.SpyCommand;
import me.ryandw11.ultrachat.formatting.ChannelJSON;
import me.ryandw11.ultrachat.formatting.NormalJSON;
import me.ryandw11.ultrachat.formatting.RangeJSON;
import me.ryandw11.ultrachat.gui.*;
import me.ryandw11.ultrachat.listner.ConsoleLogChat;
import me.ryandw11.ultrachat.listner.JoinListner;
import me.ryandw11.ultrachat.listner.NoSwear;
import me.ryandw11.ultrachat.listner.Notify;
import me.ryandw11.ultrachat.listner.Spy;
import me.ryandw11.ultrachat.listner.StopChat;
import me.ryandw11.ultrachat.pluginhooks.AdvancedBanMute;
import me.ryandw11.ultrachat.pluginhooks.EssentialsMute;
import me.ryandw11.ultrachat.util.Metrics;
import me.ryandw11.ultrachat.util.papi.PAPIDisabled;
import me.ryandw11.ultrachat.util.papi.PAPIEnabled;
import me.ryandw11.ultrachat.util.papi.PlaceHolderAPIHook;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main Class
 *
 * @author Ryandw11
 * @version 2.4
 * Updated for 1.14.
 * (Very few API methods here)
 */
public class UltraChat extends JavaPlugin {

    /**
     * TODO : I just finished allowing Hex colors in chat and removing the option for chat without JSON.
     * Next on the list is to add in the color chat picker.
     */

    public static UltraChat plugin;
    public Permission perms = null;
    public Chat chat = null;
    public Boolean chatStop = false;
    public ChatType md;
    public ArrayList<UUID> stafftoggle = new ArrayList<>();
    public ArrayList<UUID> spytoggle = new ArrayList<>();

    public PlaceHolderAPIHook papi;

    public File datafile = new File(getDataFolder() + "/data/players.yml");
    public FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
    public File channelfile;
    public FileConfiguration channel;

    public File chatColorFile = new File(getDataFolder() + "/chatcolor.yml");
    public FileConfiguration chatColorFC = YamlConfiguration.loadConfiguration(chatColorFile);

    public String prefix;
    public static YamlConfiguration LANG;
    public static File LANG_FILE;

    private ColorGUI colorGUI;
    public ChatColorManager chatColorManager;
    public AddonManager addonManager;
    public ChatColorUtils chatColorUtil;


    @Override
    public void onEnable() {


        /*
         * Plugin setup area
         */
        plugin = this;
        if (getServer().getPluginManager().getPlugin("Vault") == null && !setupChat()) {
            getLogger().info(String.format("[%s] - Vault is not found!", getDescription().getName()));
            getLogger().severe("§cWarning: You do not have Vault installed! This plugin is now disabled!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().info("Hooked into PlaceholderAPI! You can use the place holders!");
            papi = new PAPIEnabled();
        } else {
            papi = new PAPIDisabled();
        }
        getLogger().info(String.format("UltraChat is enabled and running fine! V: %s", getDescription().getVersion()));
        if (getServer().getPluginManager().getPlugin("AdvancedBan") != null && getConfig().getBoolean("pluginhooks.AdvancedBan")) {
            getLogger().info("AdvancedBan detected! Activating hook!");
            getLogger().info("Mutes will now work with the chat types.");
            Bukkit.getServer().getPluginManager().registerEvents(new AdvancedBanMute(), this);
        }
        if (getServer().getPluginManager().getPlugin("Essentials") != null && getConfig().getBoolean("pluginhooks.Essentials")) {
            getLogger().info("Essentials detected! Activating hook!");
            getLogger().info("Mutes will now work with the chat types.");
            Bukkit.getServer().getPluginManager().registerEvents(new EssentialsMute(), this);
        }
        loadFile();
        loadMethod();
        registerConfig();
        loadChannel();

        setupPermissions();
        setupChat();
        setupFormatting();
        loadLang();
        if (plugin.getConfig().getBoolean("bstats")) {
            @SuppressWarnings("unused")
            Metrics m = new Metrics(this);
        }
        addonManager = new AddonManager();
    }

    @Override
    public void onDisable() {
        getLogger().info("[UltraChat] has been disabled correctly!");
        saveFile();
        saveChannel();
    }

    /**
     * Setup the chat formatting.
     */
    public void setupFormatting() {
        String type = getConfig().getString("chat_format");
        if (type.equals("")) {
            getLogger().info("UltraChat will not format the chat. To change this go into the config.");
            return;
        }

        switch (type.toLowerCase()) {
            case "channel":
                Bukkit.getServer().getPluginManager().registerEvents(new ChannelJSON(), this);
                md = ChatType.CHANNEL;
                getLogger().info("Channel chat mode enabled.");
                break;
            case "range":
                Bukkit.getServer().getPluginManager().registerEvents(new RangeJSON(), this);
                getCommand("global").setExecutor(new Global());
                getCommand("world").setExecutor(new World());
                getLogger().info("Range chat mode enabled. The commands /global and /world are now also active.");
                md = ChatType.RANGE;
                break;
            default:
                Bukkit.getServer().getPluginManager().registerEvents(new NormalJSON(), this);
                md = ChatType.NORMAL;
                getLogger().info("Normal chat mode activated!");
                break;
        }
    }

    //Vault set-up =========================================================

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    //========================================================================= END ===============================

    /**
     * Save the data file.
     */
    public void saveFile() {
        try {
            data.save(datafile);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * load the data file
     */
    public void loadFile() {
        if (datafile.exists()) {
            try {
                data.load(datafile);

            } catch (IOException | InvalidConfigurationException e) {

                e.printStackTrace();
            }
        } else {
            try {
                data.save(datafile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (chatColorFile.exists()) {
            try {
                chatColorFC.load(chatColorFile);

            } catch (IOException | InvalidConfigurationException e) {

                e.printStackTrace();
            }
        } else {
            saveResource("chatcolor.yml", false);
            chatColorFC = YamlConfiguration.loadConfiguration(chatColorFile);
        }
    }

    /**
     * Save the channel file.
     */
    public void saveChannel() {

        try {
            channel.save(channelfile);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    /**
     * Load the cannel file.
     */
    public void loadChannel() {
        channelfile = new File(getDataFolder(), "channel.yml");
        if (!channelfile.exists()) {
            channelfile.getParentFile().mkdirs();
            saveResource("channel.yml", false);
        }
        channel = new YamlConfiguration();
        try {
            channel.load(channelfile);

        } catch (IOException | InvalidConfigurationException e) {

            e.printStackTrace();
        }
    }

    /**
     * Get the language file.
     *
     * @return The language file.
     */
    public File getLangFile() {
        return LANG_FILE;
    }

    /**
     * @return
     */
    public YamlConfiguration getLang() {
        return LANG;
    }

    /**
     *
     */
    @SuppressWarnings("static-access")
    public void loadLang() {
        File lang = new File(getDataFolder(), "lang.yml");
        if (!lang.exists()) {
            try {
                getDataFolder().mkdir();
                lang.createNewFile();
                InputStream defConfigStream = this.getResource("lang.yml");
                if (defConfigStream != null) {
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));

                    defConfig.save(lang);
                    Lang.setFile(defConfig);
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace(); // So they notice
                getLogger().severe("The language file could not be created. Disabling plugin.");
                this.setEnabled(false);
            }
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        for (Lang item : Lang.values()) {
            if (conf.getString(item.getPath()) == null) {
                conf.set(item.getPath(), item.getDefault());
            }
        }
        Lang.setFile(conf);
        this.LANG = conf;
        this.LANG_FILE = lang;
        try {
            conf.save(getLangFile());
        } catch (IOException e) {
            getLogger().warning("Failed to save lang.yml.");
            e.printStackTrace();
        }
    }


    private void registerConfig() {
        saveDefaultConfig();
    }

    /**
     * Loads all of the Events and Commands.
     */
    public void loadMethod() {
        getCommand("chat").setExecutor(new ChatCommand());
        getCommand("chat").setTabCompleter(new CommandTabCompleter());
        getCommand("sc").setExecutor(new StaffChat());
        getCommand("sctoggle").setExecutor(new StaffChatToggle());
        getCommand("spy").setExecutor(new SpyCommand());
        getCommand("channel").setExecutor(new ChannelCmd());
        Bukkit.getServer().getPluginManager().registerEvents(new StopChat(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new NoSwear(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Spy(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListner(), this);
        //Bukkit.getServer().getPluginManager().registerEvents(new Format(this), this);
        if (getConfig().getBoolean("console_log"))
            Bukkit.getServer().getPluginManager().registerEvents(new ConsoleLogChat(), this);
        loadVersions();
    }

    /**
     * Loads classes based upon the server version.
     */
    private void loadVersions() {
        String version;

        try {

            version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

        } catch (ArrayIndexOutOfBoundsException w0w) {
            version = " ";
        }
        if (version.equals("v1_14_R1") || version.equals("v1_15_R1")) {

            Bukkit.getServer().getPluginManager().registerEvents(new Notify(), this);
            colorGUI = new ColorGUI_1_15_R1();
            Bukkit.getServer().getPluginManager().registerEvents(new ColorGUI_Latest(), this);
            if (!(plugin.getConfig().getBoolean("ChatColor_Command")))
                getCommand("color").setExecutor(new ColorGUI_1_15_R1());

            this.chatColorUtil = new ChatColorUtil_Old();

        } else {

            Bukkit.getServer().getPluginManager().registerEvents(new Notify(), this);
            colorGUI = new ColorGUI_Latest();
            Bukkit.getServer().getPluginManager().registerEvents(new ColorGUI_Latest(), this);

            chatColorManager = new ChatColorManager(this, Objects.requireNonNull(chatColorFC.getConfigurationSection("chat_colors")));

            if (!(plugin.getConfig().getBoolean("ChatColor_Command")))
                getCommand("color").setExecutor(new ColorGUI_Latest());

            this.chatColorUtil = new ChatColorUtil_Latest(this);
        }
    }

    /**
     * Get the ColorGUI class for the right version.
     *
     * @return A class that implements ColorGUI
     */
    public ColorGUI getColorGUI() {
        return colorGUI;
    }


}

//Permmisions:
//ultrachat.stopchat
//ultrachat.stopchat.bypass
//ultrachat.mode
//ultrachat.vipchat
//ultrachat.broadcast
//ultrachat.staffchat
//ultrachat.staffchat.toggle
//ultrachat.spy
//ultrachat.staffmode
//ultrachat.clearchat
//ultrachat.commandblock.bypass
//ultrachat.sjoin
//ultrachat.color
//ultrachat.worldmute
//ultrachat.sjoin.alert
//ultrachat.spy.others
//ultrachat.fakejoin
//ultrachat.fakeleave
//ultrachat.help