package me.ryandw11.ultrachat.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
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
import org.bukkit.material.Wool;

import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.api.Lang;

/**
 * ColorGUI class.
 * Old and outdated. Only in for compatibility.
 * (When compiling for 1.13+ only it is ok to leave this class out (Just remove references to it in the UltraChat main class))
 * @author Ryandw11
 *
 */
public class ColorGUI_Outdated implements CommandExecutor, Listener, ColorGUI{
	
	private UltraChat plugin;
	public ColorGUI_Outdated(){
		plugin = UltraChat.plugin;
	}
	@SuppressWarnings("deprecation")
	public void openGUI(Player p){
		Inventory i = Bukkit.createInventory(null, 9*2, Lang.COLOR_GUI.toString());
		
		Wool darkblue = new Wool(DyeColor.BLUE);
		ItemStack darkblueitem = darkblue.toItemStack(1);
		ItemMeta darkbluemeta = darkblueitem.getItemMeta();
		
		Wool green = new Wool(DyeColor.GREEN);
		ItemStack greenitem = green.toItemStack(1);
		ItemMeta greenmeta = greenitem.getItemMeta();
		
		Wool lightblue = new Wool(DyeColor.CYAN);
		ItemStack lightblueitem = lightblue.toItemStack(1);
		ItemMeta lightbluemeta = lightblueitem.getItemMeta();
		
		Wool red = new Wool(DyeColor.RED);
		ItemStack reditem = red.toItemStack(1);
		ItemMeta redmeta = reditem.getItemMeta();
		
		Wool purple = new Wool(DyeColor.PURPLE);
		ItemStack purpleitem = purple.toItemStack(1);
		ItemMeta purplemeta = purpleitem.getItemMeta();
		
		Wool gold = new Wool(DyeColor.ORANGE);
		ItemStack golditem = gold.toItemStack(1);
		ItemMeta goldmeta = golditem.getItemMeta();
		
		ItemStack lightgrayitem = new ItemStack(Material.CLAY);
		ItemMeta lightgraymeta = lightgrayitem.getItemMeta();
		
		Wool gray = new Wool(DyeColor.GRAY);
		ItemStack grayitem = gray.toItemStack(1);
		ItemMeta graymeta = grayitem.getItemMeta();
		
		ItemStack blueitem = new ItemStack(Material.LAPIS_BLOCK);
		ItemMeta bluemeta = blueitem.getItemMeta();
		
		Wool lightgreen = new Wool(DyeColor.LIME);
		ItemStack lightgreenitem = lightgreen.toItemStack(1);
		ItemMeta lightgreenmeta = lightgreenitem.getItemMeta();
		
		Wool aqua = new Wool(DyeColor.LIGHT_BLUE);
		ItemStack aquaitem = aqua.toItemStack(1);
		ItemMeta aquameta = aquaitem.getItemMeta();
		
		Wool lightred = new Wool(DyeColor.PINK);
		ItemStack lightreditem = lightred.toItemStack(1);
		ItemMeta lightredmeta = lightreditem.getItemMeta();
		
		Wool pink = new Wool(DyeColor.MAGENTA);
		ItemStack pinkitem = pink.toItemStack(1);
		ItemMeta pinkmeta = pinkitem.getItemMeta();
		
		Wool yellow = new Wool(DyeColor.YELLOW);
		ItemStack yellowitem = yellow.toItemStack(1);
		ItemMeta yellowmeta = yellowitem.getItemMeta();
		
		Wool white = new Wool(DyeColor.WHITE);
		ItemStack whiteitem = white.toItemStack(1);
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
		
		whitemeta.setDisplayName("§fWhite Chat");
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
		@SuppressWarnings("deprecation")
		int data = item.getDurability();
		
		if(item.getType() == Material.LAPIS_BLOCK){
			if(p.hasPermission("ultrachat.color.blue")){
			 p.sendMessage(ChatColor.BLUE + "You choose blue chat color!");
	            p.closeInventory();
	            plugin.data.set(p.getUniqueId() + ".color", "&9");
	            plugin.saveFile();
			}
			else{
				p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
			}
		}
		else if(item.getType() == Material.CLAY){
        	if(p.hasPermission("ultrachat.color.lightgray")){
            p.sendMessage(ChatColor.GRAY + "You choose light gray color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&7");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
		}
		else{
		
		switch(data) {
        case 0:
            p.sendMessage(ChatColor.WHITE + "You choose white chat color!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&f");
            plugin.saveFile();
            break;
        case 1:
        	if(p.hasPermission("ultrachat.color.gold")){
        		p.sendMessage(ChatColor.GOLD + "You choose gold color chat!");
            	p.closeInventory();
            	plugin.data.set(p.getUniqueId() + ".color", "&6");
            	plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            	break;

        case 2:
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
            
        	

        case 3:
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
        	
        	
        	
        case 4:
        	if(p.hasPermission("ultrachat.color.yellow")){
            p.sendMessage(ChatColor.YELLOW + "You choose yellow color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&e");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        	
        case 5:
        	if(p.hasPermission("ultrachat.color.lightgreen")){
            p.sendMessage(ChatColor.GREEN + "You choose light green color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&a");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case 6:
        	if(p.hasPermission("ultrachat.color.lightred")){
            p.sendMessage(ChatColor.RED + "You choose light red color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&c");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case 7:
        	if(p.hasPermission("ultrachat.color.gray")){
            p.sendMessage(ChatColor.DARK_GRAY + "You choose gray color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&8");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case 9:
        	if(p.hasPermission("ultrachat.color.cyan")){
            p.sendMessage(ChatColor.DARK_AQUA + "You choose cyan color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&3");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case 10:
        	if(p.hasPermission("ultrachat.color.purple")){
            p.sendMessage(ChatColor.DARK_PURPLE + "You choose purple color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&5");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case 11:
        	if(p.hasPermission("ultrachat.color.darkblue")){
            p.sendMessage(ChatColor.DARK_BLUE + "You choose dark blue color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&1");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case 13:
        	if(p.hasPermission("ultrachat.color.green")){
            p.sendMessage(ChatColor.DARK_GREEN + "You choose green color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&2");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
        case 14:
        	if(p.hasPermission("ultrachat.color.red")){
            p.sendMessage(ChatColor.DARK_RED + "You choose red color chat!");
            p.closeInventory();
            plugin.data.set(p.getUniqueId() + ".color", "&4");
            plugin.saveFile();
        	}else{
        		p.sendMessage(ChatColor.RED + "You do not have permission for this color!");
        	}
            break;
		}
		}
		
	}
	
	
}