package me.ryandw11.ultrachat.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;

/**
 * ColorGUI class.
 * Updated for 1.13.
 * @author Ryandw11
 *
 */
public class ColorGUI_Latest implements CommandExecutor, Listener, ColorGUI{
	
	private UltraChat plugin;
	public ColorGUI_Latest(){
		plugin = UltraChat.plugin;
	}
	public void openGUI(Player p){
		Inventory i = Bukkit.createInventory(null, 9*2, Lang.COLOR_GUI.toString());
		
		ItemStack darkblueitem = new ItemStack(Material.BLUE_WOOL);
		ItemMeta darkbluemeta = darkblueitem.getItemMeta();
		
		ItemStack greenitem = new ItemStack(Material.GREEN_WOOL);
		ItemMeta greenmeta = greenitem.getItemMeta();
		
		ItemStack lightblueitem = new ItemStack(Material.CYAN_WOOL);
		ItemMeta lightbluemeta = lightblueitem.getItemMeta();
		
		ItemStack reditem = new ItemStack(Material.RED_WOOL);
		ItemMeta redmeta = reditem.getItemMeta();
		
		ItemStack purpleitem = new ItemStack(Material.PURPLE_WOOL);
		ItemMeta purplemeta = purpleitem.getItemMeta();
		
		ItemStack golditem = new ItemStack(Material.ORANGE_WOOL);
		ItemMeta goldmeta = golditem.getItemMeta();
		
		ItemStack lightgrayitem = new ItemStack(Material.LIGHT_GRAY_WOOL);
		ItemMeta lightgraymeta = lightgrayitem.getItemMeta();
		
		ItemStack grayitem = new ItemStack(Material.GRAY_WOOL);
		ItemMeta graymeta = grayitem.getItemMeta();
		
		ItemStack blueitem = new ItemStack(Material.LAPIS_BLOCK);
		ItemMeta bluemeta = blueitem.getItemMeta();
		
		ItemStack lightgreenitem = new ItemStack(Material.LIME_WOOL);
		ItemMeta lightgreenmeta = lightgreenitem.getItemMeta();
		
		ItemStack aquaitem = new ItemStack(Material.LIGHT_BLUE_WOOL);
		ItemMeta aquameta = aquaitem.getItemMeta();
		
		ItemStack lightreditem = new ItemStack(Material.PINK_WOOL);
		ItemMeta lightredmeta = lightreditem.getItemMeta();
		
		ItemStack pinkitem = new ItemStack(Material.MAGENTA_WOOL);
		ItemMeta pinkmeta = pinkitem.getItemMeta();
		
		ItemStack yellowitem = new ItemStack(Material.YELLOW_WOOL);
		ItemMeta yellowmeta = yellowitem.getItemMeta();
		
		ItemStack whiteitem = new ItemStack(Material.WHITE_WOOL);
		ItemMeta whitemeta = whiteitem.getItemMeta();
		
		//==========================================================

		darkbluemeta.setDisplayName("§1Dark Blue Color Chat");
		darkblueitem.setItemMeta(darkbluemeta);
		
		greenmeta.setDisplayName("§2Green Color Chat");
		greenitem.setItemMeta(greenmeta);
		
		lightbluemeta.setDisplayName("§3Cyan Color Chat");
		lightblueitem.setItemMeta(lightbluemeta);
		
		redmeta.setDisplayName("§4Red Color Chat");
		reditem.setItemMeta(redmeta);
		
		purplemeta.setDisplayName("§5Purple Color Chat");
		purpleitem.setItemMeta(purplemeta);
		
		goldmeta.setDisplayName("§6Gold Color Chat");
		golditem.setItemMeta(goldmeta);
		
		lightgraymeta.setDisplayName("§7Light Gray Color Chat");
		lightgrayitem.setItemMeta(lightgraymeta);
		
		graymeta.setDisplayName("§8Gray Color Chat");
		grayitem.setItemMeta(graymeta);
		
		bluemeta.setDisplayName("§9Blue Color Chat");
		blueitem.setItemMeta(bluemeta);
		
		lightgreenmeta.setDisplayName("§aLight Green Color Chat");
		lightgreenitem.setItemMeta(lightgreenmeta);
		
		aquameta.setDisplayName("§bAqua Color Chat");
		aquaitem.setItemMeta(aquameta);
		
		lightredmeta.setDisplayName("§cLight Red Color Chat");
		lightreditem.setItemMeta(lightredmeta);
		
		pinkmeta.setDisplayName("§dMagenta Color Chat");
		pinkitem.setItemMeta(pinkmeta);
		
		yellowmeta.setDisplayName("§eYellow Color Chat");
		yellowitem.setItemMeta(yellowmeta);
		
		whitemeta.setDisplayName("§fWhite Color Chat");
		whiteitem.setItemMeta(whitemeta);
		//==========================================================

		
		i.setItem(0, darkblueitem);
		i.setItem(1, greenitem);
		i.setItem(2, lightblueitem);
		i.setItem(3, reditem);
		i.setItem(4, purpleitem);
		i.setItem(5, golditem);
		i.setItem(6, lightgrayitem);
		i.setItem(7, grayitem);
		i.setItem(8, blueitem);
		i.setItem(9, lightgreenitem);
		i.setItem(10, aquaitem);
		i.setItem(11, lightreditem);
		i.setItem(12, pinkitem);
		i.setItem(13, yellowitem);
		i.setItem(14, whiteitem);

		
		p.openInventory(i);
	}
	/*
	 * Command
	 */
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(!(sender instanceof Player)){
			plugin.getLogger().info("This command is for players only!");
			return true;
		}
		Player p = (Player) sender;
		if(p.hasPermission("ultrachat.color")){
			openGUI(p.getPlayer());
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
		if(!e.getInventory().getName().equalsIgnoreCase(Lang.COLOR_GUI.toString())) return;
		
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().hasItemMeta()){
			p.closeInventory();
			return;
		}
		//================
		ItemStack item = e.getCurrentItem();
		
