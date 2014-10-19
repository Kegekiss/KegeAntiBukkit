package com.kegekiss.kegeantibukkit;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class KegeAntiBukkit extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		// On enregistre l'event
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		String message = event.getMessage();
		String[] command = message.split(":");
		if (event.getPlayer().isOp()) {
			// Le joueur est OP, on ne fait rien!
			return;
		} else {
			// On retourne la dernière fraction après tout les ":" .
			String newMessage = command[command.length-1];
			if (!newMessage.startsWith("/")) {
				newMessage = "/" + command[command.length-1];
			}
			if (!newMessage.equals(message)) {
				event.setMessage(newMessage);
				this.getLogger().info("Commande altérée! Nouvelle commande : '" + newMessage + "' !");
			}			
		}
	}
	
}
