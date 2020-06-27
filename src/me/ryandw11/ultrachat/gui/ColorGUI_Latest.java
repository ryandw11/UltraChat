package me.ryandw11.ultrachat.gui;

import com.sun.istack.internal.NotNull;
import me.ryandw11.ultrachat.util.ChatUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;

import java.util.*;

/**
 * ColorGUI class.
 * Updated for 1.16+
 * @author Ryandw11
 *
 */
public class ColorGUI_Latest implements CommandExecutor, Listener, ColorGUI{
	
	private UltraChat plugin;
	private List<String> colors;
	public ColorGUI_Latest(){
		plugin = UltraChat.plugin;
		colors = new ArrayList<>(Objects.requireNonNull(plugin.chatColorFC.getConfigurationSection("color_gui")).getKeys(false));
	}


	public void openGUI(Player p, int page){
		Inventory inv = Bukkit.createInventory(null, 9*3, Lang.COLOR_GUI.toString());

		int i = getMin(page);
		int invSlot = 0;
		while(i < colors.size() && i < getMax(page)){
			ConfigurationSection section = plugin.chatColorFC.getConfigurationSection("color_gui." + colors.get(i));
			assert section != null;
			if(p.hasPermission(Objects.requireNonNull(section.getString("permission")))){
				ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(section.getString("item")))));
				ItemMeta meta = item.getItemMeta();
				assert meta != null;
				meta.setDisplayName(ChatUtil.translateColorCode(section.getString("color")) + section.getName());
				meta.setLore(Collections.singletonList(ChatColor.AQUA + "Click this to set your chat color!"));
				item.setItemMeta(meta);
				inv.setItem(invSlot, item);
			}else{
				inv.setItem(invSlot, getNoPermItem());
			}

			invSlot++;
			i++;
		}
		setBottom(inv, page);
		p.openInventory(inv);
	}

	private int getMax(int page){
		return 9 * (2 * page);
	}
	private int getMin(int page){
		return (9 * (page*2)) - (9*2);
	}

	private ItemStack getNoPermItem(){
		ItemStack itemStack = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = itemStack.getItemMeta();
		assert meta != null;
		meta.setDisplayName(ChatColor.RED + "You do not have permission for this color!");
		itemStack.setItemMeta(meta);
		return itemStack;
	}

	private ItemStack getBottomStack(){
		ItemStack bottomStack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta bottomMeta = bottomStack.getItemMeta();
		assert bottomMeta != null;
		bottomMeta.setDisplayName(ChatColor.GRAY + " ");
		bottomStack.setItemMeta(bottomMeta);
		return bottomStack;
	}

	private ItemStack getPrevStack(int page){
		ItemStack prevPage = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
		ItemMeta prevMeta = prevPage.getItemMeta();
		assert prevMeta != null;
		prevMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "<< Previous Page");
		prevPage.setItemMeta(prevMeta);
		if(page > 1)
			prevPage.setAmount(page-1);
		return prevPage;
	}

	private ItemStack getCurrentStack(int page){
		ItemStack currentPage = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta currentMeta = currentPage.getItemMeta();
		assert currentMeta != null;
		currentMeta.setDisplayName(ChatColor.GRAY + "Current Page: " + page);
		currentPage.setItemMeta(currentMeta);
		currentPage.setAmount(page);
		return currentPage;
	}

	private ItemStack getNextStack(int page){
		ItemStack nextPage = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta nextMeta = nextPage.getItemMeta();
		assert nextMeta != null;
		nextMeta.setDisplayName(ChatColor.RED + "Next Page >>");
		nextPage.setItemMeta(nextMeta);
		nextPage.setAmount(page+1);
		return nextPage;
	}

	private void setBottom(Inventory inventory, int page){
		ItemStack bottomStack = getBottomStack();
		ItemStack prevPage = getPrevStack(page);
		ItemStack currentPage = getCurrentStack(page);
		ItemStack nextPage = getNextStack(page);
		for(int i = 18; i < 27; i++){
			if(i == 21 && page > 1){
				inventory.setItem(i, prevPage);
			}else if(i == 22){
				inventory.setItem(i, currentPage);
			}else if(i == 23 && colors.size() > getMax(page)){
				inventory.setItem(i, nextPage);
			}else{
				inventory.setItem(i, bottomStack);
			}
		}
	}

	/*
	 * Command
	 */
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
		if(!(sender instanceof Player)){
			plugin.getLogger().info("This command is for players only!");
			return true;
		}
		Player p = (Player) sender;
		if(p.hasPermission("ultrachat.color")){
			openGUI(p.getPlayer(), 1);
		}
		else{
			p.sendMessage(Lang.NO_PERM.toString());
		}
		return false;
		
	}
	/*
	 * Event
	 */
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		try {
			if(e.getView() == null || e.getView().getTitle() == null) return;
			if(!e.getView().getTitle().equalsIgnoreCase(Lang.COLOR_GUI.toString())) return;
		}catch(IllegalStateException ex) {
			return;
		}
		
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().hasItemMeta()){
			return;
		}

		int page = Objects.requireNonNull(e.getInventory().getItem(22)).getAmount();

		ItemStack item = e.getCurrentItem();
		if(!e.getInventory().contains(item)) return;

		if(item.equals(getBottomStack()) || item.equals(getCurrentStack(page)) || item.equals(getNoPermItem())) return;
		if(item.equals(getNextStack(page))){
			p.closeInventory();
			openGUI(p, page+1);
		}else if(item.equals(getPrevStack(page))){
			p.closeInventory();
			openGUI(p, page-1);
		}else{
			ConfigurationSection section = plugin.chatColorFC.getConfigurationSection("color_gui."
					+ ChatColor.stripColor(Objects.requireNonNull(item.getItemMeta()).getDisplayName()));

			assert section != null;
			if(!p.hasPermission(Objects.requireNonNull(section.getString("permission")))){
				p.sendMessage(ChatColor.RED + "You do not have permission to use this chat color!");
				return;
			}

			plugin.data.set(p.getUniqueId() + ".color", section.getString("color"));
			plugin.saveFile();

			p.spigot().sendMessage(TextComponent.fromLegacyText(plugin.chatColorUtil.translateChatColor(section.getString("color") + Lang.CHAT_COLOR_CHANGE)));

			p.closeInventory();
		}
		
	}
	
	
}