		switch(item.getType()) {
		case LAPIS_BLOCK:
			if(p.hasPermission("ultrachat.color.blue")){
				 p.sendMessage(ChatColor.BLUE + "You choose blue color chat!");
		            p.closeInventory();
		            plugin.data.set(p.getUniqueId() + ".color", "&9");
		            plugin.saveFile();
				}
				else{
					p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
				}
			break;
        case WHITE_WOOL:
            p.sendMessage(ChatColor.WHITE + "You choose white color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&f");
            plugin.saveFile();
            break;
        case ORANGE_WOOL:
        	if(p.hasPermission("ultrachat.color.gold")){
        		p.sendMessage(ChatColor.GOLD + "You choose gold color chat!");
            	p.closeInventory();
            	plugin.data.set(p.getUniqueId() + ".color", "&6");
            	plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            	break;

        case MAGENTA_WOOL:
        	if(p.hasPermission("ultrachat.color.magenta")){
            p.sendMessage(ChatColor.LIGHT_PURPLE + "You choose magenta color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&d");
            plugin.saveFile();
        	}
        	else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
            
        	

        case LIGHT_BLUE_WOOL:
        	if(p.hasPermission("ultrachat.color.aqua")){
            p.sendMessage(ChatColor.AQUA + "You choose Aqua color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&b");
            plugin.saveFile();
        	}
            else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        	
        	
        	
        case YELLOW_WOOL:
        	if(p.hasPermission("ultrachat.color.yellow")){
            p.sendMessage(ChatColor.YELLOW + "You choose yellow color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&e");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        	
        case LIME_WOOL:
        	if(p.hasPermission("ultrachat.color.lightgreen")){
            p.sendMessage(ChatColor.GREEN + "You choose light green color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&a");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case PINK_WOOL:
        	if(p.hasPermission("ultrachat.color.lightred")){
            p.sendMessage(ChatColor.RED + "You choose light red color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&c");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case GRAY_WOOL:
        	if(p.hasPermission("ultrachat.color.gray")){
            p.sendMessage(ChatColor.DARK_GRAY + "You choose gray color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&8");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case LIGHT_GRAY_WOOL:
        	if(p.hasPermission("ultrachat.color.lightgray")){
            p.sendMessage(ChatColor.GRAY + "You choose light gray color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&7");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case CYAN_WOOL:
        	if(p.hasPermission("ultrachat.color.cyan")){
            p.sendMessage(ChatColor.DARK_AQUA + "You choose cyan color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&3");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case PURPLE_WOOL:
        	if(p.hasPermission("ultrachat.color.purple")){
            p.sendMessage(ChatColor.DARK_PURPLE + "You choose purple color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&5");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case BLUE_WOOL:
        	if(p.hasPermission("ultrachat.color.darkblue")){
            p.sendMessage(ChatColor.DARK_BLUE + "You choose dark blue color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&1");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case GREEN_WOOL:
        	if(p.hasPermission("ultrachat.color.green")){
            p.sendMessage(ChatColor.DARK_GREEN + "You choose green color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&2");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case RED_WOOL:
        	if(p.hasPermission("ultrachat.color.red")){
            p.sendMessage(ChatColor.DARK_RED + "You choose red color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&4");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
		default:
			 p.sendMessage(ChatColor.WHITE + "You choose white color chat!");
	         p.closeInventory();
	         plugin.data.set(p.getUniqueId() + ".color", "&f");
	         plugin.saveFile();
			break;
		}
		
	}
	
	
}
