package me.hadyman.br;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
public class Listeners implements Listener {
	
	@EventHandler
	private void OnPlayerMoveEvent(final PlayerMoveEvent e) {
		if (e.getPlayer().getItemInHand().getType() != Material.AIR) {
			if (e.getPlayer().getItemInHand().getType() == Material.DIAMOND_AXE) {
				if (e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()) {
					if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().getConfig().getString("Nome_Machado").replaceAll("&", "§"))) {
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 2));						
					}
				}
			}
			
		}
	}
	
	   @EventHandler
	   private void onPlayerDamageEntityEvent(final EntityDamageByEntityEvent e) {
		   if (e.getDamager() instanceof Player) {
			   final Player player = (Player) e.getDamager();
				if (player.getItemInHand().getType() != Material.AIR) {
					if (player.getItemInHand().getType() == Material.DIAMOND_AXE) {
						if (player.getItemInHand().getItemMeta().hasDisplayName()) {
							if (player.getItemInHand().getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("Nome_Machado").replaceAll("&", "§"))) {
								e.setDamage(e.getDamage() + Main.getInstance().getConfig().getInt("Dano"));
								final Random r = new Random();
								final int i = r.nextInt(100);
								if (i <= Main.getInstance().getConfig().getInt("Chance")) {
									e.getEntity().getLocation().getWorld().strikeLightning(e.getEntity().getLocation());
								}
								
							}
					
						}
					}
					
				}
			}
			   
		   }
	   @EventHandler
	   private void onEntityDamage(EntityDamageEvent e) {
		   if (e.getEntity() instanceof Player) {
			   if (e.getCause() == DamageCause.LIGHTNING) {
	                e.setCancelled(true);
	            }
			   
		   }
	   }
		   
}


