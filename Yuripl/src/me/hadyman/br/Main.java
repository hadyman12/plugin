package me.hadyman.br;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Main getInstance(){
	    return Main.getPlugin(Main.class);
	}
	public void onEnable() {
		if(!new File(getDataFolder(), "config.yml").exists()){
		    saveDefaultConfig();
		    Bukkit.getConsoleSender().sendMessage("Configuração criada com sucesso!");
		}
		this.getCommand("pegaritem").setExecutor(this);
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		Bukkit.getConsoleSender().sendMessage("§7-");
		Bukkit.getConsoleSender().sendMessage("§7-       §c[§eItemPro§c]");
		Bukkit.getConsoleSender().sendMessage("§7- §ePlugin criado por: §9HadyMan");
		Bukkit.getConsoleSender().sendMessage("§7- §eSuporte: §bLive:hadyman67");
		Bukkit.getConsoleSender().sendMessage("§7- §eAcesse: §aHadydev.com.br");
		Bukkit.getConsoleSender().sendMessage("§7-");
	}
	
	public void onDisable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("pegaritem")) {
			Player p = (Player)sender;
			if(!p.hasPermission("itempro.pegar")) {
				p.sendMessage(getConfig().getString("msg_sem_permi").replaceAll("&", "§"));
				return true;
			}
			ItemStack item = new ItemStack(Material.DIAMOND_AXE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(getConfig().getString("Nome_Machado").replaceAll("&", "§"));
			ArrayList<String> lore = new ArrayList<>();
			lore.add(getConfig().getString("Linha1").replaceAll("&", "§"));
			lore.add(getConfig().getString("Linha2").replaceAll("&", "§"));
			lore.add(getConfig().getString("Linha3").replaceAll("&", "§"));
			meta.setLore(lore);
			meta.addEnchant(Enchantment.DURABILITY, 1000, true);
			item.setItemMeta(meta);
			p.getInventory().addItem(item);
			p.updateInventory();
			p.sendMessage(getConfig().getString("Msg_recebendo").replaceAll("&", "§"));
			
			
		}
		return super.onCommand(sender, command, label, args);
	}

}
